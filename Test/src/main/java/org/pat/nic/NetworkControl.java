/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.pat.nic;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkControl {
    public static void main(String[] args) throws Exception {
        Enumeration<NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.getName().startsWith("eth")) {
                System.out.println(networkInterface.getName());
                System.out.println(networkInterface.getDisplayName());
                System.out.println(networkInterface.getParent());
                System.out.println("----------------");
            }

        }
    }
}
