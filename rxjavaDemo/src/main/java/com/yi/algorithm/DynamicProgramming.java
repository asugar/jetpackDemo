package com.yi.algorithm;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {

    public static void main(String[] args) {
        System.out.println("main fid= " + fib(20));
        System.out.println("main fid2= " + fib2(20));
        System.out.println("main fid3= " + fib3(20));
    }

    /**
     * 斐波那契数列
     * 1 1 1
     */
    static int fib(int N) {
        if (N == 1 || N == 2) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 备忘录记录，时间复杂度n
     */
    static int fib2(int n) {
        if (n < 1) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        return helper(map, n);
    }

    static int helper(Map<Integer, Integer> map, int n) {
        if (n == 1 || n == 2) return 1;
        if (map.get(n) != null) {
            return map.get(n);
        } else {
            map.put(n, helper(map, n - 1) + helper(map, n - 2));
            return map.get(n);
        }
    }

    /**
     * 数组的迭代
     */
    static int fib3(int n) {
        int[] db = new int[n + 1];
        db[1] = db[2] = 1;
        for (int i = 3; i <= n; i++) {
            db[i] = db[i - 1] + db[i - 2];
        }
        return db[n];
    }

}
