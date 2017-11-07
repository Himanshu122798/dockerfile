package com.carpool.visitormanagement.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import com.carpool.general.logic.api.UseCase;
import com.carpool.visitormanagement.dataaccess.api.VisitorEntity;
import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.usecase.UcManageVisitor;
import com.carpool.visitormanagement.logic.base.usecase.AbstractVisitorUc;

/**
 * Use case implementation for modifying and deleting Visitors
 */
@Named
@UseCase
@Validated
public class UcManageVisitorImpl extends AbstractVisitorUc implements UcManageVisitor {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageVisitorImpl.class);

  @Override
  public boolean deleteVisitor(Long visitorId) {

    VisitorEntity visitor = getVisitorDao().find(visitorId);
    getVisitorDao().delete(visitor);
    LOG.debug("The visitor with id '{}' has been deleted.", visitorId);
    return true;
  }

  @Override
  public VisitorEto saveVisitor(VisitorEto visitor) {

    Objects.requireNonNull(visitor, "visitor");

    VisitorEntity visitorEntity = getBeanMapper().map(visitor, VisitorEntity.class);

    // initialize, validate visitorEntity here if necessary

    getVisitorDao().save(visitorEntity);
    LOG.debug("Visitor with id '{}' has been created.", visitorEntity.getId());
    return getBeanMapper().map(visitorEntity, VisitorEto.class);
  }
}
