package org.practice.base.tree;

import org.practice.base.data.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 *
 * @author feikong
 * @version 2022/6/17
 */
public class InorderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        System.out.print("前序遍历：");
        preorderTraversal(treeNode).forEach(System.out::print);
        System.out.println();
        System.out.print("中序遍历：");
        inorderTraversal(treeNode).forEach(System.out::print);
        System.out.println();
        System.out.print("后序遍历：");
        postorderTraversal(treeNode).forEach(System.out::print);
    }

    /**
     * 按照访问根节点-- 左子树 --右子树的方式遍历这棵树
     * */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * 按照访问左子树——根节点——右子树的方式遍历这棵树
     * */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    /**
     * 按照访问左子树——右子树 --根节点的方式遍历这棵树
     * */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private static void preorder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    private static void inorder(TreeNode root, List<Integer> list){
        if (root == null){
            list.add(-1);
            return;
        }
        if (root.left == null && root.right == null){
            list.add(root.val);
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private static void postorder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }
}
