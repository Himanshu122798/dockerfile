package com.carpool.visitormanagement.logic.api.to;

import com.carpool.general.common.api.to.AbstractCto;

/**
 * Composite transport object of AccessCode
 */
public class AccessCodeCto extends AbstractCto {

  private static final long serialVersionUID = 1L;

  private AccessCodeEto accessCode;

  private VisitorEto visitor;

  public AccessCodeEto getAccessCode() {

    return accessCode;
  }

  public void setAccessCode(AccessCodeEto accessCode) {

    this.accessCode = accessCode;
  }

  public VisitorEto getVisitor() {

    return visitor;
  }

  public void setVisitor(VisitorEto visitor) {

    this.visitor = visitor;
  }

}
