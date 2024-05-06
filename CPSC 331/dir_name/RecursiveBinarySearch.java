public class RecursiveBinarySearch {

    // Recursive Binary Search function implemented as provided
    public static int recursiveBinarySearch(int[] arr, int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid; // Target found
            }
            if (arr[mid] < target) {
                return recursiveBinarySearch(arr, mid + 1, high, target);
            } else {
                return recursiveBinarySearch(arr, low, mid - 1, target);
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40}; // Example sorted array
        int target = 10; // Target value to find
        int resultIndex = recursiveBinarySearch(arr, 0, arr.length - 1, target);

        if (resultIndex != -1) {
            System.out.println("Element found at index: " + resultIndex);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}

