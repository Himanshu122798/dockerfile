package com.carpool.visitormanagement.dataaccess.api.dao;

import com.carpool.general.dataaccess.api.dao.ApplicationDao;
import com.carpool.visitormanagement.dataaccess.api.AccessCodeEntity;
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Data access interface for AccessCode entities
 */
public interface AccessCodeDao extends ApplicationDao<AccessCodeEntity> {

  /**
   * Finds the {@link AccessCodeEntity accesscodes} matching the given {@link AccessCodeSearchCriteriaTo}.
   *
   * @param criteria is the {@link AccessCodeSearchCriteriaTo}.
   * @return the {@link PaginatedListTo} with the matching {@link AccessCodeEntity} objects.
   */
  PaginatedListTo<AccessCodeEntity> findAccessCodes(AccessCodeSearchCriteriaTo criteria);
}
