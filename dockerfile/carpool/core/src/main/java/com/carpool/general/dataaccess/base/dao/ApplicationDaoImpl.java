package com.carpool.general.dataaccess.base.dao;

import org.springframework.stereotype.Repository;

import com.carpool.general.dataaccess.api.dao.ApplicationDao;

import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity;
import io.oasp.module.jpa.dataaccess.base.AbstractRevisionedDao;

/**
 * This is the abstract base implementation of {@link ApplicationDao}.
 *
 * @param <ENTITY> is the {@link #getEntityClass() type} of the managed entity.
 *
 */
@Repository
public abstract class ApplicationDaoImpl<ENTITY extends MutablePersistenceEntity<Long>>
    extends AbstractRevisionedDao<ENTITY> implements ApplicationDao<ENTITY> {

  /**
   * The constructor.
   */
  public ApplicationDaoImpl() {

    super();
  }

}
