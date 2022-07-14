package org.practice.base.math;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class IsNumberPowerOfFour {

    public static void main(String[] args) {
        System.out.println(isPowerOf4(4));
        System.out.println(isPowerOf4(1024));
        System.out.println(isPowerOf4(65536));
        System.out.println(isPowerOf4(240));
        System.out.println(isPowerOf4(64 * 64));
        System.out.println(isPowerOf4(1024 * 1024));
        System.out.println(isPowerOf4(1000));
    }

    private static boolean isPowerOf4(int num){
        return num > 0 && (num & (num - 1)) == 0 && num % 3 == 1;
    }
}
