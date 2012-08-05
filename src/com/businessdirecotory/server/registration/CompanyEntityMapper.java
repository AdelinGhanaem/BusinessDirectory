package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.Entity;

public class CompanyEntityMapper {

  private CompanyBuilder builder = new CompanyBuilder();

  public CompanyEntityMapper() {
  }

  Entity createEntity(Company company, Entity companyEntity) {
    companyEntity.setProperty(CompanyEntity.NAME, company.getName());
    companyEntity.setProperty(CompanyEntity.ADDRESS, company.getAddress());
    companyEntity.setProperty(CompanyEntity.ACTIVITY, company.getActivity());
    companyEntity.setProperty(CompanyEntity.LOCATION, company.getLocation());
    companyEntity.setProperty(CompanyEntity.EMAIL, company.getEmail());
    companyEntity.setProperty(CompanyEntity.CONTACT_FACE, company.getContactFace());
    companyEntity.setProperty(CompanyEntity.PHONE_NUMBER, company.getPhoneNumber());
    companyEntity.setProperty(CompanyEntity.DESCRIPTION, company.getDescription());
    companyEntity.setProperty(CompanyEntity.LOGO_URL, company.getLogoURL());
    companyEntity.setProperty(CompanyEntity.FACEBOOK, company.getFacebook());
    companyEntity.setProperty(CompanyEntity.GOOGLE_PLUS, company.getGooglePlus());
    companyEntity.setProperty(CompanyEntity.TWITTER, company.getGooglePlus());
    companyEntity.setProperty(CompanyEntity.USER_ID, company.getUserId());

    return companyEntity;
  }

  Company create(Entity entity) {
    Company company = builder.withId(entity.getKey().getId()).withName(String.valueOf(entity.getProperty(CompanyEntity.NAME)))
            .withActivity(String.valueOf(entity.getProperty(CompanyEntity.ACTIVITY)))
            .withLocation(String.valueOf(entity.getProperty(CompanyEntity.LOCATION)))
            .withContactFace(String.valueOf(entity.getProperty(CompanyEntity.CONTACT_FACE)))
            .withAddress(String.valueOf(entity.getProperty(CompanyEntity.ADDRESS)))
            .withEmail(String.valueOf(entity.getProperty(CompanyEntity.EMAIL)))
            .withPhoneNumber(String.valueOf(entity.getProperty(CompanyEntity.PHONE_NUMBER)))
            .withDescription(String.valueOf(entity.getProperty(CompanyEntity.DESCRIPTION)))
            .withImageURL(String.valueOf(entity.getProperty(CompanyEntity.LOGO_URL)))
            .withFacebook(String.valueOf(entity.getProperty(CompanyEntity.FACEBOOK)))
            .withGooglePlus(String.valueOf(entity.getProperty(CompanyEntity.GOOGLE_PLUS)))
            .withTwitter(String.valueOf(entity.getProperty(CompanyEntity.TWITTER)))
            .withUserId(Long.valueOf(String.valueOf(entity.getProperty(CompanyEntity.USER_ID))))
            .build();
    return company;
  }
}