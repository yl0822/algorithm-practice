package org.practice.base.scene;

/**
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 链接：https://leetcode.cn/problems/majority-element
 *
 * @author feikong
 * @version 2022/6/29
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 3, 3}));
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(majorityElement(new int[]{2, 3, 1, 1, 1, 2, 2, 2, 2}));
    }

    private static int majorityElement(int[] nums) {
        int base = nums[0];
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0){
                base = nums[i];
            }
            if (base == nums[i]){
                cnt++;
            }else {
                cnt--;
            }
        }
        return base;
    }
}
