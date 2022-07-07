package org.practice.base.tree;

import org.practice.base.data.DataMocker;
import org.practice.base.data.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 *
 * @author feikong
 * @version 2022/7/7
 */
public class DiameterOfBinaryTree {
    int max = 0;

    public static void main(String[] args) {
        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(DataMocker.mockTree()));
    }

    private int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        //将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
        max = Math.max(left + right, max);
        //返回节点深度
        return Math.max(left, right) + 1;
    }
}
