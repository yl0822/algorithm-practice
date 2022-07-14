package org.practice.base.math;

/**
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现int sqrt(int x)函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 *
 * 链接：https://leetcode.cn/problems/jJ0w9p
 *
 * @author feikong
 * @version 2022/7/13
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(sqrt(2));
        System.out.println(sqrt(2022));
    }

    /**
     * xi = (xi + x / xi) / 2
     * */
    private static double sqrt(int x){
        double d = x;
        while (true) {
            double xi = 0.5 * (d + (double) x / d);
            if (Math.abs(d - xi) < 1e-7) {
                break;
            }
            d = xi;
        }
        return d;
    }
}
