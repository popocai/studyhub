package org.patrick.learn.ignite.sql;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class CspSignalDaoImpl extends HibernateBaseDao implements CspSignalDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<CspSignal> queryAll() {
        String hql = "from CspSignal";
        return this.getCurrentSession().createQuery(hql).list();
    }

}
