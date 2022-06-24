package org.practice.base.data;

/**
 * @author feikong
 * @version 2022/6/21
 */
public class DataPrinter {

    public static void printArray(int[] ints){
        StringBuilder stringBuilder = new StringBuilder();
        for (int anInt : ints) {
            stringBuilder.append(anInt+", ");
        }
        System.out.println(stringBuilder);
    }
}
