package com.businessdirecotory.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */

public interface ImagesResource extends ClientBundle {

  public static ImagesResource INSTANCE = GWT.create(ImagesResource.class);


  @Source("loading.gif")
  ImageResource loadingImage();

  @Source("email.png")
  ImageResource email();

  @Source("contactFace.png")
  ImageResource contactFace();

  @Source("activity.png")
  ImageResource activity();

  @Source("map.png")
  ImageResource map();

  @Source("buttonLoading.gif")
  ImageResource buttonLoading();


  @Source("mobile_phone.png")
  ImageResource phoneNumber();


  @Source("facebook.png")
  ImageResource faceBook();


  @Source("plus.png")
  ImageResource plus();

  @Source("twitter.png")
  ImageResource twitter();


  @Source("blog.png")
  ImageResource description();

  @Source("Alert.png")
  ImageResource alert();
}
