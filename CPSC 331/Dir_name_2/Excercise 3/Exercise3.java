class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class Stack {
    private Node top;

    boolean isEmpty() {
        return top == null;
    }

    void push(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    void printStack() {
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " - ");
            current = current.next;
        }
        System.out.println();
    }
}

class Queue {
    private Node head, tail;

    boolean isEmpty() {
        return head == null;
    }

    void enqueue(int data) {
        Node node = new Node(data);
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    void printQueue() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " - ");
            current = current.next;
        }
        System.out.println();
    }
}

class SimpleSet {
    private Node head;

    void add(int data) {
        if (!contains(data)) {
            Node node = new Node(data);
            if (head == null) {
                head = node;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
        }
    }

    boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

public class Exercise3 {
    private static void reverseStack(Stack stack, Queue queue) {
        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }
    }

    private static Queue generateUniqueQueue(Stack stack) {
        Queue uniqueQueue = new Queue();
        SimpleSet uniqueSet = new SimpleSet();

        while (!stack.isEmpty()) {
            int element = stack.pop();
            if (!uniqueSet.contains(element)) {
                uniqueSet.add(element);
                uniqueQueue.enqueue(element);
            }
        }

        return uniqueQueue;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        int[] input = {2, 9, 3, 1, 8, 9, 0, 7, 8, 4, 5, 3};
        // Fill the stack
        for (int i = input.length - 1; i >= 0; i--) {
            stack.push(input[i]);
        }

        System.out.print("Input Stack: ");
        stack.printStack();

        Queue queue = new Queue();
        reverseStack(stack, queue);

        System.out.print("Reversed Stack into Queue: ");
        while (!queue.isEmpty()) {
            stack.push(queue.dequeue()); // Revert back to stack to maintain original order for uniqueness operation
        }
        stack.printStack(); // Since we pushed back to stack, let's print the stack to show it's reversed

        Queue uniqueQueue = generateUniqueQueue(stack); // Generate unique queue
        System.out.print("Output Queue with unique values: ");
        uniqueQueue.printQueue();
    }
}

