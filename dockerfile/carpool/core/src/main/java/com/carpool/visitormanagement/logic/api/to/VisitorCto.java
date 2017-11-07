package com.carpool.visitormanagement.logic.api.to;

import com.carpool.general.common.api.to.AbstractCto;

/**
 * Composite transport object of Visitor
 */
public class VisitorCto extends AbstractCto {

  private static final long serialVersionUID = 1L;

  private VisitorEto visitor;

  private AccessCodeEto code;

  public VisitorEto getVisitor() {

    return visitor;
  }

  public void setVisitor(VisitorEto visitor) {

    this.visitor = visitor;
  }

  public AccessCodeEto getCode() {

    return code;
  }

  public void setCode(AccessCodeEto code) {

    this.code = code;
  }

}
