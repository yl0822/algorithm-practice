package org.practice.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 *
 * 一次煎饼翻转的执行过程如下：
 *
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 *
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 * 链接：https://leetcode.cn/problems/pancake-sorting
 *
 * @author feikong
 * @version 2022/6/28
 */
public class PancakeSort {
    public static void main(String[] args) {
//        pancakeSort(new int[]{3, 2, 4, 1}).forEach(System.out::println);
//        pancakeSort(new int[]{1, 2, 3, 4}).forEach(System.out::println);
        pancakeSort(new int[]{155, 66, 1000, 3, 5, 35, 664, 556, 112,  1001}).forEach(System.out::println);
    }

    /**
     * 煎饼排序就是不断反转数组前k个元素，就像你有一叠半径各异的煎饼，为了不扯破煎饼，你只能不断翻转煎饼而不能直接扯出来或塞进去。
     * 3, 2, 4, 1 -- 第一步拿到最大元素4
     * 4, 2, 3, 1 -- 第二步因为只能从前到后翻，所以得把4先翻到第一个
     * 1, 3, 2, 4 -- 第三步再翻到最后面，这样一轮下来，就完成一个元素的排序
     * 3, 1, 2, 4
     * 2, 1, 3, 4
     * 1, 2, 3, 4
     * */
    private static List<Integer> pancakeSort(int[] arr) {
        List<Integer> list = new ArrayList<>();
        // 一轮确定一个元素，所以一共要走数组长度轮，且没次确定一个最大值，所以排序范围不断缩小。
        for (int i = arr.length - 1; i > 0; i--) {
            // 取最大元素下标
            int maxIdx = getMaxIdx(arr, i);
            // 如果最大元素已经在最右边，直接跳过
            if (maxIdx == i){
                continue;
            }
            // 如果最大元素已经在最左边，跳过第二步，直接进行第三步
            if (maxIdx > 0){
                reverse(arr, maxIdx);
                list.add(maxIdx + 1);
            }
            // 第三步
            reverse(arr, i);
            list.add(i + 1);
        }
        return list;
    }

    private static int getMaxIdx(int[] arr, int k){
        int max = arr[0];
        int idx = 0;
        for (int i = 0; i <= k; i++) {
            if (arr[i] > max){
                max = arr[i];
                idx = i;
            }
        }
        return idx;
    }

    private static void reverse(int[] arr, int k){
        for (int i = 0, j=k; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
