package org.practice.base.tree;

import org.practice.base.data.DataMocker;
import org.practice.base.data.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 *
 * @author feikong
 * @version 2022/6/17
 */
public class MaxDepth {

    public static void main(String[] args) {
//        System.out.println(maxDepth(DataMocker.mockTree()));
        System.out.println(maxDepth(new TreeNode(3, new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
    }

    /**
     * 树结构一般都是递归，数组一般都是迭代，链表两种都可能
     * 1. 空树为0层。
     * 2. 如果一个节点的左右节点都为空，认为是叶子节点，也就是1层。
     * 3. 如果一个节点的左节点为空，右节点非空，那么非空侧的高度肯定高于空侧。
     * 4. 每下溯一层，就有增加一层。
     * */
    private static int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }
}
