package org.practice.base;

/**
 * 第一个算法：求两个数的最大公约数
 *
 * @author feikong
 * @version 2022/6/7
 */
public class GCD {

    public static void main(String[] args) {
        System.out.println(gcd(30, 45));
        System.out.println(gcd(169, 130));
        System.out.println(gcd(1756, 4444));
        System.out.println(gcd(5555, 7777));
    }

    /**
     * 计算两个非负整数的p和q的最大公约数，如果q是0则最大公约数是p，否则将p除以q得到的余数r，p和q的最大公约数就是q和r的最大公约数。
     * */
    private static int gcd(int p, int q){
        if (q == 0){
            return p;
        }
        return gcd(q, p % q);
    }
}
