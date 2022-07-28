package org.practice.base.math;

import java.util.Stack;

/**
 * @author feikong
 * @version 2022/7/15
 */
public class GiantNumPlus {

    public static void main(String[] args) {
//        System.out.println(plusGiantNum("1", "9"));
        System.out.println(plusGiantNum("0", "9133"));
        System.out.println(plusGiantNum2("2128798", "458745"));
    }

    private static String plusGiantNum(String num1, String num2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        // 补齐0
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            if (i > num1.length() - 1){
                stack1.push(0);
            }else {
                stack1.push(Integer.valueOf(String.valueOf(num1.charAt(i))));
            }
            if (i > num2.length() - 1){
                stack2.push(0);
            }else {
                stack2.push(Integer.valueOf(String.valueOf(num2.charAt(i))));
            }
        }
        StringBuilder sb = new StringBuilder();
        int ext = 0;
        while (!stack1.isEmpty()) {
            int temp = stack1.pop() + stack2.pop() + ext;
            ext = temp / 10;
            sb.append(temp % 10);
        }
        if (ext == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    private static String plusGiantNum2(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0';
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }
}
