package org.practice.base.scene;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 * 链接：https://leetcode.cn/problems/min-stack
 *
 * @author feikong
 * @version 2022/6/28
 */
public class MinStack {
    // 存储每一次push之后最小值的快照历史，如果只有一个int值存储会丢掉历史，导致如果原最小值被pop之后无法更新最小值。
    // 和正真数据存储stack同步操作，保证快照历史和stack中元素一致。
    private Stack<Integer> min;
    private Stack<Integer> stack;

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    public MinStack() {
        this.stack = new Stack<>();
        this.min = new Stack<>();
        this.min.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        min.push(Math.min(val, min.peek()));
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
