package org.practice.base.math;

import org.practice.base.data.DataPrinter;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * <p>
 * https://leetcode.cn/problems/counting-bits/
 *
 * @author feikong
 * @version 2022/6/24
 */
public class CountBits {

    public static void main(String[] args) {
        DataPrinter.printArray(countBits(5));
    }

    private static int[] countBits(int n) {
        int[] ints = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            for (int j = 0; j < 32; j++) {
                // 每一次迭代可以判断n的二进制最后一位是0还是1，>>是右移，32次后n=0，
                // &是位与，1&1=1，其他几种情况都是0，所以和1位与，也就是和00000...001位与，除了最后一位有意义，其他位都是0
                if (((i >> j) & 1) == 1) {
                    count++;
                }
            }
            ints[i] = count;
        }
        return ints;
    }
}
