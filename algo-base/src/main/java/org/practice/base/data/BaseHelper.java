package org.practice.base.data;

import com.sun.crypto.provider.RC2Cipher;
import sun.misc.Unsafe;
import sun.reflect.Reflection;

/**
 * @author feikong
 * @version 2022/6/24
 */
public class BaseHelper {

    public static void main(String[] args) {
//        System.out.println(Object.class.getClassLoader().getClass().getName());
        System.out.println(String.class.getClassLoader().getClass().getName());
//        System.out.println(Unsafe.class.getClassLoader().getClass().getName());
        System.out.println(RC2Cipher.class.getClassLoader().getClass().getName());
        System.out.println(Reflection.getCallerClass());

    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
