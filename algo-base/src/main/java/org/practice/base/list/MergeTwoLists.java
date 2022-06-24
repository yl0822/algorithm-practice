package org.practice.base.list;


import org.practice.base.data.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 * @author feikong
 * @version 2022/6/16
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        System.out.println(mergeTwoLists(
                ListNode.init(1, 2, 5, 8),
                ListNode.init(3, 4, 6)
                ));
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    private static ListNode mergeTwoLists2(ListNode list1, ListNode list2){
        ListNode ret = new ListNode(0);
        ListNode cur = ret;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }

        }
        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }
        return ret.next;
    }
}
