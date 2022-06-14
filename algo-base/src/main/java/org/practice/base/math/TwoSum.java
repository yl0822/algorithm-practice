package org.practice.base.math;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * @author feikong
 * @version 2022/6/10
 */
public class TwoSum {

    public static void main(String[] args) {
        Arrays.stream(twoSum2(new int[]{2, 7, 11, 15}, 9)).forEach(System.out::print);
        System.out.println();
        Arrays.stream(twoSum2(new int[]{3,2,4}, 6)).forEach(System.out::print);
        System.out.println();
        Arrays.stream(twoSum2(new int[]{3,3}, 6)).forEach(System.out::print);
    }

    /**
     * 时间复杂度O(N)
     * 增加了空间复杂度
     * */
    private static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            idxMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer next = idxMap.get(target - nums[i]);
            if (next != null && next != i) {
                return new int[]{i, next};
            }
        }
        return null;
    }

    /**
     * 时间复杂度O(N平方)
     * 但没有额外增加空间成本
     * */
    private static int[] twoSum2(int[] nums, int target){
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
