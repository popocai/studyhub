/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/

public class Happy extends Thread {
    final StringBuffer sb1 = new StringBuffer();

    final StringBuffer sb2 = new StringBuffer();

    public static void main(String args[]) {
        final Happy h = new Happy();

        new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    h.sb1.append("A");
                    h.sb2.append("B");
                    System.out.print(h.sb1);
                    System.out.print(h.sb2);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    h.sb1.append("D");
                    h.sb2.append("C");
                    System.out.print(h.sb2);
                    System.out.print(h.sb1);
                }
            }
        }.start();
    }
}

