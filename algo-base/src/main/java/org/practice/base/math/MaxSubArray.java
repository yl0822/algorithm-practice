package org.practice.base.math;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。(最大子序合)
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * @author feikong
 * @version 2022/6/10
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{1, -66, 11, 23, 56, 14, -144, 55, 15, -178, -199, 54, 33, 20}));
        System.out.println(maxSubArray(new int[]{-3, -2, -1}));
    }

    private static int maxSubArray(int[] nums){
        // 取第一个数为基准值
        int base = nums[0];
        // 初始化和
        int sum = 0;
        for (int num : nums) {
            if (sum > 0){
                // 正数对和最大肯定是正向的，所以直接加
                sum += num;
            } else {
                // 负数则重置和为该负数
                sum = num;
            }
            base = Math.max(base, sum);
        }
        return base;
    }
}
