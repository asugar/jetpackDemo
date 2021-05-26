package com.yi.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {

    public static void main(String[] args) {
//        System.out.println("main fid= " + fib(20));
//        System.out.println("main fid2= " + fib2(20));
//        System.out.println("main fid3= " + fib3(20));


        System.out.println("main numWays2= " + numWays3(10));
    }

    /**
     * 最长递增子序列
     */
    static int maxLen(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //初始化就是边界情况
        dp[0] = 1;
        int maxans = 1;
        //自底向上遍历
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            //从下标0到i遍历
            for (int j = 0; j < i; j++) {
                //找到前面比nums[i]小的数nums[j],即有dp[i]= dp[j]+1
                if (nums[j] < nums[i]) {
                    //因为会有多个小于nums[i]的数，也就是会存在多种组合了嘛，我们就取最大放到dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //求出dp[i]后，dp最大那个就是nums的最长递增子序列啦
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }


    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 10 级的台阶总共有多少种跳法
     * 有重复计算
     */
    static int numWays(int n) {
        if (n <= 2) {
            return n;
        }
        return numWays(n - 1) + numWays(n - 2);
    }

    /**
     * 减少重复计算
     * 从上到下的方式
     */
    static int numWays2(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        if (n <= 2) {
            return n;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int temp = numWays(n);
            map.put(n, temp);
            return temp;
        }
    }

    /**
     * 从下到上，只需要记录上次的值
     */
    static int numWays3(int n) {
        if (n <= 2) {
            return n;
        }
        int last2 = 1;
        int last = 2;
        for (int i = 3; i <= n; i++) {
            int temp = last + last2;
            last2 = last;
            last = temp;
        }
        return last;
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
