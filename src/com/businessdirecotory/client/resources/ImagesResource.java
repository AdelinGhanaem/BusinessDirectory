package com.businessdirecotory.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */

public interface ImagesResource extends ClientBundle {

  @Source("loading.gif")
  ImageResource  loadingImage();

  @Source("email.png")
  ImageResource  email();

  @Source("contactFace.png")
  ImageResource  contactFace();

  @Source("activity.png")
  ImageResource  activity();

  @Source("map.png")
  ImageResource map();

  @Source("buttonLoading.gif")
  ImageResource buttonLoading();


}
