package org.practice.advanced;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author feikong
 * @version 2022/6/24
 */
public class RateLimiter {
    // 时间段（秒）
    private long period;
    // 时间段内允许最大流量（这里不考虑实时并发流量，也就是只需要限制时间段内进来的多少流量，而不用考虑当前有效流量，也就是请求结束后无需移除）
    private long limit;
    private Map<Long, AtomicInteger> periodMap;

    public RateLimiter(long period, long limit) {
        this.period = period;
        this.limit = limit;
        this.periodMap = new HashMap<>();
    }

    public static void main(String[] args) throws Exception{
        // 每秒最多3个请求
        RateLimiter limiter = new RateLimiter(1, 3);
        // 应该是前3个不拦截，第四个拦截
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
        Thread.sleep(990);
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
        System.out.println(limiter.counterLimit());
    }

    /**
     * 在period周期内统计请求次数
     * */
    public boolean counterLimit(){
        long cur = System.currentTimeMillis();
        Long key = (cur / 1000) + period;
        AtomicInteger ac = periodMap.get(key);
        if (ac == null){
            ac = new AtomicInteger();
            periodMap.put(key, ac);
        }
        return ac.incrementAndGet() > limit;
    }

    public boolean windowLimit(){
        long cur = System.currentTimeMillis();
        Long key = (cur) + period * 1000;
        AtomicInteger ac = periodMap.get(key);
        if (ac == null){
            ac = new AtomicInteger();
            periodMap.put(key, ac);
        }
        return ac.incrementAndGet() > limit;
    }

    public boolean leakLimit(){
        return false;
    }

    public boolean bucketLimit(){
        return false;
    }
}
