package org.practice.base.data;

/**
 * @author feikong
 * @version 2022/6/10
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val){
        this.val = val;
        this.next = null;
    }

    public static ListNode init(int... value){
        ListNode listNode = new ListNode(-1);
        ListNode temp = listNode;
        for (int i : value) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return listNode.next;
    }

    public static ListNode getLastNode(ListNode listNode){
        if (listNode == null){
            return null;
        }
        while (listNode.next != null){
            listNode = listNode.next;
        }
        return listNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(val + "");
        ListNode tmp = next;
        while (tmp != null){
            sb.append(" -> ").append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
