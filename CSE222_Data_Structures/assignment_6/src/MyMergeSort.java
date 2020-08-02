import java.util.LinkedList;
import java.util.ListIterator;

public class MyMergeSort {
    public static <T extends Comparable<T>> void sort(LinkedList<T> table) {
        T[] arr = (T[]) table.toArray(new Comparable[table.size()]);
        pre_merge(arr);
        ListIterator<T> iter = table.listIterator();
        int i = 0;
        while (iter.hasNext()){
            iter.next();
            iter.set(arr[i++]);
        }
    }

    private static <T extends Comparable<T>> void pre_merge(T[] table) {
        if (table.length > 1) {
            int half = table.length / 2;
            T[] left = (T[]) new Comparable[half];
            T[] right = (T[]) new Comparable[table.length - half];
            System.arraycopy(table,0,left,0,half);
            System.arraycopy(table, half, right, 0, table.length - half);

            pre_merge(left);
            pre_merge(right);

            merge(table,left,right);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] output, T[] left,
                                                        T[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length ){
            if (left[leftIndex].compareTo(right[rightIndex]) < 0)
                output[currentIndex++] = left[leftIndex++];
            else
                output[currentIndex++] = right[rightIndex++];
        }
        while(leftIndex < left.length) {
            output[currentIndex++] = left[leftIndex++];
        }
        while(rightIndex < right.length) {
            output[currentIndex++] = right[rightIndex++];
        }
    }
}
