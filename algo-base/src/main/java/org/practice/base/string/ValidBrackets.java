package org.practice.base.string;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * @author feikong
 * @version 2022/6/14
 */
public class ValidBrackets {

    public static void main(String[] args) {
        System.out.println(isValidBrackets("()"));
        System.out.println(isValidBrackets("()[]{}"));
        System.out.println(isValidBrackets("(]"));
        System.out.println(isValidBrackets("([)]"));
    }

    private static boolean isValidBrackets(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if ((c == ')' && pop != '(') || (c == ']' && pop != '[') || (c == '}' && pop != '{')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
