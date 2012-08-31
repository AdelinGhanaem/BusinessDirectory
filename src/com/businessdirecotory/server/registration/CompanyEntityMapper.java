package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.Entity;

public class CompanyEntityMapper {

  private CompanyBuilder builder = new CompanyBuilder();

  public CompanyEntityMapper() {
  }

  Entity createEntity(Company company, Entity companyEntity) {



    return companyEntity;
  }

  Company create(Entity entity) {
//    Company company = builder.withId(entity.getKey().getId()).withName(String.valueOf(entity.getProperty(CompanyEntity.NAME)))
//            .withActivity(String.valueOf(entity.getProperty(CompanyEntity.ACTIVITY)))
//            .withLocation(String.valueOf(entity.getProperty(CompanyEntity.LOCATION)))
//            .withContactFace(String.valueOf(entity.getProperty(CompanyEntity.CONTACT_FACE)))
//            .withAddress(String.valueOf(entity.getProperty(CompanyEntity.ADDRESS)))
//            .withEmail(String.valueOf(entity.getProperty(CompanyEntity.EMAIL)))
//            .withPhoneNumber(String.valueOf(entity.getProperty(CompanyEntity.PHONE_NUMBER)))
//            .withDescription(String.valueOf(entity.getProperty(CompanyEntity.DESCRIPTION)))
//            .withImageURL(String.valueOf(entity.getProperty(CompanyEntity.LOGO_URL)))
//            .withFacebook(String.valueOf(entity.getProperty(CompanyEntity.FACEBOOK)))
//            .withGooglePlus(String.valueOf(entity.getProperty(CompanyEntity.GOOGLE_PLUS)))
//            .withTwitter(String.valueOf(entity.getProperty(CompanyEntity.TWITTER)))
//            .withUserId(Long.valueOf(String.valueOf(entity.getProperty(CompanyEntity.USER_ID))))
//            .build();
    return null;
  }
}