package org.practice.base.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 分解质因数
 *
 * @author feikong
 * @version 2022/6/7
 */
public class PrimeFactorization {

    public static void main(String[] args) {
        // 2,2,2,3
        pf2(24).forEach(System.out::println);
        System.out.println("---------");
        // 3,3,5
        pf2(45).forEach(System.out::println);
    }

    /**
     * 如果本身是质数，那么就无法分解可以直接返回
     * 如果是合数，那么从最小的质数到给指定值区间开始判断每一个质数，能整除的就是质因数。
     * 递归方式
     * */
    private static List<Integer> pf3(int p){
        List<Integer> list = new ArrayList<>();
        if (PrimeRecognizer.isPrime(p)){
            list.add(p);
            return list;
        }
        for (int i = 2; i <= p; i++) {
            if (!PrimeRecognizer.isPrime(i)){
                continue;
            }
            if (p % i == 0){
                list.addAll(pf3(p / i));
            }
        }
        return list;
    }

    /**
     * 循环方式
     * */
    private static List<Integer> pf2(int p){
        List<Integer> list = new ArrayList<>();
        while (true){
            int f = pf(p);
            list.add(f);
            if (p == f){
                break;
            }
            p = p / f;
        }
        return list;
    }

    private static int pf(int p){
        if (PrimeRecognizer.isPrime(p)){
            return p;
        }
        for (int i = 2; i <= p; i++) {
            if (!PrimeRecognizer.isPrime(i)){
                continue;
            }
            if (p % i == 0){
                return i;
            }
        }
        throw new RuntimeException("");
    }
}
