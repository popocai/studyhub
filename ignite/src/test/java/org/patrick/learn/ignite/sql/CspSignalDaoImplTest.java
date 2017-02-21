/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.patrick.learn.ignite.sql;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CspSignalDaoImplTest {

    @Test
    public void queryAllTest() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath:applicationContext-bean.xml", "applicationContext-data.xml" });

        CspSignalDao cspSignalDao = appContext.getBean(CspSignalDao.class);

        System.out.println(cspSignalDao.queryAll().size());
    }



}
