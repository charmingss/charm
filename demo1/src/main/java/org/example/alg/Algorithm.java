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

    public static void main(String[] args) {
//        System.out.println(new Algorithm().diffWaysToCompute("2*3-4*5"));
//        System.out.println(new Algorithm().climbStairs(6));
//        System.out.println(new Algorithm().click(6));
        final int[] ints = {1,12, 3, 21,12};
        System.out.println(new Algorithm().houseRobber(ints));
    }
}


