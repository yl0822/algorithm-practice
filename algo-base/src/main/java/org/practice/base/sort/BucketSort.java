package org.practice.base.sort;

import org.practice.base.data.DataPrinter;

/**
 * @author feikong
 * @version 2022/6/21
 */
public class BucketSort {

    public static void main(String[] args) {
        DataPrinter.printArray(sort(new int[]{155, 66, 1000, 3, 5, 35, 664, 556, 112,  1001}));
    }

    private static int[] sort(int[] data) {
        int n = data.length;
        int[][] bask = new int[10][n];
        int[] index = new int[10];
        int max = Integer.MIN_VALUE;
        for (int value : data) {
            max = Math.max(max, (Integer.toString(value).length()));
        }
        StringBuilder str;
        for (int i = max - 1; i >= 0; i--) {
            for (int datum : data) {
                str = new StringBuilder();
                if (Integer.toString(datum).length() < max) {
                    for (int k = 0; k < max - Integer.toString(datum).length(); k++)
                        str.append("0");
                }
                str.append(datum);
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = datum;
            }
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < index[j]; k++) {
                    data[pos++] = bask[j][k];
                }
            }
            for (int x = 0; x < 10; x++) {
                index[x] = 0;
            }
        }
        return data;
    }
}
