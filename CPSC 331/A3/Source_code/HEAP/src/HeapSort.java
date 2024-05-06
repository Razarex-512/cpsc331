public class HeapSort {

    public static void heapSortHeapify(int[] arr) {
        int n = arr.length;
        int swapsHeapify = heapifyBuildMaxHeap(arr);

        int swapsSort = performHeapSort(arr, n);

        int totalSwaps = swapsHeapify + swapsSort;
        System.out.println("Swaps to Create Max Heap (Heapify): " + swapsHeapify);
        System.out.println("Total Swaps with Heapify Method: " + totalSwaps);
    }

    public static void heapSortInsert(int[] arr) {
        int n = arr.length;
        int swapsInsert = buildMaxHeapByInsertion(arr);

        int swapsSort = performHeapSort(arr, n);

        int totalSwaps = swapsInsert + swapsSort;
        System.out.println("Swaps for Heap Creation (Insertion): " + swapsInsert);
        System.out.println("Total Swaps with Insertion Method: " + totalSwaps);
    }

    private static int performHeapSort(int[] arr, int n) {
        int swapsSort = 0;
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swapsSort++;
            swapsSort += verifyMaxHeap(arr, i, 0);
        }
        return swapsSort;
    }

    public static int heapifyBuildMaxHeap(int[] arr) {
        int swaps = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            swaps += verifyMaxHeap(arr, arr.length, i);
        }
        return swaps;
    }

    public static int verifyMaxHeap(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int swaps = 0;

        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            swaps++;
            swaps += verifyMaxHeap(arr, n, largest);
        }
        return swaps;
    }

    public static int buildMaxHeapByInsertion(int[] arr) {
        int swaps = 0;
        for (int i = 1; i < arr.length; i++) {
            int childIndex = i;
            while (childIndex > 0 && arr[childIndex] > arr[(childIndex - 1) / 2]) {
                int temp = arr[childIndex];
                arr[childIndex] = arr[(childIndex - 1) / 2];
                arr[(childIndex - 1) / 2] = temp;
                swaps++;
                childIndex = (childIndex - 1) / 2;
            }
        }
        return swaps;
    }
}
