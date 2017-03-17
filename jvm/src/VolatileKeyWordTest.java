/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/

public class VolatileKeyWordTest extends Thread {
    private volatile
    boolean stop = false;

    public void stopMe() {
        stop = true;
    }

    @Override
    public void run() {
        int i = 0;
        while (!stop) {
            i++;
        }
        System.out.println("I'm stopped : " + i);
    }

    public static void main(String[] args) throws Exception {
        VolatileKeyWordTest t = new VolatileKeyWordTest();

        t.start();
        Thread.sleep(1000);
        t.stopMe();

        Thread.sleep(100000);

    }

}
