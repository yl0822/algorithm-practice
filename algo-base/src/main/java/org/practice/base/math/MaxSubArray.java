package org.practice.base.math;

/**
 * 最大子序合
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author feikong
 * @version 2022/6/10
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-66, 11, 23, 56, 14, -144, 55, 15, -178, 54, 33}));
    }

    private static int maxSubArray(int[] nums){
        int base = nums[0];
        int sum = 0;
        for (int num : nums) {
            // 因为存在负数
            if (sum > 0){
                sum += num;
            } else {
                sum = num;
            }
            base = Math.max(base, sum);
        }
        return base;
    }
}
