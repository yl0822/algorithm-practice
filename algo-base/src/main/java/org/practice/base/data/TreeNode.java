package org.practice.base.data;

import java.util.List;

/**
 * @author feikong
 * @version 2022/6/10
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        inorder(this);
        return "" + val;
    }

    private static void inorder(TreeNode root){
        if (root == null){
            return;
        }
        System.out.println(root.val);
        inorder(root.left);
        inorder(root.right);
    }
}
