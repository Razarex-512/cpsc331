import java.util.ArrayList;
import java.util.Scanner;
/*
        1
        3
        3
        0 1 8
        1 2 4
        0 2 6

        1
        4
        5
        0 1 15
        0 2 10
        1 2 5
        1 3 20
        2 3 12

        1
        3
        5
        0 1 10
        0 2 15
        1 2 5
        1 3 20
        2 3 12
*/

public class Main {

    // Array to store routes
    static ArrayList<Integer>[] routes;

    // Main method to execute Dijkstra's algorithm
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of warehouses: ");
        int warehouse = scanner.nextInt();
        System.out.println("Enter the number of delivery locations: ");
        int locations = scanner.nextInt() + warehouse;
        System.out.println("Enter the number of roads: ");
        int roads = scanner.nextInt();

        // Initialize the graph and routes array
        int[][] graph = new int[locations][locations];
        routes = new ArrayList[locations];
        for (int i = 0; i < locations; i++) {
            routes[i] = new ArrayList<>();
            if (i == 0) routes[i].add(0); // Warehouse as the start point
        }

        // Input roads and distances
        System.out.println("Enter the roads in the format 'start end distance': ");
        for (int i = 0; i < roads; i++) {
            int start = ensureNonNegative(scanner, "Start node cannot be negative. Please re-enter: ");
            int end = ensureNonNegative(scanner, "End node cannot be negative. Please re-enter: ");
            int distance = ensurePositive(scanner, "Distance must be positive. Please re-enter: ");
            graph[start][end] = distance;
        }

        // Execute Dijkstra's algorithm
        dijkstra(graph, locations);
    }

    // Dijkstra's algorithm implementation
    public static void dijkstra(int[][] graph, int locations) {
        int[] shortestDistances = new int[locations];
        boolean[] visited = new boolean[locations];

        for (int i = 0; i < locations; i++) {
            shortestDistances[i] = Integer.MAX_VALUE;
        }
        shortestDistances[0] = 0; // Distance from source to itself

        for (int i = 0; i < locations; i++) {
            int nearest = findNearestLocation(shortestDistances, visited);
            visited[nearest] = true;

            for (int adj = 0; adj < locations; adj++) {
                if (!visited[adj] && graph[nearest][adj] != 0 && shortestDistances[nearest] != Integer.MAX_VALUE && shortestDistances[nearest] + graph[nearest][adj] < shortestDistances[adj]) {
                    shortestDistances[adj] = shortestDistances[nearest] + graph[nearest][adj];
                    updateRoute(routes, nearest, adj);
                }
            }
        }

        printRoutes(shortestDistances, locations);
    }

    // Helper methods
    private static int ensureNonNegative(Scanner scanner, String message) {
        int value = scanner.nextInt();
        while (value < 0) {
            System.out.println(message);
            value = scanner.nextInt();
        }
        return value;
    }

    private static int ensurePositive(Scanner scanner, String message) {
        int value = scanner.nextInt();
        while (value <= 0) {
            System.out.println(message);
            value = scanner.nextInt();
        }
        return value;
    }

    private static int findNearestLocation(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                index = i;
            }
        }
        return index;
    }

    private static void updateRoute(ArrayList<Integer>[] routes, int source, int destination) {
        routes[destination].clear();
        routes[destination].addAll(routes[source]);
        routes[destination].add(destination);
    }

    private static void printRoutes(int[] distances, int locations) {
        for (int i = 1; i < locations; i++) {
            System.out.print("Path to Location " + i + ": ");
            if (routes[i].isEmpty()) {
                System.out.println("No accessible path, Distance: Infinity");
            } else {
                for (int j = 0; j < routes[i].size(); j++) {
                    System.out.print(routes[i].get(j) + (j < routes[i].size() - 1 ? " -> " : ""));
                }
                System.out.println(", Distance: " + distances[i]);
            }
        }
    }
}
