package com.carpool.general.logic.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.mmm.util.entity.api.GenericEntity;
import net.sf.mmm.util.entity.api.PersistenceEntity;
import net.sf.mmm.util.transferobject.api.AbstractTransferObject;
import net.sf.mmm.util.transferobject.api.TransferObject;

import com.carpool.general.common.base.AbstractBeanMapperSupport;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Abstract base class for any <em>use case</em> in this application. Actual implementations need to be annotated with
 * {@link javax.inject.Named} and {@link com.carpool.general.logic.api.UseCase}.
 *
 */
public abstract class AbstractUc extends AbstractBeanMapperSupport {

  /**
   * The limit for {@link net.sf.mmm.util.search.base.AbstractSearchCriteria#getMaximumHitCount() maximum hit count} for
   * UI requests.
   */
  protected static final int MAXIMUM_HIT_LIMIT = 500;

  /**
   * The constructor.
   */
  public AbstractUc() {

    super();
  }

  /**
   * Maps a {@link PaginatedListTo paginated list} of persistent entities to a {@link PaginatedListTo paginated list} of
   * transfer objects.
   *
   * @param <T> is the generic type of the {@link AbstractTransferObject transfer object}.
   * @param <E> is the generic type of the {@link PersistenceEntity entity}.
   * @param paginatedList is the paginated list to map from.
   * @param klass is the target class to map the paginated entities to.
   * @return a {@link PaginatedListTo paginated list of entity transfer objects}.
   */
  protected <T extends TransferObject, E extends PersistenceEntity<?>> PaginatedListTo<T> mapPaginatedEntityList(
      PaginatedListTo<E> paginatedList, Class<T> klass) {

    List<T> etoList = getBeanMapper().mapList(paginatedList.getResult(), klass);
    PaginatedListTo<T> result = new PaginatedListTo<>(etoList, paginatedList.getPagination());

    return result;
  }

  /**
   * Creates a {@link Map} with all {@link GenericEntity entities} from the given {@link Collection} using their
   * {@link GenericEntity#getId() ID} as key. All {@link GenericEntity entities} without an {@link GenericEntity#getId()
   * ID} ({@code null}) will be ignored.
   *
   * @param <ID> is the generic type of the {@link GenericEntity#getId() ID}.
   * @param <E> is the generic type of the {@link GenericEntity entity}.
   * @param entities is the {@link Collection} of {@link GenericEntity entities}.
   * @return a {@link Map} mapping from {@link GenericEntity#getId() ID} to {@link GenericEntity entity}.
   */
  protected static <ID, E extends GenericEntity<ID>> Map<ID, E> getEntityMap(Collection<E> entities) {

    Map<ID, E> id2EntityMap = new HashMap<>();
    for (E entity : entities) {
      ID id = entity.getId();
      if (id != null) {
        id2EntityMap.put(id, entity);
      }
    }
    return id2EntityMap;
  }

  /**
   * Determines the {@link GenericEntity entities} to delete if <code>currentList</code> is the current list from the
   * persistence and <code>listToSave</code> is the new list that shall be saved. In other words this method selects the
   * {@link GenericEntity entities} from <code>currentList</code> that are not contained in <code>listToSave</code>.
   *
   * @param <ID> is the generic type of the {@link GenericEntity#getId() ID}.
   * @param <E> is the generic type of the {@link GenericEntity entity}.
   * @param currentEntities is the {@link Collection} of the {@link GenericEntity entities} currently persisted. We
   *        assume that all objects in this list have an {@link GenericEntity#getId() ID} value (that is not
   *        {@code null}).
   * @param entitiesToSave is the {@link Collection} that contains the {@link GenericEntity entities} that shall be
   *        saved. It may contain {@link GenericEntity entities} that have no {@link GenericEntity#getId() ID} that
   *        shall be newly created.
   * @return the {@link List} with the {@link GenericEntity entities} to delete.
   */
  protected static <ID, E extends GenericEntity<ID>> List<E> getEntities2Delete(Collection<E> currentEntities,
      Collection<E> entitiesToSave) {

    List<E> result = new ArrayList<>(currentEntities.size());
    Map<ID, E> entityMap = getEntityMap(entitiesToSave);
    for (E entity : currentEntities) {
      if (!entityMap.containsKey(entity.getId())) {
        // entity from currentList is not contained in listToSave...
        result.add(entity);
      }
    }
    return result;
  }

}
