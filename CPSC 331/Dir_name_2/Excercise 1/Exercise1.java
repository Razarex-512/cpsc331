class DirectedGraph {
    private int numVertices;
    private Node[] adjacencyList;

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    DirectedGraph(int vertices) {
        numVertices = vertices;
        adjacencyList = new Node[vertices];
    }

    void addEdge(int vertex1, int vertex2) {
        Node newNode = new Node(vertex2);
        if (adjacencyList[vertex1] == null) {
            adjacencyList[vertex1] = newNode;
        } else {
            Node temp = adjacencyList[vertex1];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    boolean isReachable(int src, int dest) {
        if (src == dest) return true;

        boolean[] visited = new boolean[numVertices];
        Queue queue = new Queue();
        visited[src] = true;
        queue.enqueue(src);

        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            Node head = adjacencyList[currentVertex];

            while (head != null) {
                int nextVertex = head.data;
                if (nextVertex == dest) return true;

                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.enqueue(nextVertex);
                }
                head = head.next;
            }
        }
        return false;
    }

    private static class Queue {
        Node front, rear;

        Queue() {
            this.front = this.rear = null;
        }

        void enqueue(int data) {
            Node newNode = new Node(data);
            if (rear == null) {
                front = rear = newNode;
                return;
            }
            rear.next = newNode;
            rear = newNode;
        }

        int dequeue() {
            if (front == null) return -1;

            Node temp = front;
            front = front.next;
            if (front == null) rear = null;
            return temp.data;
        }

        boolean isEmpty() {
            return front == null;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 6}, {6, 7}, {7, 3}, {3, 5}, {4, 6}};
        int n = 6; // Number of vertices
        DirectedGraph graph = new DirectedGraph(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        int src = 4, dest = 5;
        System.out.println(graph.isReachable(src, dest)); // Output: true

        src = 5;
        dest = 0;
        System.out.println(graph.isReachable(src, dest)); // Output: false
    }
}

