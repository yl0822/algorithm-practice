package org.practice.base.data;

/**
 * @author feikong
 * @version 2022/6/17
 */
public class DataMocker {

    public static TreeNode mockTree(){
        return new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
    }

    public static ListNode mockList(){
        return ListNode.init(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
