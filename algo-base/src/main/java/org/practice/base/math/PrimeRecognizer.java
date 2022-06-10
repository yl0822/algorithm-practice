package org.practice.base.math;

/**
 * 质数判断
 *
 * @author feikong
 * @version 2022/6/7
 */
public class PrimeRecognizer {

    public static void main(String[] args) {
        System.out.println(isPrime(2));
        System.out.println(isPrime(7));
        System.out.println(isPrime(91));
        System.out.println(isPrime(566757));
        System.out.println(isPrime(56908569));
    }

    /**
     * 质数大于等于2且不能被它本身和1以外的数整除。
     * 对正整数p，如果用2到p平方根的之间（左右闭区间）的所有整数去除，均无法整除则p为质数。
     */
    public static boolean isPrime(int p) {
        if (p <= 3) {
            return p > 1;
        }
        for (int i = 2; i <= Math.sqrt(p); i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }
}
