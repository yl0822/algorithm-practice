package org.practice.base.scene;

/**
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 *
 * 链接：https://leetcode.cn/problems/container-with-most-water
 *
 *
 * @author feikong
 * @version 2022/6/21
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea2(new int[]{1, 1}));
    }

    private static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int i1 = i + 1; i1 < height.length; i1++) {
                max = Math.max(max, Math.min(height[i], height[i1]) * (i1 - i));
            }
        }
        return max;
    }

    private static int maxArea2(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j){
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]){
                i++;
            }else {
                j--;
            }
        }
        return max;
    }
}
