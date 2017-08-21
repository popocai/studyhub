/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/

/**
 * 栈分配
 * - 小对象(一般几十个bytes), 在没有逃逸(非线程私用)的情况下, 可以直接分配在栈上
 * - 直接分配在栈上, 可以自动回收, 减轻GC压力
 * - 大对象或者逃逸对象无法栈上分配
 */
public class AllocatOnStack {
    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }

        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    /*
     * 1. -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
     * 2. -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC
     */
}
