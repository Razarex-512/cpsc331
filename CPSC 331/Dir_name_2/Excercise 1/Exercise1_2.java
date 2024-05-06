class Exercise1_2 {
    private int numVertices;
    private Node[] adjacencyList;

    private static class Node {
        int vertex;
        Node next;

        Node(int vertex) {
            this.vertex = vertex;
            this.next = null;
        }
    }

    private static class Path {
        int[] vertices;
        int count;

        Path(int size) {
            vertices = new int[size];
            count = 0;
        }

        void addVertex(int vertex) {
            if (count < vertices.length) {
                vertices[count++] = vertex;
            }
        }

        Path copy() {
            Path newPath = new Path(vertices.length);
            newPath.count = this.count;
            System.arraycopy(this.vertices, 0, newPath.vertices, 0, this.count);
            return newPath;
        }

        void printPath() {
            if (count > 0) {
                System.out.print(vertices[0]);
                for (int i = 1; i < count; i++) {
                    System.out.print(" — " + vertices[i]);
                }
                System.out.println();
            }
        }
    }

    public Exercise1_2(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new Node[numVertices];
    }

    public void addEdge(int src, int dest) {
        Node node = new Node(dest);
        node.next = adjacencyList[src];
        adjacencyList[src] = node;
    }

    private int countPathsUtil(int src, int dest, int m, Path currentPath, int[] pathCount) {
        currentPath.addVertex(src);
        if (m == 0 && src == dest) {
            pathCount[0]++;
            currentPath.printPath();
            return 1;
        }
        if (m < 0) return 0;

        int count = 0;
        Node temp = adjacencyList[src];
        while (temp != null) {
            Path newPath = currentPath.copy();
            count += countPathsUtil(temp.vertex, dest, m - 1, newPath, pathCount);
            temp = temp.next;
        }

        return count;
    }

    public int countPaths(int src, int dest, int m) {
        Path currentPath = new Path(m + 1);
        int[] pathCount = new int[1];
        countPathsUtil(src, dest, m, currentPath, pathCount);
        return pathCount[0];
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 6}, {0, 1}, {1, 6}, {1, 9}, {1, 5}, {5, 3}, {3, 4}, {9, 5}, {9, 3}, {9, 4}, {6, 9}, {7, 6}, {7, 1}};
        int n = 10; // Number of vertices
        Exercise1_2 graph = new Exercise1_2(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        int src = 0, dest = 3, m = 4;
        System.out.println("The graph has " + graph.countPaths(src, dest, m) + " routes from source " + src + " to destination " + dest + " with " + m + " edges.");
    }
}

