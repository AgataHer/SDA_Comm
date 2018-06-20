package com.adacademy.tweeter.dao;

import com.adacademy.tweeter.model.BaseEntity;
import com.sdacademy.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.*;

/**
 * Abstract DAO for database entities used in the system
 * @param <T> type of the stored object
 */
public abstract class AbstractDao<T extends BaseEntity> {

    protected abstract Class<T> getClazz();

    public Optional<T> add(final T entity) {
        Session session = HibernateUtils.getHibernateSession();
        try {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return Optional.of(entity);
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Method return single entity by the ID
     *
     * @param id the ID of the entity
     * @return Entity object
     */
    public Optional<T> get(final Long id) {
        Session session = HibernateUtils.getHibernateSession();
        try {
            T entity = session.get(getClazz(),id);
            return Optional.of(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Method returns all object stored in the database
     *
     * @return Collection of the objects
     */
    public Optional<List<T>> getAll() {
        Session session = HibernateUtils.getHibernateSession();
        try {
            List<T> entities = session.createQuery("FROM " + getClazz().getSimpleName()).list();
            return Optional.of(entities);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Method removes entity from the database
     *
     * @param entity the entity to remove
     * @return TRUE if removed successfully, FALSE otherwise
     */
    public boolean remove(final T entity) {
        if (entity == null) {
            return false;
        }
        Session session = HibernateUtils.getHibernateSession();
        try {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method updates entity in the memory
     *
     * @param entity the entity to update
     * @return the new entity, or EMPTY if any error occurred
     */
    public Optional<T> update(final T entity) {
        Session session = HibernateUtils.getHibernateSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return Optional.of(entity);
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

