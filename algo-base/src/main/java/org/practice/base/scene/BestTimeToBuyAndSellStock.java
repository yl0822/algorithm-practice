package org.practice.base.scene;

/**
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 *
 * @author feikong
 * @version 2022/6/17
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{2,5,33,6,1,11}));
        System.out.println(maxProfit2(new int[]{7,6,4,3,1}));
    }

    /**
     * 1. 这个题和最大子序和有点像
     * 2. 无序数组，按从前到后的顺序，求最大差值。
     * 3. 暴力方式：算出每个值和后续值的最大差值，找最大的。（实时证明超时了）
     * 4. 单次轮询：设定一个base值，再指定一个指针不断后移，遇到比base更小的就更新base（因为首先不可能往前算，
     *      其次后面任意一个值和更小的base值相差一定更大），如果碰到更大的就和之前最大差值进行比较取更大。
     * */
    private static int maxProfit2(int[] prices) {
        int max = 0;
        int base = prices[0];
        int next;
        for (int i = 1; i < prices.length; i++) {
            next = prices[i];
            if (base > next){
                base = next;
            }else {
                max = Math.max(max, next - base);
            }
        }
        return max;
    }

    private static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int i1 = i + 1; i1 < prices.length; i1++) {
                max = Math.max(max, prices[i1] - prices[i]);
            }
        }
        return max;
    }
}
