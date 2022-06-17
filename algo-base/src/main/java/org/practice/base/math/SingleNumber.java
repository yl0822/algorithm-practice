package org.practice.base.math;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 链接：https://leetcode.cn/problems/single-number
 *
 * @author feikong
 * @version 2022/6/17
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4, 3, 1, 3, 4}));
    }

    /**
     * 1. 要求有O（n）复杂度，以及不能有额外空间消耗
     * 2. 不能考虑用排序后再处理，因为排序至少O(nlog2n)，也不能两次循环判重。
     * 3. 数组一般都用迭代而不是递归，一是因为数组天然适合递归的下标查询，二是递归核心就是子集化分治处理，数组不支持元素增删。
     * 4. 本来只考虑了加减，没想到还有异或操作，0^0/1^1=0,1^0/0^1=1,所以推到出核心表达式：
     *      1. a^0=a
     *      2. a^a=0
     *      也就是说把列表里的值全异或，相同值互消（天然达到我想要的取负的想法），最后剩下的就是结果。
     *      所以说以后碰到需要同值互消的场景，要想起来异或！
     * */
    private static int singleNumber(int[] nums) {
        int base = nums[0];
        for (int i = 1; i < nums.length; i++) {
            base = base^nums[i];
        }
        return base;
    }
}
