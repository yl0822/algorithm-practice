package org.practice.base.scene;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author feikong
 * @version 2022/7/14
 */
public class QueueMockStack {
    private Queue<Integer> input;
    private Queue<Integer> output;

    public QueueMockStack() {
        this.input = new LinkedList<>();
        this.output = new LinkedList<>();
    }

    public static void main(String[] args) {
        QueueMockStack queue = new QueueMockStack();
        queue.push2(1);
        queue.push2(2);
        queue.push2(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push2(4);
        queue.push2(5);
        queue.push2(6);
        System.out.println(queue.top());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    /**
     * 将队列数据想想成环，每但一个元素进来，就要把原来的数据放到该元素之后去，保证最新的元素一定在队列最前。
     * 所以和StackMockQueue不一样，这里的第二个队列的作用仅仅是用于临时中转数据。
     * */
    public void push(int x) {
        if (input.isEmpty()){
            input.offer(x);
            return;
        }
        while (!input.isEmpty()){
            output.offer(input.remove());
        }
        input.offer(x);
        while (!output.isEmpty()){
            input.offer(output.remove());
        }
    }

    /**
     * 如果理解上面逻辑，就知道用可以数量来处理，一个队列也可以实现。
     * */
    public void push2(int x) {
        if (input.isEmpty()){
            input.offer(x);
            return;
        }
        int n = input.size();
        input.offer(x);
        while (n-- > 0){
            input.offer(input.remove());
        }
    }

    public int pop() {
        return input.remove();
    }

    public int top() {
        return input.element();
    }

    public boolean empty() {
        return input.isEmpty();
    }
}
