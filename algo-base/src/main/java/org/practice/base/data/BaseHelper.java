package org.practice.base.data;

/**
 * @author feikong
 * @version 2022/6/24
 */
public class BaseHelper {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
