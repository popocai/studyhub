/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package java8.newfeature.lambda;

import java.util.Arrays;

public class Lambda {
    public static void main(String[] args) {
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        Arrays.asList("a", "b", "d").forEach(e -> {
            char c = (char) (e.charAt(0) + 1);

            System.out.print(c);
        });

        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }
}
