package com.carpool.visitormanagement.logic.base.usecase;

import javax.inject.Inject;

import com.carpool.general.logic.base.AbstractUc;
import com.carpool.visitormanagement.dataaccess.api.dao.VisitorDao;

/**
 * Abstract use case for Visitors, which provides access to the commonly necessary data access objects.
 */
public class AbstractVisitorUc extends AbstractUc {

  /** @see #getVisitorDao() */
  @Inject
  private VisitorDao visitorDao;

  /**
   * Returns the field 'visitorDao'.
   * 
   * @return the {@link VisitorDao} instance.
   */
  public VisitorDao getVisitorDao() {

    return this.visitorDao;
  }

}
