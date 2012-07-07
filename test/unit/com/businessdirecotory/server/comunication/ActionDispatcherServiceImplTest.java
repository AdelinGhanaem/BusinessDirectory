package com.businessdirecotory.server.comunication;

import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
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
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  private class TestResponse implements Response {
  }

  @Mock
  public ActionHandlerRepository repository;

  private ActionDispatcherServiceImpl actionDispatcherService;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    actionDispatcherService = new ActionDispatcherServiceImpl(repository);
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

  @Test
  public void returnNullResponseWhenNoActionHandlerIsRegistered() {
    TestAction action = new TestAction();

    when(repository.getActionHandler(action.getClass())).thenReturn(null);
    Response response = actionDispatcherService.dispatch(action);
    assertThat(response, is(nullValue()));
  }

}
