package org.practice.base.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
 *
 * @author feikong
 * @version 2022/8/24
 */
public class LevelOrder {

    public static void main(String[] args) {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3, Arrays.asList(new Node(6), new Node(7,
                        Arrays.asList(new Node(11, Arrays.asList(new Node(14)))))))
                , new Node(4, Arrays.asList(new Node(8, Arrays.asList(new Node(12))))), new Node(5,
                        Arrays.asList(new Node(9, Arrays.asList(new Node(13))), new Node(10)))));
        for (Integer list : levelOrder(root)) {
            System.out.print(list + "    ");
        }
    }

    public static List<Integer> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>(8);
        }
        List<Integer> ans = new ArrayList<>(8);
        Queue<Node> queue = new ArrayDeque<>(8);
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; ++i) {
                Node cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                ans.add(cur.val);
                if (cur.children == null) {
                    continue;
                }
                for (Node child : cur.children) {
                    queue.offer(child);
                }
            }
        }
        return ans;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
