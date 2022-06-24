package org.practice.base.math;

import org.practice.base.data.DataPrinter;
import org.practice.base.sort.QuickSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 链接：https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array
 *
 * @author feikong
 * @version 2022/6/24
 */
public class FindDisappearedNumbers {

    public static void main(String[] args) {
        findDisappearedNumbers(new int[]{4,3,3,3}).forEach(System.out::println);
        findDisappearedNumbers(new int[]{1,2,2,3,3,4,6,7}).forEach(System.out::println);
    }

    private static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (set.add(i)){
                ret.add(i);
            }
        }
        return ret;
    }
}
