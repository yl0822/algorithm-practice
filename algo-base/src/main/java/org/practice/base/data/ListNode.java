package org.practice.base.data;

/**
 * @author feikong
 * @version 2022/6/10
 */
public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode(int value){
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(value + "");
        ListNode tmp = next;
        while (tmp != null){
            sb.append(" -> ").append(tmp.value);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
