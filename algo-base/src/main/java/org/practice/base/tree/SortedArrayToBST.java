package org.practice.base.tree;

import org.practice.base.data.TreeNode;

/**
 * 数组转换成平衡二叉树
 *
 * @author feikong
 * @version 2022/6/10
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        System.out.println(sortedArrayToBST(new int[]{1,2,3,4,5,6,7,8,9,10,-1,-1,11,-1,12}));
    }

    public static TreeNode sortedArrayToBST(int[] nums){
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // always choose left middle node as a root
        int p = (left + right) / 2;
        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = sortedArrayToBST(nums, left, p - 1);
        root.right = sortedArrayToBST(nums, p + 1, right);
        return root;
    }
}
