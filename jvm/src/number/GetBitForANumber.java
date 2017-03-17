/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package number;

public class GetBitForANumber {
    public static void main(String[] args) {
        int a = 5;
        System.out.println(0x80000000);
        System.out.println(bitForInt(a));

        System.out.println(bitForInt(-9));
    }

    public static String bitForInt(int a) {
        String rtn = "";

        for (int i = 0; i < 32; i++) {
            int t = (a & (0x80000000 >>> i)) >>> (31 - i);
            rtn = rtn + t;
        }
        return rtn;
    }
}
