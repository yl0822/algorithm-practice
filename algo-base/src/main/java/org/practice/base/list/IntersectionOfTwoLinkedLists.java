package org.practice.base.list;

import org.practice.base.data.ListNode;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 链接：https://leetcode.cn/problems/intersection-of-two-linked-lists/
 *
 * @author feikong
 * @version 2022/6/28
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        System.out.println();
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
