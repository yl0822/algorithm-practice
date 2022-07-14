package org.practice.base.scene;

import java.util.Stack;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class StackMockQueue {
    private Stack<Integer> input;
    private Stack<Integer> output;

    public StackMockQueue() {
        this.input = new Stack<>();
        this.output = new Stack<>();
    }

    public static void main(String[] args) {
        StackMockQueue queue = new StackMockQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    public void enqueue(int n) {
        input.push(n);
    }


    public int dequeue() {
        if (output.isEmpty()) {
            if (input.isEmpty()) {
                return -1;
            } else {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
        }
        return output.pop();
    }
}
