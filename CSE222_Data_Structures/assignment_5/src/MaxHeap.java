import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * MaxHeap class implement heap by using ArrayList as described in my book.
 * MaxHeap class to handle the ArrayList heap operations.
 * The key of heap will be "number of people" this time. So, the age which the
 * highest number of people is at, will be at the root
 * @author Türker Tercan
 * @param <E> Generic that extends DataInterface
 */
public class MaxHeap<E extends DataInterface<E>> {
    /**
     * Comparator class to compare to compare two objects of class E
     * @param <E> Generic that extends DataInterface
     */
    private static class MyComparator<E extends DataInterface<E>> implements Comparator<E> {
        /**
         * Compares two generic and returns result
         * @param o1 Generic one
         * @param o2 Generic two
         * @return extraction of two objects
         */
        @Override
        public int compare(E o1, E o2) {
            return o1.getCount() - o2.getCount();
        }
    }

    /** An ArrayList to hold the data */
    private final ArrayList<E> table;
    /** An optional object that implements the Comparator interface
     * by providing a compare method */
    private final Comparator<E> comparator;

    /**
     * Basic Constructor
     */
    public MaxHeap() {
        table = new ArrayList<>();
        comparator = new MyComparator<>();
    }

    /**
     * Construct the heap the given comparators compare method
     * @param comp given comparator
     */
    public MaxHeap(Comparator<E> comp) {
        table = new ArrayList<>();
        comparator = comp;
    }

    /**
     * Adds new element to heap
     * if the elements is already in the heap, increases it's size by one
     * otherwise, add the element end of the list
     * post: The value in the root is the biggest by decided with comparator
     * @param data the element that will be added
     */
    public void add(E data) {
        int temp;
        if ((temp = getIndex(data.getValue())) != -1) {
            table.get(temp).increase();
            int child = temp;
            int parent = (child - 1) / 2;

            while (parent >= 0 && compare(parent, child) < 0) {
                swap(parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        } else {
            table.add(data);
            int child = table.size() - 1;
            int parent = (child - 1) / 2;

            while (parent >= 0 && compare(parent, child) < 0) {
                swap(parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    /**
     * Removes the element from heap
     * if the element has more than one size, decreases it's size by one and
     * checks if it causes any changes in the heap
     * otherwise, remove element from the heap and reorder the heap
     * @param age integer that will be removed
     */
    public void remove(int age) {
        if (table.isEmpty() || getIndex(age) == -1)
            throw new NoSuchElementException();
        int index = getIndex(age);
        if (!table.get(index).isAlone()) {
            table.get(index).decrease();
        } else {
            // If only one item then remove it
            if (table.size() == 1) {
                table.remove(index);
                return;
            }
            // Remove the last item from the ArrayList and place it into
            // the index is found
            table.set(index, table.remove(table.size() - 1));
        }
        int parent = index;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= table.size()) {
                break; // Out of heap
            }
            int rightChild = leftChild + 1;
            int maxChild = leftChild; // Assume leftChild is bigger
            // See whether rightChild is smaller
            if (rightChild < table.size() && compare(leftChild, rightChild) < 0 ) {
                maxChild = rightChild;
            }
            // maxChild is the index of the larger child
            // Move bigger child up heap if necessary
            if (compare(parent, maxChild) < 0) {
                swap(parent, maxChild);
                parent = maxChild;
            }
            else { // Heap property is restored
                break;
            }
        }
    }

    /**
     * Gets index of the age
     * @param age integer that will be searching for
     * @return specific position
     */
    private int getIndex(int age) {
        for (int i = 0 ; i < table.size(); i++) {
            if (table.get(i).getValue() == age)
                return i;
        }
        return -1;
    }

    /**
     * Compare two indexes with comparator compare method if it not null
     * @param left left element
     * @param right right element
     * @return extraction of two elements
     */
    private int compare(int left, int right) {
        if (comparator != null) {
            return comparator.compare(table.get(left), table.get(right));
        } else {
            return table.get(left).compareTo(table.get(right));
        }
    }

    /**
     * Swaps two elements
     * @param left left element
     * @param right right element
     */
    private void swap(int left, int right) {
        E temp = table.get(left);
        table.set(left, table.get(right));
        table.set(right, temp);
    }

    /**
     * Finds the element in the ArrayList
     * @param data element that is searching for
     * @return element
     */
    public E find(E data) {
        if (getIndex(data.getValue()) == -1)
            throw new NoSuchElementException();

        return table.get(getIndex(data.getValue()));
    }

    /**
     * Counts all elements younger than given age
     * @param age given integer
     * @return count of elements is smaller than given integer
     */
    public int youngerThan(int age) {
        int count = 0;
        for (E temp : table)
            if (temp.getValue() < age)
                count += temp.getCount();
        return count;
    }

    /**
     * Counts all elements older than given age
     * @param age given integer
     * @return count of elements is larger than given integer
     */
    public int olderThan(int age) {
        int count = 0;
        for (E temp : table)
            if (temp.getValue() > age)
                count += temp.getCount();
        return count;
    }

    /**
     * Writes all elements of heap
     * @return String that stores all elements
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E temp : table) {
            sb.append(temp.toString()).append("\n");
        }
        return sb.toString();
    }
}
