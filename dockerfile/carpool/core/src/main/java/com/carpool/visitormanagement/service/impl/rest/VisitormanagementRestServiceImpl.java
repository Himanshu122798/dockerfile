package com.carpool.visitormanagement.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import com.carpool.visitormanagement.logic.api.Visitormanagement;
import com.carpool.visitormanagement.logic.api.to.AccessCodeEto;
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.carpool.visitormanagement.service.api.rest.VisitormanagementRestService;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * The service implementation for REST calls in order to execute the logic of component {@link Visitormanagement}.
 */
@Named("VisitormanagementRestService")
public class VisitormanagementRestServiceImpl implements VisitormanagementRestService {

  @Inject
  private Visitormanagement visitormanagement;

  @Override
  public VisitorEto getVisitor(long id) {

    return this.visitormanagement.findVisitor(id);
  }

  @Override
  public VisitorEto saveVisitor(VisitorEto visitor) {

    return this.visitormanagement.saveVisitor(visitor);
  }

  @Override
  public void deleteVisitor(long id) {

    this.visitormanagement.deleteVisitor(id);
  }

  @Override
  public PaginatedListTo<VisitorEto> findVisitorsByPost(VisitorSearchCriteriaTo searchCriteriaTo) {

    return this.visitormanagement.findVisitorEtos(searchCriteriaTo);
  }

  @Override
  public AccessCodeEto getAccessCode(long id) {

    return this.visitormanagement.findAccessCode(id);
  }

  @Override
  public AccessCodeEto saveAccessCode(AccessCodeEto accesscode) {

    return this.visitormanagement.saveAccessCode(accesscode);
  }

  @Override
  public void deleteAccessCode(long id) {

    this.visitormanagement.deleteAccessCode(id);
  }

  @Override
  public PaginatedListTo<AccessCodeEto> findAccessCodesByPost(AccessCodeSearchCriteriaTo searchCriteriaTo) {

    return this.visitormanagement.findAccessCodeEtos(searchCriteriaTo);
  }

}
