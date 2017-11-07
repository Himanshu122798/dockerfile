package com.carpool.visitormanagement.dataaccess.api.dao;

import com.carpool.general.dataaccess.api.dao.ApplicationDao;
import com.carpool.visitormanagement.dataaccess.api.VisitorEntity;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Data access interface for Visitor entities
 */
public interface VisitorDao extends ApplicationDao<VisitorEntity> {

  /**
   * Finds the {@link VisitorEntity visitors} matching the given {@link VisitorSearchCriteriaTo}.
   *
   * @param criteria is the {@link VisitorSearchCriteriaTo}.
   * @return the {@link PaginatedListTo} with the matching {@link VisitorEntity} objects.
   */
  PaginatedListTo<VisitorEntity> findVisitors(VisitorSearchCriteriaTo criteria);
}
