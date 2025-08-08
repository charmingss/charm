package org.example.alg;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    /**
     * 分治算法：基本思想是将一个规模为N的问题分解为K个规模较小的子问题，这些子问题相互独立且与原问题性质相同。求出子问题的解，就可得到原问题的解
     * <p>
     * 下面一个例题是给一个表达式加括号，返回所有可能的解
     * 示例 1：
     * <p>
     * 输入：expression = "2-1-1"
     * 输出：[0,2]
     * 解释：
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     * 示例 2：
     * <p>
     * 输入：expression = "2*3-4*5"
     * 输出：[-34,-14,-10,-10,10]
     * 解释：
     * (2*(3-(4*5))) = -34
     * ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10
     * (2*((3-4)*5)) = -10
     * (((2*3)-4)*5) = 10
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**
     * 爬楼梯
     * 动态规划算法：和分治算法类似，都是规模复杂的算法分割为多个子问题，然后求解子问题的解，最后将子问题的解组合成原问题的解，区别是动态规划会
     * 保存子问题的解，然后利用这些解来求解原问题。
     * 这个方法不好，会超时
     */
    public int click(int n) {
        if (n<=2){
            return n;
        }
        if (n>2){
            int count = click(n-1);
            int count2 = click(n-2);
            return count+count2;
        }
        return 0;
    }
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int pre2 = 1, pre1 = 2;
        for (int i = 2; i < n; i++) {
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
    public int houseRobber(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int pre2 =0;
        int pre1 =0;
        int pre3 = 0;
        for (int i = 0; i < arr.length; i++) {
            int cur = Math.max(pre2,pre3)+arr[i];
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return Math.max(pre2,pre1);
    }

    /**
     * 环形房屋抢劫问题
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //处理环形情况，将环形问题分解为两个线性子问题，分别排除首或尾元素。
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int first, int last) {
        int pre3 = 0, pre2 = 0, pre1 = 0;
        for (int i = first; i <= last; i++) {
            //动态规划核心逻辑
            int cur = Math.max(pre3, pre2) + nums[i];
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return Math.max(pre2, pre1);
    }
    public static long derangementOptimized(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;

        long a = 1; // D(0)
        long b = 0; // D(1)
        long c = 0;

        for (int i = 2; i <= n; i++) {
            c = (i - 1) * (a + b);
            a = b;
            b = c;
        }

        return b;
    }
    /**
     * 信封错排列问题
     * 递归方法
     * @param num
     * @return
     */
    public int derangementRecursive(int num) {
        if (num ==0){
            return 1;
        }
        if (num ==1){
            return 0;
        }

        return (num-1)*(derangementRecursive(num-1)+derangementRecursive(num-2));

    }

    public long derangementRecursive2(int num){
        //空集合视为一种排序
        if (num ==0){
            return 1;
        }
        //只有一个元素无法错位
        if (num ==1){
            return 0;
        }
        //必须+1以包含0~ num所有索引
         long[] dp = new long[num + 1];
         dp[0] = 1;
         dp[1] = 0;
         for (int i = 2; i <= num; i++) {
             dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
         }
         return  dp[num];
    }
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
    public int minPathSums(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
//        System.out.println(new Algorithm().diffWaysToCompute("2*3-4*5"));
//        System.out.println(new Algorithm().climbStairs(6));
//        System.out.println(new Algorithm().click(6));
        final int[] ints = {1,12, 3, 21,12,23,46};
        System.out.println(new Algorithm().houseRobber(ints));
    }
}


