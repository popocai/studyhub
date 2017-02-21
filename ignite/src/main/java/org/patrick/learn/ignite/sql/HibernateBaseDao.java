package org.patrick.learn.ignite.sql;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateBaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdateObj(final Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    public void saveObj(final Object obj) {
        getCurrentSession().save(obj);
    }

    public void updateObj(final Object obj) {
        getCurrentSession().merge(obj);
    }

    public <T> void saveOrUpdateSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.saveOrUpdate(bean);
        }
    }

    public <T> void saveSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.save(bean);
        }
    }

    public <T> void updateSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.update(bean);
        }
    }

    public <T> void saveOrUpdateList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.saveOrUpdate(bean);
        }
    }

    public <T> void saveList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.save(bean);
        }
    }

    public <T> void updateList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.update(bean);
        }
    }

}
