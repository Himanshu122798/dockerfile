package com.carpool.visitormanagement.logic.api.usecase;

import java.util.List;

import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

public interface UcFindVisitor {

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

}
