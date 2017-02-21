/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class MDCTest {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLogger");

        MDC.put("KEY-1", "key 1");

        logger.info("Test Me");

        logger.debug("TestTestTestTestTestTestTestTestTestTestTest Me");

        // NDC.clear();

        org.patrick.log4j.NDCThread.main(args);

        new Thread(new org.patrick.log4j.NDCThread()).start();

    }

}
