package org.practice.base.math;

import org.practice.base.data.DataPrinter;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class FindNearestNumber {

    public static void main(String[] args) {
        DataPrinter.printArray(int2Array(12543));
        System.out.println(isOrdered(int2Array(12543), 0, 4));
        System.out.println(isOrdered(int2Array(12345), 0, 4));
        System.out.println(isOrdered(int2Array(54321), 0, 4));
        System.out.println(findNearestNumber(2, int2Array(12345)));
    }

    private static int findNearestNumber(int num){
        int size = String.valueOf(num).length();
        if (size == 1){
            return -1;
        }
        int[] ints = int2Array(num);
        for (int i = 2; i <= size; i++) {
            if (isOrdered(ints, size - i - 1, size - 1)){
            }
        }
        return 0;
    }

    private static int findNearestNumber(int tar, int[] ints){
        int nearest = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (nearest - tar > ints[i] - tar){
                nearest = ints[i];
            }
        }
        return nearest;
    }

    private static boolean isOrdered(int[] ints, int start, int end){
        int base = ints[start];
        for (int i = start + 1; i <= end; i++) {
            if (base > ints[i]){
                base = ints[i];
            }else {
                return false;
            }
        }
        return true;
    }

    private static int[] int2Array(int num){
        String str = String.valueOf(num);
        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(String.valueOf(str.toCharArray()[i]));
        }
        return ints;
    }
}
