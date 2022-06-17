package org.practice.base.tree;

import org.practice.base.data.TreeNode;

/**
 * 数组转换成平衡二叉树
 *
 * @author feikong
 * @version 2022/6/10
 */
public class SortedArrayToBT {

    public static void main(String[] args) {
//        System.out.println(sortedArrayToBT(new int[]{1,2,3,4,5,6,7,8,9,10,-1,-1,11,-1,12}));
        System.out.println(ceilingPowerOfTwo(31));
        System.out.println(ceilingPowerOfTwo(33));
        System.out.println(ceilingPowerOfTwo(1000));
    }


    private static TreeNode sortedArrayToBT(int[] nums) {
        int[] ints = new int[ceilingPowerOfTwo(nums.length)];
        for (int i = 0; i < ceilingPowerOfTwo(nums.length); i++) {
            if (i < nums.length){
                ints[i] = nums[i];
            }else {
                ints[i] = -1;
            }
        }
        return null;
    }

    public static int ceilingPowerOfTwo(int x){
        return 1 << 32 - Integer.numberOfLeadingZeros(x - 1);
    }
}
