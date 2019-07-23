package com.example.springcloud.common.oauth2server;

import java.math.BigInteger;

/**
 * @author jbj
 * @create 2019-05-17 9:25
 */
public class Test2 {

    public Test2() {}

    private static void Test2() {
        System.out.println("teste");
    }

    public static void main(String[] args) {

        int n = 500000;
        long t1 = useArrTime(n);
        System.out.println("t1:" + t1);
        System.out.println(temp(n));

        double d = 0.1234;
        Double t = 0.1123;

        Test2();
    }


    /**
     * 使用数组
     * @return
     */
    private static long useArrTime(int n) {
        // 定义数组方法
        long t1 = System.currentTimeMillis();
        long[] arr = new long[n];
        arr[0] = arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(arr[n - 1]);
        return System.currentTimeMillis() - t1;
    }

    private static long useDiGui(int n) {
        long t1 = System.currentTimeMillis();
        diGui(n);
        return System.currentTimeMillis() - t1;
    }

    /**
     * 使用递归
     * @param n
     * @return
     */
    private static int diGui(int n) {
        if (n < 0) {
            throw new RuntimeException("有异常");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return diGui(n - 2) + diGui(n - 1);
    }

    private static long temp(int n) {
        long t1 = System.currentTimeMillis();
        Long a = 1L, b = 1L, c = 0L;
        //因为前面还有两个1、1 所以i<=18
        for (int i = 1; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println(c);
        return System.currentTimeMillis() - t1;
    }
}
