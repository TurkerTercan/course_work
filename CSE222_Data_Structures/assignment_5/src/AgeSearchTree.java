import java.util.NoSuchElementException;

/**
 * We need to record the number people in each age for a population.
 * Extends BinarySearchTree class from my book
 * @param <E> generic that implements DataInterface
 */
public class AgeSearchTree<E extends DataInterface<E>> extends BinarySearchTree<E>{
    /** boolean to control is item in the tree or not */
    private boolean isFound = false;
    /** size of the tree */
    private int size;
    /** Basic constructor */
    public AgeSearchTree() {
        super();
        size = 0;
    }

    /**
     * Starter method add
     * pre: The object to insert must implement the DataInterface
     * @param item The object being inserted
     * @return true of the object is inserted, false if the object already exist in the tree
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        size++;
        return addReturn;
    }

    /**
     * Recursive add method
     * post : The data field addReturn is set true if the item is added to the tree,
     *        false if the item is already in the tree
     *        if item is found in the tree, increases it's size
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree - insert it
            addReturn = true;
            return new Node<>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            localRoot.data.increase();
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Starter method delete
     * post: if object has just 1 member delete it from
     * @param target the object to be deleted
     * @return The object deleted from the tree or null
     *         if the object was not in the tree
     */
    @Override
    public E delete(E target) {
        root = delete(root, target);
        if (deleteReturn != null)
            size--;
        return deleteReturn;
    }

    /**
     * Recursive delete method
     * post: if node's data has more than 1 size, decrease data; otherwise, delete the node
     *       deleteReturn is equal to the deleted item
     *       as it was stored in the tree or null
     *       if the item was not found
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree
            deleteReturn = null;
            return null;
        }
        // Searching item to delete
        int comp = item.compareTo(localRoot.data);
        if (comp < 0) {
            // item is smaller
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if (comp > 0) {
            // item is larger
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else {
            // item is equal
            deleteReturn = localRoot.data;
            // if node's data has 1 size
            if (localRoot.data.isAlone()) {
                // Remove the node
                if (localRoot.left == null) {
                    // If there is no left child, return right child
                    // which can also be null
                    return localRoot.right;
                }
                else if (localRoot.right == null) {
                    // If there is no right child, return left child
                    return localRoot.left;
                }
                else {
                    // Replace the data with inorder predecessor
                    if (localRoot.left.right == null) {
                        // Left child has no right child
                        // Replace the data with the data in the left child
                        localRoot.data = localRoot.left.data;
                        // Replace the left child with its left child
                        localRoot.left = localRoot.left.left;
                    }
                    else {
                        // Searches for largest child in left child
                        localRoot.data = findLargestChild(localRoot.left);
                    }
                }
            }
            // If node's data has more than 1 size
            else {
                localRoot.data.decrease();
            }
            return localRoot;
        }
    }

    /**
     * Remove method
     * post: if target has more than 1 size, decrease it; otherwise, remove node
     * @param target The element to be deleted
     * @return if element is found, return true; otherwise, false
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /**
     * Starter youngerThan method
     * It counts the people that is younger than given age
     * @param age the integer that is searching
     * @return count of people younger than that age
     */
    public int youngerThan(int age) {
        isFound = false;
        int temp = youngerThan(root, age);
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return temp;
    }

    /**
     * Recursive method younger than
     * This method traverses just necessary nodes
     * @param localRoot root of the current subtree
     * @param age given integer
     * @return total count of younger than given integer
     */
    private int youngerThan(Node<E> localRoot, int age) {
        if (localRoot == null)
            return 0;
        if (localRoot.data.getValue() == age) {
            isFound = true;
            return countChildren(localRoot.left);
        }
        else if (localRoot.data.getValue() > age)
            return youngerThan(localRoot.left, age);
        else {
            return localRoot.data.getCount() + youngerThan(localRoot.left, age)
                    + youngerThan(localRoot.right, age);
        }
    }

    /**
     * Count of children
     * @param localRoot root of current subtree
     * @return count of children
     */
    private int countChildren(Node<E> localRoot) {
        if (localRoot == null)
            return 0;
        return localRoot.data.getCount() + countChildren(localRoot.left)
                + countChildren(localRoot.right);
    }

    /**
     * Older than method
     * @param age given integer that will searching for
     * @return count of people that is older than given age
     */
    public int olderThan(int age) {
        return size - youngerThan(age) - 1;
    }

    /**
     * Prints all tree in preOrderTraversal
     * @return tree's preOrderTraversal
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    /**
     * Recursive method preOrderTraversal
     * @param localRoot local root of the current subtree
     * @param sb StringBuilder
     */
    private void preOrderTraversal(Node<E> localRoot, StringBuilder sb) {
        if (localRoot == null) {
            sb.append("null").append("\n");
            return;
        }
        sb.append(localRoot.data.toString()).append("\n");
        preOrderTraversal(localRoot.left, sb);
        preOrderTraversal(localRoot.right, sb);
    }


}
