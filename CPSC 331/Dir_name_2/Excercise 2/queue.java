
class Queue {
    Node front;
    Node rear;

    public Queue() {
        this.front = this.rear = null;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int dequeued = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return dequeued;
    }

    //EX: 2-1
    public void reverse() {
        Node prev = null;
        Node current = this.front;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        this.front = prev;
    }

    //EX: 2-2
    public void divideByNext() {
        Node current = this.front;

        while (current != null && current.next != null) {
            if (current.data != 0 && current.next.data != 0) {
                if (current.data < current.next.data) {
                    current.data = current.next.data / current.data;
                } else {
                    current.data /= current.next.data;
                }
            } else {
                current.data = 0; // Set to 0 if either current or next data is 0
            }

            current = current.next;
        }
    }


    //EX: 2-3
    public void removeDuplicates() {
        Node current = this.front;
        Node temp = null;

        while (current != null && current.next != null) {
            temp = current;

            while (temp.next != null) {
                if (current.data == temp.next.data) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }

            current = current.next;
        }
    }

    //EX: 2-3
    public void sortAscending() {
        Node current = this.front;
        Node index = null;
        int temp;

        while (current != null) {
            index = current.next;

            while (index != null) {
                if (current.data > index.data) {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }

                index = index.next;
            }

            current = current.next;
        }
    }

    public void displayQueue() {
        Node current = this.front;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    public boolean isEmpty() {
        return front == null;
    }

}
