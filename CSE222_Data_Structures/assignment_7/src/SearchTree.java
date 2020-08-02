/**
 * SearchTree interface from my book
 * @param <E> generic
 */
public interface SearchTree<E> {
    /**
     * Inserts item where it belongs in the tree.
     * @param item E element
     * @return true if item is inserted, false if isn't (already in tree)
     */
    boolean add(E item);

    /**
     * Returns true if target is found in the tree
     * @param target E element
     * @return if target is the tree
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data in the node that is equal to target.
     * if no such node is found, returns null
     * @param target E element
     * @return E element reference
     */
    E find(E target);

    /**
     * Removes target (if found) from tree and returns it; otherwise, returns null
     * @param target E element
     * @return E element that is deleted
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns true; otherwise, returns false
     * @param target E element
     * @return if it removed
     */
    boolean remove(E target);

}
