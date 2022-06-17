package org.practice.base.tree;

import org.practice.base.data.DataMocker;
import org.practice.base.data.TreeNode;

import java.util.List;

import static org.practice.base.tree.InorderTraversal.inorderTraversal;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * https://leetcode.cn/problems/symmetric-tree/
 *
 * @author feikong
 * @version 2022/6/17
 */
public class SymmetricTree {

    public static void main(String[] args) {
        System.out.println(isSymmetric(DataMocker.mockTree()));
        System.out.println(isSymmetric(new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)))));
        System.out.println(isSymmetric(new TreeNode(1,
                new TreeNode(2, new TreeNode(2), null),
                new TreeNode(2, new TreeNode(2), null))));
        System.out.println(isSymmetric(new TreeNode(1,
                new TreeNode(2, new TreeNode(2), null),
                new TreeNode(2, new TreeNode(2), null))));
    }

    public static boolean isSymmetric(TreeNode root){
        List<Integer> list1 = inorderTraversal(root.left);
        List<Integer> list2 = inorderTraversal(root.right);
        if (list1.size() != list2.size()){
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(list2.size() - 1 - i))){
                return false;
            }
        }
        return true;
    }
}
