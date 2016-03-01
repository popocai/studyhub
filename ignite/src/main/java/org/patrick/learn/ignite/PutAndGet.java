/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.patrick.learn.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class PutAndGet {
    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");

            // Store keys in cache (values will end up on different cache nodes).
            // for (int i = 0; i < 10; i++)
            // cache.put(i, Integer.toString(i));

            for (int i = 0; i < 10; i++) {
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');
            }
        }
    }
}
