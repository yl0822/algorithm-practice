package org.practice.base.math;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * https://leetcode.cn/problems/hamming-distance/
 *
 * @author feikong
 * @version 2022/6/24
 */
public class HammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
        System.out.println(hammingDistance(3, 1));
        // 1011 >> 1 -> 0101=5
        System.out.println(11 >> 1);
        // 1011 >> 1 -> 0010=2
        System.out.println(11 >> 2);
        // 0011 << 1 -> 0110=6
        System.out.println(3 << 1);
        // 0011 << 2 -> 1100=12
        System.out.println(3 << 2);
    }

    private static int hammingDistance(int x, int y) {
        int count = 0;
        for (int j = 0; j < 32; j++) {
            // 每一次迭代可以判断n的二进制最后一位是0还是1，>>是右移，32次后n=0，
            // &是位与，1&1=1，其他几种情况都是0，所以和1位与，也就是和00000...001位与，除了最后一位有意义，其他位都是0
            if ((((x ^ y) >> j) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}
