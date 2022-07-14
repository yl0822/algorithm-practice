package org.practice.base.math;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class IsNumberPowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOf3(3));
        System.out.println(isPowerOf3(9));
        System.out.println(isPowerOf3(81));
        System.out.println(isPowerOf3(45));
        System.out.println(isPowerOf3(81 * 81));
        System.out.println(isPowerOf3(65536));
        System.out.println(isPowerOf3(999));
    }

    private static boolean isPowerOf3(int num){
        return num > 0 && 1162261467 % num == 0;
    }
}
