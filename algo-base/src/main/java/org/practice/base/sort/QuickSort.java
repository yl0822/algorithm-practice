package org.practice.base.sort;

import org.practice.base.data.DataPrinter;

import static org.practice.base.data.BaseHelper.swap;

/**
 * @author feikong
 * @version 2022/6/22
 */
public class QuickSort {

    public static void main(String[] args) {
        DataPrinter.printArray(sort(new int[]{155, 66, 1000, 3, 5, 35, 664, 556, 112,  1001}));
        DataPrinter.printArray(sort(new int[]{2,7,3,4,1,5,6}));
    }

    public static int[] sort(int[] data){
        return quickSort2(data, 0, data.length - 1);
    }

    /**
     * 单向迭代
     * */
    private static int[] quickSort(int[] data, int left, int right) {
        if (left < right) {
            // 基础值
            int pivot = data[left];
            // 用于保存下一个大于pivot的下标
            int idx = left + 1;
            for (int i = left + 1; i <= right; i++) {
                // i不断迭代，idx在碰到比pivot更小的值就会和离得最近的比pivot大的值交换
                if (data[i] < pivot) {
                    // 一定要注意这里交换不是pivot值，而是i和idx，可以理解为pivot是在整体结束后统一处理
                    swap(data, i, idx);
                    idx++;
                }
            }
            // 退一位将原来指向第一个大值改为指向最后一个小值，用于下一步的和pivot交换
            idx--;
            // 一轮结束完之后 |pivot|一堆比pivot小的值（这堆数据本身无序）|一堆比pivot大的值（这堆数据本身无序）|
            // 而idx正好就是大小数据的分隔下标，所以此时将pivot放到这个下标上，于是形成了
            // |一堆比pivot小的值（这堆数据本身无序）|pivot|一堆比pivot大的值（这堆数据本身无序）|
            swap(data, left, idx);
            // 无序小堆数据再次排序
            quickSort(data, left, idx - 1);
            // 无序大堆数据再次排序
            quickSort(data, idx + 1, right);
        }
        return data;
    }

    /**
     * 双向迭代
     * */
    private static int[] quickSort2(int[] data, int left, int right) {
        if (left >= right){
            return data;
        }
        // 基础值
        int pivot = data[left];
        // 用于保存下一个大于pivot的下标
        int head = left + 1;
        int tail = right;
        while (head < tail){
            if (data[tail] > pivot){
                tail--;
                continue;
            }
            if (data[head] < pivot){
                head++;
                continue;
            }
            swap(data, head, tail);
        }
        swap(data, left, head);
        // 无序小堆数据再次排序
        quickSort2(data, left, head - 1);
        // 无序大堆数据再次排序
        quickSort2(data, head + 1, right);
        return data;
    }
}
