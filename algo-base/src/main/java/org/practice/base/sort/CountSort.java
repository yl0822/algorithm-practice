package org.practice.base.sort;

import org.practice.base.data.BaseHelper;
import org.practice.base.data.DataPrinter;

/**
 * @author feikong
 * @version 2022/7/13
 */
public class CountSort {

    public static void main(String[] args) {
        DataPrinter.printArray(sort(new int[]{2,7,3,4,1,5,6, 8, 8,7, 1, 2, 3,2, 1,4, 6,9,3,2}));
    }

    /**
     * 如果事先知道待排元素的范围（当然知道基数就更好了），可以以这个范围的大小构建一个数组，遍历待排元素，每个元素作为数组下标将数组内的次数+1，
     * 最终形成了元素下标和数量的映射关系，从而遍历一遍数组列出下标以及次数列出即可。
     * */
    private static int[] sort(int[] data){
        int range = BaseHelper.max(data);
        int[] count = new int[range + 1];
        for (int item : data) {
            if (item > range){
                throw new RuntimeException("元素不在范围内");
            }
            count[item] = count[item] + 1;
        }
        int[] sorted = new int[data.length];
        int idx = 0;
        for (int i = 0; i < count.length; i++) {
            for (int i1 = 0; i1 < count[i]; i1++) {
                sorted[idx++] = i;
            }
        }
        return sorted;
    }
}
