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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CombineTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("com.ericsson");

        MDC.put("KEY-1", "key 1");

        // System.out.println(NDC.getDepth());

        logger.info("Test Me");

        logger.debug("TestTestTestTestTestTestTestTestTestTestTest Me");

        // NDC.clear();

        // org.patrick.log4j.NDCThread.main(args);

        // new Thread(new org.patrick.log4j.NDCThread()).start();

    }

}
