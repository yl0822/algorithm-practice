package org.practice.base.math;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class IsNumberPowerOfTwo {

    public static void main(String[] args) {
        System.out.println(isPowerOf2(0));
        System.out.println(isPowerOf2(2));
        System.out.println(isPowerOf2(24));
        System.out.println(isPowerOf2(32));
        System.out.println(isPowerOf2(1024));
        System.out.println(isPowerOf2(65536));
        System.out.println(isPowerOf2(-2147483648));
    }

    private static boolean isPowerOf2(int num){
        return (num & (num - 1)) == 0;
    }
}
