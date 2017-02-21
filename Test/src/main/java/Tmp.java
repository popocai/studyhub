/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/

public class Tmp {
    public static void main(String[] args) {
        int w = (int) 888.8;
        byte x = (byte) 1000L;
        long y = (byte) 100;
        byte z = (byte) 100L;


        System.out.println(w);
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);

        System.out.println(16 * 4);
        System.out.println(16 >> 2);
        System.out.println((16 / 2) ^ 2);
        System.out.println(16 >>> 2);

    }
}
