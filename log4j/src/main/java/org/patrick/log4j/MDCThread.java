/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.patrick.log4j;

import org.apache.log4j.Logger;

public class MDCThread implements Runnable {
    public static void main(String[] args) {
        new MDCThread().run();
    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger("MyLogger");

        // NDC.push("AAA");

        logger.info(Thread.currentThread().getName() + " --- Test Me");

        logger.debug(Thread.currentThread().getName() + " --- TestTestTestTestTestTestTestTestTestTestTest Me");

    }

}
