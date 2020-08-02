import java.util.LinkedList;
import java.util.ListIterator;

/** My Quick sort algorithm which takes LinkedList as data*/
public class MyQuickSort {
   /**
    * It takes a LinkedList but get and set methods in linked list runs O(n) time. It disrupts the QuickSort's efficiency.
    * So i implemented such way that, it first converts linked list to an array. Firstly, sorts that array.
    * And then, set LinkedList to arrays' elements
    * @param table data stored
    * @param <T> Comparable generic
    */
   public static <T extends Comparable<T>> void sort(LinkedList<T> table) {
      T[] arr = (T[]) table.toArray(new Comparable[table.size()]);
      quickSort(arr, 0, table.size() - 1);
      ListIterator<T> iter = table.listIterator();
      int i = 0;
      while (iter.hasNext()){
         iter.next();
         iter.set(arr[i++]);
      }
   }

   /**
    * Sorts the table using the quickSort algorithm
    * @param table contains Comparable objects
    * @param first index that will be compared firstly
    * @param last index that will be compared lastly
    * @param <T> Comparable generic type
    */
   private static <T extends Comparable<T>> void quickSort(T[] table, int first, int last) {
      if (first < last) {
         int pivotIndex = partition(table, first, last);
         quickSort(table, first, pivotIndex - 1);
         quickSort(table, pivotIndex + 1, last);
      }
   }

   /**
    * Partition the table so that values from first to pivot are less than or equal
    * to the pivot, and values from pivot to last are greater than the pivot value
    * @param table contains Comparable objects
    * @param first index that will be compared firstly
    * @param last index that will be compared lastly
    * @param <T> <T> Comparable generic type
    * @return pivot index
    */
   private static <T extends Comparable<T>> int partition(T[] table, int first, int last) {
      sort3(table, first, last);
      swap(table, first, (first + last) / 2);

      T pivot = table[first];
      int startIndex = first;
      int endIndex = last;

      do {
         while ((startIndex < last) && pivot.compareTo(table[startIndex]) >= 0) {
            startIndex++;
         }

         while ((endIndex > 0) && pivot.compareTo(table[endIndex]) < 0) {
            endIndex--;
         }

         if (startIndex < endIndex) {
            swap(table, startIndex, endIndex);
         }
      }while (startIndex < endIndex);

      swap(table, first, endIndex);
      return endIndex;
   }

   /**
    * Swaps two elements from an array
    * @param table contains elements
    * @param first index that will be displaced
    * @param last index that will be displaced
    * @param <T> Generic type
    */
   private static <T> void swap(T[] table, int first, int last) {
      T temp = table[first];
      table[first] = table[last];
      table[last] = temp;
   }

   /**
    * Sort table[first], table[middle], table[last]
    * @param table contains comparable elements
    * @param first index of first element
    * @param last index of last element
    * @param <T> comparable generic type
    */
   private static <T extends Comparable<T>> void sort3(T[] table, int first, int last) {
      int middle = (first + last) / 2;
      if (table[middle].compareTo(table[first]) < 0) {
         swap(table, first, middle);
      }
      if (table[last].compareTo(table[first]) < 0) {
         swap(table,middle, last);
      }
      if (table[middle].compareTo(table[first]) < 0) {
         swap(table, first, middle);
      }
   }
}
