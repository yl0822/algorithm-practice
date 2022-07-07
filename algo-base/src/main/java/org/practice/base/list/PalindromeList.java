package org.practice.base.list;

import org.practice.base.data.ListNode;

import java.util.Stack;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * https://leetcode.cn/problems/palindrome-linked-list/
 *
 * @author feikong
 * @version 2022/6/29
 */
public class PalindromeList {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNode.init(1, 2, 3, 3, 2, 1)));
        System.out.println(isPalindrome(ListNode.init(1, 2, 3, 2, 1)));
        System.out.println(isPalindrome(ListNode.init(1, 2, 3, 3, 4)));
        System.out.println(isPalindrome(ListNode.init(1, 0, 0)));
        System.out.println(isPalindrome(ListNode.init(1, 2, 2, 1)));
    }

    private static boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        int cnt = 0;
        ListNode temp = head;
        while (temp != null){
            cnt++;
            temp = temp.next;
        }
        int idx = cnt;
        while (idx-- > cnt / 2 && head != null) {
            stack.push(head);
            head = head.next;
        }
        if (cnt % 2 == 1){
            stack.pop();
        }
        while (head != null){
            if (stack.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }
        return stack.isEmpty();
    }
}
