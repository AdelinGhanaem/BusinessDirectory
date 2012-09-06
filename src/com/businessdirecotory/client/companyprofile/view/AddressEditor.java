package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.shared.entites.Address;
import com.businessdirecotory.shared.entites.LatLong;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.event.Event;
import com.google.gwt.maps.client.event.HasMouseEvent;
import com.google.gwt.maps.client.event.MouseEventCallback;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AddressEditor extends Composite implements Editor<Address> {

  interface AddressEditorUiBinder extends UiBinder<HTMLPanel, AddressEditor> {
  }

  private static AddressEditorUiBinder ourUiBinder = GWT.create(AddressEditorUiBinder.class);


  @UiField
  TextBox city;

  @UiField
  TextBox county;

  @UiField
  TextBox street;

  @UiField
  TextBox description;

  @Ignore
  MapWidget mapWidget;

  @UiField
  SimplePanel mapWrapper;

  @Ignore
  long id;

  @Ignore
  private MapOptions mapOptions;

  final Marker marker = new Marker();

  @Ignore
  LatLong latAndLong = new LatLong(25.38191796875003d, 42.60635104185243d);

  LeafValueEditor<LatLong> latLong = new LeafValueEditor<LatLong>() {

    @Override
    public void setValue(LatLong value) {
      marker.setPosition(new LatLng(value.getLatitude(),value.getLongitude()));
      latAndLong = value;
    }

    @Override
    public LatLong getValue() {
      return latAndLong;
    }
  };

  public AddressEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    mapOptions = new MapOptions();
    mapOptions.setCenter(new LatLng(42.60635104185243d, 25.38191796875003d));
    mapOptions.setZoom(8);
    mapOptions.setMapTypeId(new MapTypeId().getHybrid());
    mapOptions.setDraggable(true);
    mapOptions.setDisableDoubleClickZoom(true);
    mapOptions.setNavigationControl(true);
    mapOptions.setMapTypeControl(true);
    mapOptions.setMapTypeId(new MapTypeId().getHybrid());
    mapOptions.setScaleControl(true);
    mapWidget = new MapWidget(mapOptions);
    mapWidget.setSize("400px", "400px");
    Event.addListener(mapWidget.getMap(), "click", new MouseEventCallback() {
      @Override
      public void callback(HasMouseEvent event) {
        HasLatLng hasLatLng = event.getLatLng();
        marker.setPosition(hasLatLng);
        marker.setMap(mapWidget.getMap());
        latAndLong.setLatitude(hasLatLng.getLatitude());
        latAndLong.setLongitude(hasLatLng.getLongitude());
      }
    });
    mapWrapper.add(mapWidget);
    initWidget(rootElement);

  }


}