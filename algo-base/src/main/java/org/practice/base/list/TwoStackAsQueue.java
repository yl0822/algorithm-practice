package org.practice.base.list;

import java.util.Stack;

/**
 * 两个栈实现队列
 *
 * @author feikong
 * @version 2022/6/22
 */
public class TwoStackAsQueue {
    private final Stack<Integer> s1;
    private final Stack<Integer> s2;
    private final Object lock;

    public TwoStackAsQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        lock = new Object();
    }

    public static void main(String[] args) {
        TwoStackAsQueue queue = new TwoStackAsQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        queue.push(4);
        queue.push(5);
        System.out.println(queue.pop());
        queue.push(6);
        queue.push(7);
        queue.push(8);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public int pop(){
        if (s2.isEmpty()){
            swapStack();
        }
        return s2.pop();
    }

    private void swapStack(){
        synchronized (lock){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
    }

    public void push(int n){
        synchronized (lock){
            s1.push(n);
        }
    }
}
