import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of a Skip-List data structure
 * Modify the skip list implementation so that each
 * node in the lowest-level list keeps several elements
 * instead of just one element as in a B-tree node.
 * @param <E> Comparable object
 */
public class MySkipList<E extends Comparable<E>> implements SearchTree<E> {
    //Nested Class
    /**
     * Static class to contain data array and links
     * @param <E> The type of data stored. Must be Comparable
     */
    private static class SLNode<E extends Comparable<E>> {
        /** Links that connects other nodes to itself */
        SLNode<E>[] links;
        /** Array that contains data in the nodes */
        ArrayList<E> data;
        /** An integer value to contain how many numbers can be in the array */
        int max_size;

        /**
         * To create a node of level m and size of order
         * @param m The level of the node
         * @param order The size of data array
         */
        @SuppressWarnings("unchecked")
        public SLNode(int m, int order) {
            links = (SLNode<E>[]) new SLNode[m];
            data = new ArrayList<>();
            max_size = order;
        }

        /**
         * Method to check size is greater than minimum
         * @return return true if size greater than min value
         */
        public boolean isEnough() {
            return (max_size / 2) <= data.size();
        }

        /**
         * Method to check capacity reached
         * @return true if capacity is reached
         */
        public boolean capacityReached(){
            return max_size - 1 <= data.size();
        }

        /**
         * Method to add an element to data array
         * @param element The element will be added
         */
        public void add(E element) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).compareTo(element) > 0) {
                    data.add(i, element);
                    return;
                }
            }
            data.add(element);
        }

        /**
         * Method to remove the element in the list,
         * @param element The element will be removed
         * @return If there is no such element in array, return null
         */
        public E remove(E element) {
            int index = data.indexOf(element);
            if (index == -1)
                return null;
            return data.remove(index);
        }

        /**
         * Finds an object in the data ArrayList
         * @param target The item being sought
         * @return A reference to the object
         */
        public E find(E target) {
            int index = data.indexOf(target);
            if (index == -1)
                return null;
            return data.get(index);
        }

        /**
         * Override string method
         * @return Returns a string that represents the object
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int i = 0; i < data.size(); i++) {
                if (i != data.size() - 1)
                    sb.append(data.get(i)).append(", ");
                else
                    sb.append(data.get(i));
            }
            sb.append("}").append(links.length).append(": |");
            for (SLNode<E> link : links) {
                if (link == null)
                    sb.append("->").append("null");
                else {
                    sb.append("->").append(link.data.get(0));
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
    /** Size of the nodes in the skip-list */
    private int nodeSize;
    /** The maximum level of the skip-list */
    private int maxLevel;
    /** Smallest power of 2 that is greater than the current skip-list size */
    private int maxCap;
    /** The number of objects can be in a single node */
    private int order;
    /** Natural log of 2 */
    private static final double LOG2 = Math.log(2.0);
    /** Minimum possible integer value for the head */
    private static final int MIN = Integer.MIN_VALUE;
    /** Random number generator */
    private Random rand = new Random();

    /**
     * Basic constructor for my skip-list
     * @param order
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public MySkipList(int order) {
        this.order = order;
        size = 0;
        nodeSize = 0;
        maxLevel = 0;
        maxCap = computeMaxCap(maxLevel);
        head = new SLNode(maxLevel, MIN);
    }

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
                    && current.links[i].data.get(0).compareTo(target) < 0) {
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
     *         the target. If not found, null is returned.
     */
    @Override
    public E find(E target) {
        SLNode<E>[] pred = predecessors(target);
        if (pred[0].links != null
                && pred[0].links[0].data.contains(target)) {
            return pred[0].links[0].data.get(pred[0].links[0].data.indexOf(target));
        } else {
            return null;
        }
    }

    /**
     * Recompute the max capacity
     * @param level maxLevel of the skip-list
     * @return re-calculated max capacity
     */
    private int computeMaxCap(int level) {
        return (int) Math.pow(2, level) - 1;
    }

    /**
     * Insert the given item
     * @param item The item to add
     * @return true as the item is added
     */
    @Override
    public boolean add(E item) {
        size++;
        SLNode<E>[] pred = search(item);
        //If there is no node, create first node
        if (nodeSize == 0) {
            nodeSize++;
            if (nodeSize > maxCap) {
                maxLevel++;
                maxCap = computeMaxCap(maxLevel);
                head.links = Arrays.copyOf(head.links, maxLevel);
                pred = Arrays.copyOf(pred, maxLevel);
                pred[maxLevel - 1] = head;
            }
            SLNode<E> newNode = new SLNode<E>(logRandom(), order);
            for (int i = 0; i < newNode.links.length; i++) {
                newNode.links[i] = pred[i].links[i];
                pred[i].links[i] = newNode;
            }
            newNode.add(item);
            return true;
        }
        if (pred[0] == head) {
            //The element to be added less than first node's first element
            if (!head.links[0].capacityReached())
                head.links[0].add(item);
            else {
                nodeSize++;
                if (size > maxCap) {
                    maxLevel++;
                    maxCap = computeMaxCap(maxLevel);
                    head.links = Arrays.copyOf(head.links, maxLevel);
                    pred = Arrays.copyOf(pred, maxLevel);
                    pred[maxLevel - 1] = head;
                }
                SLNode<E> newNode = new SLNode<E>(1, order);
                newNode.add(item);

                for (int i = 0; i < newNode.links.length; i++) {
                    newNode.links[i] = pred[i].links[i];
                    pred[i].links[i] = newNode;
                }
                if (order % 2 == 1) {
                    for (int i = 0; i < order / 2; i++) {
                        newNode.add(newNode.links[0].data.remove(0));
                    }
                } else {
                    for (int i = 0; i < (order / 2) - 1; i++) {
                        newNode.add(newNode.links[0].data.remove(0));
                    }
                }
                return true;
            }
        }
        else if (!pred[0].capacityReached())
            pred[0].add(item);
        else {
            nodeSize++;
            if (nodeSize > maxCap) {
                maxLevel++;
                maxCap = computeMaxCap(maxLevel);
                head.links = Arrays.copyOf(head.links, maxLevel);
                pred = Arrays.copyOf(pred, maxLevel);
                pred[maxLevel - 1] = head;
            }
            SLNode<E> newNode = new SLNode<E>(logRandom(), order);
            pred[0].add(item);
            for (int i = 0; i < newNode.links.length; i++) {
                newNode.links[i] = pred[i].links[i];
                pred[i].links[i] = newNode;
            }
            if (order % 2 == 1) {
                //If order is an odd number
                for (int i = (order / 2) + 1; i < order; i++) {
                    newNode.add(pred[0].data.remove((order / 2) + 1));
                }
            } else {
                //If order is an even number
                for (int i = (order / 2); i < order ; i++) {
                    newNode.add(pred[0].data.remove((order / 2)));
                }
            }
        }
        return true;
    }

    /**
     * Removes an instance of the given item
     * @param item The item to remove
     * @return true if the item is removed, false if the item is not in the list
     */
    @Override
    public boolean remove(E item) {
        SLNode<E>[] pred = predecessors(item);
        if (pred[0].links[0] == null){
            return false;
        }

        if (!pred[0].links[0].data.contains(item)) {
            return false; //item is not in the list
        }

        else {
            size--;
            pred[0].links[0].data.remove(item);
            if (!pred[0].links[0].isEnough() && pred[0].links[0] != head.links[0]) {
                SLNode<E> deleteNode = pred[0];
                for (int i = 0; i < pred[0].links[0].data.size(); i++) {
                    deleteNode.add(deleteNode.links[0].data.remove(0));
                }
                if (deleteNode.data.size() < order) {
                    for (int i = 0; i < deleteNode.links.length; i++) {
                        if (pred[i].links[i] != null)
                            pred[i].links[i] = pred[i].links[i].links[i];
                    }
                } else {
                    if (order % 2 == 0) {
                        for (int i = (order / 2); i < order ; i++) {
                            pred[0].links[0].add(pred[0].data.remove(order / 2));
                        }
                    } else {
                        for (int i = (order / 2) + 1; i < order; i++) {
                            pred[0].links[0].add(pred[0].data.remove((order / 2) + 1));
                        }
                    }
                }
                return true;
            } else if (pred[0].links[0] == head.links[0] && head.links[0].data.size() == 0){
                SLNode<E> deleteNode = pred[0];
                for (int i = 0; i < deleteNode.links.length; i++) {
                    if (pred[i].links[i] != null)
                        pred[i].links[i] = pred[i].links[i].links[i];
                }
            }
        }
        return true;
    }

    /**
     * Search for an item in the list
     * Different than search method because it compares last element
     * of the data ArrayList
     * @param target The item being sought
     * @return An SLNode array which references the predecessors of
     * the target at each level.
     */
    @SuppressWarnings("unchecked")
    public SLNode<E>[] predecessors(E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null && current.links[i].data.size() != 0
                    && current.links[i].data.get(current.links[i].data.size() - 1)
                    .compareTo(target) < 0) {
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    @Override
    public boolean contains(E target) {
        return false;
    }


    @Override
    public E delete(E target) {
        return null;
    }

    /**
     * Method to generate a logarithmic distributed integer between
     * 1 and maxLevel. I.E. 1/2 of the values are 1, 1/4 are 2, etc.
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
     * Override toString method to print whole list
     * @return String that represents the object
     */
    @Override
    public String toString() {
        if (size == 0)
            return "Empty";
        StringBuilder sb = new StringBuilder();
        SLNode<E> itr = head;
        sb.append("Head ").append(maxLevel);
        int lineMaker = 0;
        for (int i = 0; i < itr.links.length; i++) {
            if (itr.links[i] == null || itr.links[i].data.size() == 0)
                sb.append("->").append("null");
            else
                sb.append("->").append(itr.links[i].data.get(0));
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
