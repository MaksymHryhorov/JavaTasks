package com.knubisoft.base.queue;

import com.knubisoft.base.queue.car.Car;
import com.knubisoft.base.queue.car.CarComparator;

import java.util.*;

public class QueueTasksImpl implements QueueTasks {

    @Override
    public Queue<Integer> reverseQueueUsingRecursion(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return queue;
        }

        Integer change = queue.poll();
        reverseQueueUsingRecursion(queue);
        queue.add(change);

        return queue;
    }

    @Override
    public Queue<Integer> reverseFirstKElementsOfQueue(Queue<Integer> queue, int k) {
        if (queue.isEmpty() || k > queue.size()) {
            return queue;
        }

        if (k <= 0) {
            return queue;
        }

        Stack<Integer> stack = new Stack<>();

        // Push the first K elements into a Stack
        for (int i = 0; i < k; i++) {
            stack.push(queue.peek());
            queue.remove();
        }

        // Enqueue the contents of stack
        // at the back of the queue
        while (!stack.empty()) {
            queue.add(stack.peek());
            stack.pop();
        }

        // Remove the remaining elements and enqueue
        // them at the end of the Queue
        for (int i = 0; i < queue.size() - k; i++) {
            queue.add(queue.peek());
            queue.remove();
        }

        return queue;
    }

    @Override
    public Queue<Integer> sortQueueOfInt(Queue<Integer> queue) {
        ArrayList<Integer> array = new ArrayList<>();

        while (!queue.isEmpty()) {
            array.add(queue.peek());
            queue.poll();
        }

        Collections.sort(array);
        queue.addAll(array);

        return queue;
    }

    @Override
    public boolean validParentheses(String parentheses) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < parentheses.length(); i++) {
            if ((parentheses.charAt(i) == '(') || (parentheses.charAt(i) == '[')
                    || (parentheses.charAt(i) == '{')) {

                stack.push(parentheses.charAt(i));

            } else {
                if (stack.empty()) return false;
                if ((parentheses.charAt(i) == ')') && (stack.peek() != '(')) return false;
                if ((parentheses.charAt(i) == ']') && (stack.peek() != '[')) return false;
                if ((parentheses.charAt(i) == '}') && (stack.peek() != '{')) return false;

                stack.pop();
            }
        }

        return stack.empty();
    }

    @Override
    public PriorityQueue<Car> implementPriorityQueueThroughComparator(List<Car> cars) {
        PriorityQueue<Car> priorityQueue = new PriorityQueue<>(cars.size(), new CarComparator());
        priorityQueue.addAll(cars);
        return priorityQueue;
    }
}
