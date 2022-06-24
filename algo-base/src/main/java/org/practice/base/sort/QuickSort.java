package org.practice.base.sort;

import org.practice.base.data.DataPrinter;

import static org.practice.base.sort.HeapSort.swap;

/**
 * @author feikong
 * @version 2022/6/22
 */
public class QuickSort {

    public static void main(String[] args) {
        DataPrinter.printArray(sort(new int[]{155, 66, 1000, 3, 5, 35, 664, 556, 112,  1001}));
        DataPrinter.printArray(sort(new int[]{2,7,3,4,1,5,6}));
    }

    private static int[] sort(int[] data){
        return quickSort(data, 0, data.length - 1);
    }

    /**
     * 任选一个base值，大于该base值的右移，小于该base值的左移，
     * */
    private static int[] quickSort(int[] data, int left, int right) {
        if (left < right) {
            // 基础值
            int base = left;
            // 用于保存下一个大于base的下标
            int idx = left + 1;
            for (int i = left + 1; i <= right; i++) {
                // i不断迭代，idx在碰到比base更小的值就会和离得最近的比base大的值交换
                if (data[i] < data[base]) {
                    // 一定要注意这里交换不是base值，而是i和idx，可以理解为base是在整体结束后统一处理
                    swap(data, i, idx);
                    idx++;
                }
            }
            // 因为最后一次的idx++（预加）没用上
            idx--;
            // 一轮结束完之后 |base|一堆比base小的值（这堆数据本身无序）|一堆比base大的值（这堆数据本身无序）|
            // 而idx正好就是大小数据的分隔下标，所以此时将base放到这个下标上，于是形成了
            // |一堆比base小的值（这堆数据本身无序）|base|一堆比base大的值（这堆数据本身无序）|
            swap(data, base, idx);
            // 无序小堆数据再次排序
            quickSort(data, left, idx - 1);
            // 无序大堆数据再次排序
            quickSort(data, idx + 1, right);
        }
        return data;
    }
}
