package com.lidl.sourcecode.spring_aop_proxy;

public class PerformanceUtil {

    private static long start = 0;
    private static long end = 0;

    public static void startPerformance() {
        start = System.currentTimeMillis();
    }

    public static void endPerformance() {
        end = System.currentTimeMillis();
        System.out.println("method use:" + (end - start));
    }
}