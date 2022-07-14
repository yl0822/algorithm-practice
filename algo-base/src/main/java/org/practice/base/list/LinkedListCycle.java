package org.practice.base.list;

import org.practice.base.data.ListNode;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 *
 * 链接：https://leetcode.cn/problems/linked-list-cycle
 *
 * @author feikong
 * @version 2022/6/17
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        System.out.println(hasCycle(n1));
    }

    /**
     * 有没有可能遍历不完但也永远不会相等？不会！在一个环形跑道上，博尔特跑再快也会再次经过你的。
     * 比如博尔特10m/s，你0.1/每秒，100m的环道上，当博尔特第一圈结束，你还在第一圈的1m处，所以博尔特可以在第二圈的0.1s之后追上你（不考虑芝诺悖论）
     * 如果你是9.9m/s，博尔特需要在第100圈左右才能追上你。注意相遇点不一定是入环点。
     * */
    private static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true){
            // 要么遍历完
            if (fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            // 要么会相等
            if (fast == slow) {
                return true;
            }
        }
    }

}
