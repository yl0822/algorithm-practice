package org.practice.base.list;

import org.practice.base.data.ListNode;

/**
 * 反转链表
 *
 * @author feikong
 * @version 2022/6/10
 */
public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(listNode);
        System.out.println(reverseListNode(listNode));
    }

    /**
     * 三指针移动
     * */
    private static ListNode reverseListNode(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
