package org.practice.advanced;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足LRU (最近最少使用) 缓存 约束的数据结构。
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用(读)的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 链接：https://leetcode.cn/problems/lru-cache
 *
 * @author feikong
 * @version 2022/6/22
 */
public class LRUCacheWithLHM extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCacheWithLHM(int capacity){
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCacheWithLHM cache = new LRUCacheWithLHM(2);
        cache.put(2, 2);
        cache.put(1, 2);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println("------------------");
        cache = new LRUCacheWithLHM(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > this.capacity;
    }
}
