package org.practice.base.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author feikong
 * @version 2022/6/15
 */
public class ThreeSum {

    public static void main(String[] args) {
        for (List<Integer> list : threeSum2(new int[]{-1, 0, 1, 2, -1, -4})) {
            for (Integer integer : list) {
                System.out.print(integer + ", ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                    if (nums[i] + nums[i1] + nums[i2] == 0){
                        List<Integer> idx = new ArrayList<>();
                        idx.add(i);
                        idx.add(i1);
                        idx.add(i2);
                        ret.add(idx);
                    }
                }
            }
        }
        return ret;
    }

    private static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            }else {
                List<Integer> idx = new ArrayList<>();
                idx.add(i);
                map.put(nums[i], idx);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                List<Integer> list = map.get(-nums[i] - nums[i1]);
                if (list != null){
                    for (Integer integer : list) {
                        if (integer == i || integer == i1){
                            continue;
                        }
                        List<Integer> li = new ArrayList<>();
                        li.add(i);
                        li.add(i1);
                        li.add(integer);
                        ret.add(li);
                    }
                }
            }
        }
        return ret;
    }
}
