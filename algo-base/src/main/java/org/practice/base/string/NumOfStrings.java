package org.practice.base.string;

/**
 * 给你一个字符串数组 patterns 和一个字符串 word ，统计 patterns 中有多少个字符串是 word 的子字符串。返回字符串数目。
 * https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word/
 *
 * @author feikong
 * @version 2022/6/10
 */
public class NumOfStrings {

    public static void main(String[] args) {
        System.out.println(nos(new String[]{"a", "abc", "bc", "d"}, "abc"));
        System.out.println(nos(new String[]{"a", "b", "c"}, "aaaaabbbbbb"));
        System.out.println(nos(new String[]{"a", "a", "a"}, "ab"));
    }

    /**
     * 遍历即可
     * */
    private static int nos(String[] patterns, String word){
        if (patterns == null || patterns.length <= 0){
            return 0;
        }
        int cnt = 0;
        for (String pattern : patterns) {
            cnt = word.contains(pattern) ? cnt + 1 : cnt;
        }
        return cnt;
    }
}
