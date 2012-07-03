package com.businessdirecotory.client.search;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import static org.mockito.Mockito.doAnswer;

/**

 */
public class TestingAsyncCallbacksHelper {

  public static <T> Stubber doOnSuccess(final T result) {

    return doAnswer(new Answer<T>() {
      @Override
      public T answer(InvocationOnMock invocation) throws Throwable {

        Object[] args = invocation.getArguments();

        AsyncCallback<T> callback = (AsyncCallback<T>) args[args.length - 1];

        callback.onSuccess(result);

        return null;
      }
    });
  }


  public static <T>Stubber doOnFailure(final Throwable throwable) {

    return doAnswer(new Answer<Throwable>() {
            @Override
            public Throwable answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();

                AsyncCallback<T> callback = (AsyncCallback<T>) args[args.length - 1];

                callback.onFailure(throwable);

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }




}
