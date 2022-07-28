package org.practice.advanced;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 * https://leetcode.cn/problems/remove-k-digits/
 *
 * @author feikong
 * @version 2022/7/15
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        System.out.println(removeKDigits("732654", 3));
    }

    /**
     * 先考虑删除一个数字的逻辑，然后重复k次。
     * 删除一个数字的，要注意不是直接删除高位，因为剩余数字不会补0，也就是123删除1是23，删除3是12。但高位对数字权重影响更大。
     * 一个数字全排列，最小就是升序，最大就是降序，所以就是从高位开始找，如果升序就一直迭代直到找到降序数字，该升序串的最后一位就是要删除的数值。
     */
    private static String removeKDigits(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
