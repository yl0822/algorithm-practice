package org.practice.base.list;

import org.practice.base.data.ListNode;

/**
 * 反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
 *
 * @author feikong
 * @version 2022/6/10
 */
public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(listNode);
        System.out.println("迭代法：");
        System.out.println(reverseListNode(listNode));
        ListNode listNode2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println("递归法：");
        System.out.println(reverseListNode2(listNode2));
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

    /**
     * 首先考虑 1 -> 2 -> 3 -> 4，递归方案肯定考虑是把234反转之后如何处理第一个节点。
     * 也就是 1 -> 2 <- 3 <- 4，我们显然要考虑将1 -> 2转化为1 <- 2，要考虑如何拿到2。
     * 因为234反转后的结果应该是4的指针，通常我们就会考虑从4开始回头找2的指针。
     * 但其实有更简单的办法，那就是1，1的next是啥，其实就是2，所以这里最关键一步就是把1的next(也就是2)的next指向1自己。
     * */
    private static ListNode reverseListNode2(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode ret = reverseListNode2(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
