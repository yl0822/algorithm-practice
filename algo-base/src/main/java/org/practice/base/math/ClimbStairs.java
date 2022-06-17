package org.practice.base.math;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * https://leetcode.cn/problems/climbing-stairs/
 *
 * @author feikong
 * @version 2022/6/16
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    private static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
