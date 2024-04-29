import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = {12, 34, 54, 2, 3};
        System.out.println("Original Array: " + Arrays.toString(array));
        shellSort(array);
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void shellSort(int[] array) {
        int n = array.length;

        // Start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements array[0..gap-1] are already in gapped order
            // keep adding one more element until the entire array is gap sorted
            for (int i = gap; i < n; i++) {
                // add array[i] to the elements that have been gap sorted
                // save array[i] in temp and make a hole at position i
                int temp = array[i];

                // shift earlier gap-sorted elements up until the correct location for array[i] is found
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                // put temp (the original array[i]) in its correct location
                array[j] = temp;
            }
        }
    }
}
