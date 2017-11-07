package com.carpool.visitormanagement.logic.impl.usecase;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import com.carpool.general.logic.api.UseCase;
import com.carpool.visitormanagement.dataaccess.api.VisitorEntity;
import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.usecase.UcFindVisitor;
import com.carpool.visitormanagement.logic.base.usecase.AbstractVisitorUc;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Use case implementation for searching, filtering and getting Visitors
 */
@Named
@UseCase
@Validated
public class UcFindVisitorImpl extends AbstractVisitorUc implements UcFindVisitor {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindVisitorImpl.class);

  @Override
  public VisitorEto findVisitor(Long id) {

    LOG.debug("Get Visitor with id {} from database.", id);
    return getBeanMapper().map(getVisitorDao().findOne(id), VisitorEto.class);
  }

  @Override
  public PaginatedListTo<VisitorEto> findVisitorEtos(VisitorSearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<VisitorEntity> visitors = getVisitorDao().findVisitors(criteria);
    return mapPaginatedEntityList(visitors, VisitorEto.class);
  }

}
