import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of a Skip-List data structure
 * @param <E> Comparable
 */
public class SkipList<E extends Comparable<E>> implements SearchTree<E>{
    //Nested Class
    /**
     * Static class to contain data and links
     * @param <E> The type of data stored. Must be Comparable
     */
    private static class SLNode<E> {
        SLNode<E>[] links;
        E data;

        /**
         * Create a node of level m
         * @param m The level of the node
         * @param data The data to be stored
         */
        @SuppressWarnings("unchecked")
        public SLNode(int m, E data) {
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }

        /**
         * Override string method
         * @return Returns a string that represents the object
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(data.toString()).append(" ").append(links.length).append(": |");
            for (int i = 0; i < links.length; i++) {
                if (links[i] == null)
                    sb.append("->").append("null");
                else {
                    sb.append("->").append(links[i].data);
                }
            }
            sb.append("|");
            return sb.toString();
        }
    }
    //Data fields
    /** Head of the skip-list */
    private SLNode<E> head;
    /** Size of the skip-list */
    private int size;
    /** The maximum level of the skip-list */
    private int maxLevel;
    /** Smallest power of 2 that is greater than the current skip-list size */
    private int maxCap;
    /** Natural log of 2 */
    private static final double LOG2 = Math.log(2.0);
    /** Minimum possible integer value for the head */
    private static final int MIN = Integer.MIN_VALUE;
    /** Random number generator */
    private Random rand = new Random();

    //Constructor
    /**
     * Search for an item in the list
     * Basic constructor for skip-list
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public SkipList() {
        size = 0;
        maxLevel = 0;
        maxCap = computeMaxCap(maxLevel);
        head = new SLNode(maxLevel, MIN);
    }

    //Methods

    /**
     * Search for an item in the list
     * @param target The item being sought
     * @return An SLNode array which references the predecessors of
     * the target at each level.
     */
    @SuppressWarnings("unchecked")
    private SLNode<E>[] search(E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0) {
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    /**
     * Find an object in the skip-list
     * @param target The item being sought
     * @return A reference to the object in the skip-list that matches
     *         the target. If not found, null is returned
     */
    public E find(E target) {
        SLNode<E>[] pred = search(target);
        if (pred[0].links != null
                && pred[0].links[0].data.compareTo(target) == 0) {
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }

    /**
     * Insert the given item
     * @param item The item to add
     * @return true as the item is added
     */
    public boolean add(E item) {
        size++;
        SLNode<E>[] pred = search(item);
        if (size > maxCap) {
            maxLevel++;
            maxCap = computeMaxCap(maxLevel);
            head.links = Arrays.copyOf(head.links, maxLevel);
            pred = Arrays.copyOf(pred, maxLevel);
            pred[maxLevel - 1] = head;
        }
        SLNode<E> newNode = new SLNode<E>(logRandom(), item);
        for (int i = 0; i < newNode.links.length; i++) {
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        return true;
    }

    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /**
     * Removes an instance of the given item
     * @param item The item to remove
     * @return true if the item is removed, false if the item is not in the list
     */
    public boolean remove(E item) {
        SLNode<E>[] pred = search(item);
        if (pred[0].links != null
                && pred[0].links[0].data.compareTo(item) != 0) {
            return false; //item is not in the list
        } else {
            size--;
            SLNode<E> deleteNode = pred[0];
            for (int i = 0; i < deleteNode.links.length; i++) {
                if (pred[i].links[i] != null)
                    pred[i].links[i] = pred[i].links[i].links[i];
            }
            return true;
        }
    }

    @Override
    public E delete(E target) {
        E temp = find(target);
        if (temp == null)
            return null;
        remove(temp);
        return temp;
    }


    /**
     * Method to generate a logarithmic distributed integer between
     * 1 and maxLevel. I.E. 1&2 of the values are 1, 1/4 are 2, etc.
     * @return a random logarithmic distributed int between 1 and maxLevel
     */
    private int logRandom() {
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1)
            k = maxLevel - 1;
        return maxLevel - k;
    }

    /**
     * Recompute the max cap
     * @param level maxLevel of the skip-list
     * @return re-calculated max capacity
     */
    private int computeMaxCap(int level) {
        return (int) Math.pow(2, level) - 1;
    }

    /**
     * Override toString method to print whole list
     * @return String that represents the object
     */
    public String toString() {
        if (size == 0)
            return "Empty";
        StringBuilder sb = new StringBuilder();
        SLNode<E> itr = head;
        sb.append("Head ").append(maxLevel);
        int lineMaker = 0;
        for (int i = 0; i < itr.links.length; i++) {
            if (itr.links[i] == null)
                sb.append("->").append("null");
            else {
                sb.append("->").append(itr.links[i].data);
            }
        }
        while (itr.links[0] != null) {
            itr = itr.links[0];
            sb.append(" --> ").append(itr.toString());
            lineMaker++;
            if (lineMaker == 10) {
                sb.append("\n");
                lineMaker = 0;
            }
        }
        return sb.toString();
    }
}
