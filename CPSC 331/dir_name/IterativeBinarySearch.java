public class IterativeBinarySearch {

    
    public static int iterativeBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid; // Target found
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40}; // Example sorted array
        int target = 10; // Target value to find
        int resultIndex = iterativeBinarySearch(arr, target);

        if (resultIndex != -1) {
            System.out.println("Element found at index: " + resultIndex);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}

