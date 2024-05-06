// THE MAIN CLASS DEMONSTRATES OPERATIONS ON A CUSTOM QUEUE
public class Exercise2 {
    public static void main(String[] args) {

        Queue queue = new Queue();
        int[] seedValues = {3, 15, 0, 18, 4, 10, 2, 5, 25};

        for (int value : seedValues) {
            queue.enqueue(value);
        }

        // DEMONSTRATION 1: REVERSING THE QUEUE
        System.out.println("Operation 1:");
        System.out.print("Queue before operations - ");
        queue.displayQueue();

        queue.reverse();
        System.out.print("Queue after reversing - ");
        queue.displayQueue();

        // DEMONSTRATION 2: DIVIDING EACH ELEMENT BY ITS SUCCESSOR
        System.out.println("Operation 2:");
        System.out.print("Current state of the queue - ");
        queue.displayQueue();

        queue.divideByNext();
        System.out.print("Queue after division operation - ");
        queue.displayQueue();

        System.out.println("Operation 3:");
        System.out.print("Queue before removing duplicates and sorting - ");
        queue.displayQueue();

        // DEMONSTRATION 3: REMOVING DUPLICATES AND SORTING
        queue.removeDuplicates();
        queue.sortAscending();
        System.out.print("Queue after removing duplicates and sorting - ");
        queue.displayQueue();
    }
}

