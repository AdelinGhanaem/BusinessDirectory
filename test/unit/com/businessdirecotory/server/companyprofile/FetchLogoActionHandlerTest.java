package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.actions.FetchLogoAction;
import com.businessdirecotory.shared.entites.reponses.FetchLogoResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchLogoActionHandlerTest {


  FetchLogoActionHandler handler;

  @Mock
  ImagesRepository imagesRepository;


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new FetchLogoActionHandler(imagesRepository);
  }

  @Test
  public void fetchImageByUserIdAndReturnsImageURL() throws Exception {
    String url = "URL";

    when(imagesRepository.getURL(1l)).thenReturn(url);

    FetchLogoResponse response = handler.handle(new FetchLogoAction(1l));

    assertThat(response, is(notNullValue()));

    assertThat(response.getImageURL(), is(notNullValue()));


  }
}
