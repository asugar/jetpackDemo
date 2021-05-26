package com.yi.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println("---- main begin " + System.currentTimeMillis());
        long result = fibonacci(9);
        System.out.println("---- main end  " + System.currentTimeMillis() + " result= " + result);
    }

    private static Map<Integer, Long> cache = new HashMap<>();

    private static long fibonacci(int n) {
//        System.out.println("---- fibonacci n= " + n);
        if (cache.get(n) != null) {
            return cache.get(n);
        } else if (n == 0) {
            cache.put(n, 0L);
            return 0;
        } else if (n == 1) {
            cache.put(n, 1L);
            return 1;
        } else {
            long result = fibonacci(n - 1) + fibonacci(n - 2);
            cache.put(n, result);
            return result;
        }
    }
}
