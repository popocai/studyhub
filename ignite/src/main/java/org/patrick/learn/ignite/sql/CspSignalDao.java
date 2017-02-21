package org.patrick.learn.ignite.sql;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CspSignalDao {
    public List<CspSignal> queryAll();


}
