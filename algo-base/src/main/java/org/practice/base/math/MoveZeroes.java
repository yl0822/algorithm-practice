package org.practice.base.math;

import org.practice.base.data.DataPrinter;

import static org.practice.base.data.BaseHelper.swap;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * https://leetcode.cn/problems/move-zeroes/
 *
 * @author feikong
 * @version 2022/6/24
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] ints = new int[]{0, 1, 0, 5, 0, 0, 2, 4, 0, 0, 3, 0};
//        int[] ints = new int[]{1, 2, 3, 4, 5};
        DataPrinter.printArray(ints);
        moveZeroes(ints);
        DataPrinter.printArray(ints);
    }

    private static void moveZeroes(int[] nums) {
        // 初始化一个指向第一个0的零指针
        int zero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                // 如果该元素是0，且零指针从来没赋值过，就将零指针指向当前下标，也就是第一个0
                if (zero < 0){
                    zero = i;
                }
                // 零指针赋值之后，后续再出现0也不要更新了，因为要保证零指针指向的是第一个0
            }else {
                // 首元素非0，直接跳过
                if (i == 0){
                    continue;
                }
                // 非0元素之前如果存在零指针，就把当前元素和零指针交换
                if (zero >= 0){
                    swap(nums, zero, i);
                    // 这里是核心，交换后零指针下移一位，这里有大学问
                    // 首先这里不能用很容易想到的的zero=i，想着零指针和i交换之后，再把零指针指向i，这个操作的问题在于
                    // 比如000XX，碰到第1个X的时候zero=0,i=3,这样操作之后变成X000X，此时zero=i=3，当i继续迭代到4的时候继续进行交换
                    // 就变成了X00X0，而不是XX000。所以这里就违背了上面一直强调的零指针一定要指向第一个0
                    // 那这里为什么用zero++，因为0元素和非0元素之间只有两种情况，一个是0X相邻，一个是0000X之间有多个0
                    // 相邻的情况下zero++和zero=i的效果其实是一样的，多个0的情况下，只有zero++才能保证0指针是数组里的第一个0
                    zero++;
                }
                // 如果没有零指针，就继续向前迭代
            }
        }
    }
}
