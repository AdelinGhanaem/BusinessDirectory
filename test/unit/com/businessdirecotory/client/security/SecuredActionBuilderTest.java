package com.businessdirecotory.client.security;

import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.client.authorization.SecuredActionBuilderImpl;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SecuredActionBuilderTest {


  private class TestAction<R extends Response> implements Action<R> {
  }

  private class TestResponse implements Response {
  }

  SecuredActionBuilderImpl securedActionBuilder;

  @Mock
  SecurityTokenProvider provider;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    securedActionBuilder = new SecuredActionBuilderImpl(provider);
  }

  @Test
  public void returnsSecuredAction() {
    TestAction action = new TestAction();
    SecuredAction securedAction = securedActionBuilder.build(action);
    assertThat(securedAction, is(notNullValue()));
    assertThat(securedAction.getAction(), is(notNullValue()));
    assertEquals(securedAction.getAction().getClass(), action.getClass());
  }

  @Test
  public void returnedActionContainsSecurityToke() {
    Token token = new Token(2l, 2l, "Adelin", new Date());
    TestAction<TestResponse> testAction = new TestAction<TestResponse>();
    when(provider.getToken()).thenReturn(token);
    SecuredAction<SecuredResponse> action = securedActionBuilder.build(testAction);
    verify(provider).getToken();
    assertThat(action.getToken(), is(notNullValue()));
    assertThat(action.getToken(), is(equalTo(token)));
    assertThat(action.getToken().getUser(), is(equalTo("Adelin")));


  }
}
