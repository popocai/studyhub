/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.pat.jodd;

import java.util.Iterator;

import jodd.props.Props;
import jodd.props.PropsEntry;

public class JoddTest {
    public static void main(String[] args) throws Exception {
        Props props = new Props();

        props.load(JoddTest.class.getClassLoader().getResourceAsStream("test.properties"));

        System.out.println(props.getBaseValue("test"));
        System.out.println(props.getValue("test"));

        System.out.println(props.getValue("s1.test"));

        System.out.println(props.getValue("test", "dev"));

        System.out.println("###############");

        Iterator<PropsEntry> iterator = props.iterator();
        while (iterator.hasNext()) {
            PropsEntry entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("###############");

        // String[] allActiveProfile = props.getActiveProfiles();
        String[] allProfile = props.getAllProfiles();
        String[] profiles = props.getProfilesFor("nop");

        System.out.println("!!!!!!!!!");
        // for (String s : allActiveProfile) {
        // System.out.println(s);
        // }
        System.out.println("!!!!!!");

        for (String s : allProfile) {
            System.out.println(s);
        }
        System.out.println("!!!!!!");

        for (String s : profiles) {
            System.out.println(s);
        }
        System.out.println("!!!!!!");

        System.out.println(props.getValue("test", props.getAllProfiles()));

    }
}
