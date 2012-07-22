package com.businessdirecotory.server.comunication;

import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepository;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ActionDispatcherServiceImplTest {

  private class TestAction implements Action<TestResponse> {
  }

  private class TestActionHandler implements ActionHandler {
    @Override
    public Response handle(Action action) {
      return null;
    }
  }

  private class TestResponse implements Response {
  }

  @Mock
  public ActionHandlerRepository repository;

  @Captor
  ArgumentCaptor<Date> captor;

  private ActionDispatcherServiceImpl actionDispatcherService;

  @Mock
  AuthorizedTokensRepository authorizedTokens;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    actionDispatcherService = new ActionDispatcherServiceImpl(repository, authorizedTokens);
  }

  @Test
  public void shouldDispatchActionAndReturnsResponse() {

    Action<Response> action = new Action<Response>() {
    };
    when(repository.getActionHandler(action.getClass())).thenReturn(new ActionHandler() {
      @Override
      public Response handle(Action action) {
        return new Response() {
        };
      }
    });
    Response response = actionDispatcherService.dispatch(action);

    assertThat(response, is(notNullValue()));

    verify(repository).getActionHandler(action.getClass());
  }

  @Test
  public void tryWithAnotherAction() {

    TestAction action = new TestAction();

    when(repository.getActionHandler(action.getClass())).thenReturn(new TestActionHandler());

    TestResponse response = actionDispatcherService.dispatch(action);

    assertThat(action, is(notNullValue()));

    assertThat((Class<TestAction>) action.getClass(), is(equalTo(TestAction.class)));

    verify(repository).getActionHandler(action.getClass());

  }

  @Test(expected = NoActionHandlerException.class)
  public void throwsExceptionResponseWhenNoActionHandlerIsRegistered() {
    TestAction action = new TestAction();
    when(repository.getActionHandler(action.getClass())).thenReturn(null);
    actionDispatcherService.dispatch(action);
  }

  @Test
  public void checkTokenIsSecuredForSecuredActions() throws ParseException {
    Token token = new Token();
    SecuredAction<SecuredResponse> action = new SecuredAction(new TestAction(), token);

    when(repository.getActionHandler(action.getAction().getClass())).thenReturn(new ActionHandler() {
      @Override
      public Response handle(Action action) {
        return new Response() {
        };
      }
    });

    when(authorizedTokens.isAuthorized(eq(token), isA(Date.class))).thenReturn(true);

    SecuredResponse securedResponse = actionDispatcherService.dispatch(action);

    verify(authorizedTokens).isAuthorized(eq(token), captor.capture());
    verify(repository).getActionHandler(action.getAction().getClass());
    assertThat(securedResponse, is(notNullValue()));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date expected = sdf.parse(sdf.format(new Date()));
    Date actual = sdf.parse(sdf.format(captor.getValue()));
    assertEquals(expected, actual);
  }

  @Test
  public void returnsSecurityResponseWithNullValueWhenTokenIsNotAuthorized() {

    Token token = new Token("user");

    Action action = new Action() {
    };

    SecuredAction securedAction = new SecuredAction(action, token);

    Date date = Calendar.getInstance().getTime();

    when(authorizedTokens.isAuthorized(token, date)).thenReturn(false);

    SecuredResponse response = (SecuredResponse) actionDispatcherService.dispatch(securedAction);

    verify(authorizedTokens).isAuthorized(eq(token), isA(Date.class));

    verify(repository, never()).getActionHandler(action.getClass());

    assertThat(response, is(notNullValue()));

    assertThat(response.getResponse(), is(nullValue()));

  }

  @Test
  public void handlesSecuredActionWhenActionIsAuthorized() {
    TestAction action = new TestAction();

    Token token = new Token("user");

    SecuredAction<SecuredResponse<TestResponse>> securedAction =
            new SecuredAction<SecuredResponse<TestResponse>>(action, token);
    Date date = new Date();
    when(authorizedTokens.isAuthorized(eq(token), isA(Date.class))).thenReturn(true);

    when(repository.getActionHandler(action.getClass())).thenReturn(new ActionHandler() {
      @Override
      public Response handle(Action action) {
        return new TestResponse();
      }
    });

    Response response = actionDispatcherService.dispatch(securedAction);

    verify(repository).getActionHandler(action.getClass());
    verify(authorizedTokens).isAuthorized(eq(token), isA(Date.class));
    assertThat(response, is(notNullValue()));
    assertEquals(response.getClass(), SecuredResponse.class);

  }

}
