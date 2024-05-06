public class Main {

    public static void main(String[] args) {
        int[] randomArray = generateRandomArray(1000);
        int[] sortedArray = generateSortedArray(1000);

        processArray("Shuffled Array", randomArray);
        processArray("\nOrdered Array", sortedArray);
    }

    private static void processArray(String description, int[] array) {
        System.out.println(description + ": ");
        HeapSort.heapSortHeapify(array);
        HeapSort.heapSortInsert(array);
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
}
