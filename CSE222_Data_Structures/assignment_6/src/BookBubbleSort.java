/** Implements the bubble sort algorithm. */
public class BookBubbleSort {
    /** Sort the array using bubble sort algorithm.
     * pre: table contains Comparable objects
     * post: table is sorted
     * @param table The array to be sorted
     */

    public static <T extends Comparable<T>> void sort(T[] table) {
        int pass = 1;
        boolean exchanges = false;
        do {
            // Invariant: Element after table.length - pass + 1 are in place
            exchanges = false;
            // Compare each pair of adjacent elements
            for (int i = 0; i < table.length - pass; i++) {
                if (table[i].compareTo(table[i + 1]) > 0) {
                    //Exchange pair
                    T temp = table[i];
                    table[i] = table[i+1];
                    table[i+1] = temp;
                    exchanges = true;
                }
            }
            pass++;
        }while (exchanges);
    }
}
