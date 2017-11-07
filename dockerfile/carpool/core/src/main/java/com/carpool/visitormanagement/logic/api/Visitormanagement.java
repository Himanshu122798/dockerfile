package com.carpool.visitormanagement.logic.api;

import com.carpool.visitormanagement.logic.api.to.AccessCodeEto;
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.usecase.UcFindVisitor;
import com.carpool.visitormanagement.logic.api.usecase.UcManageVisitor;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Interface for Visitormanagement component.
 */
public interface Visitormanagement extends UcFindVisitor, UcManageVisitor {

  /**
   * Returns a Visitor by its id 'id'.
   *
   * @param id The id 'id' of the Visitor.
   * @return The {@link VisitorEto} with id 'id'
   */
  VisitorEto findVisitor(Long id);

  /**
   * Returns a paginated list of Visitors matching the search criteria.
   *
   * @param criteria the {@link VisitorSearchCriteriaTo}.
   * @return the {@link List} of matching {@link VisitorEto}s.
   */
  PaginatedListTo<VisitorEto> findVisitorEtos(VisitorSearchCriteriaTo criteria);

  /**
   * Deletes a visitor from the database by its id 'visitorId'.
   *
   * @param visitorId Id of the visitor to delete
   * @return boolean <code>true</code> if the visitor can be deleted, <code>false</code> otherwise
   */
  boolean deleteVisitor(Long visitorId);

  /**
   * Saves a visitor and store it in the database.
   *
   * @param visitor the {@link VisitorEto} to create.
   * @return the new {@link VisitorEto} that has been saved with ID and version.
   */
  VisitorEto saveVisitor(VisitorEto visitor);

  /**
   * Returns a AccessCode by its id 'id'.
   *
   * @param id The id 'id' of the AccessCode.
   * @return The {@link AccessCodeEto} with id 'id'
   */
  AccessCodeEto findAccessCode(Long id);

  /**
   * Returns a paginated list of AccessCodes matching the search criteria.
   *
   * @param criteria the {@link AccessCodeSearchCriteriaTo}.
   * @return the {@link List} of matching {@link AccessCodeEto}s.
   */
  PaginatedListTo<AccessCodeEto> findAccessCodeEtos(AccessCodeSearchCriteriaTo criteria);

  /**
   * Deletes a accessCode from the database by its id 'accessCodeId'.
   *
   * @param accessCodeId Id of the accessCode to delete
   * @return boolean <code>true</code> if the accessCode can be deleted, <code>false</code> otherwise
   */
  boolean deleteAccessCode(Long accessCodeId);

  /**
   * Saves a accessCode and store it in the database.
   *
   * @param accessCode the {@link AccessCodeEto} to create.
   * @return the new {@link AccessCodeEto} that has been saved with ID and version.
   */
  AccessCodeEto saveAccessCode(AccessCodeEto accessCode);

}
