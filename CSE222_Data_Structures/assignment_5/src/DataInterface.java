/**
 * To use AgeSearchTree and MaxHeap classes generic way,
 * I implemented an interface for AgeData class.
 * It has all necessary methods to do in two questions.
 * @param <E> Generic that is implements Comparable interface
 */
public interface DataInterface<E> extends Comparable<E>{
    /**
     * This method check the object has size more than one
     * @return true if object has 1 size; otherwise, false
     */
    boolean isAlone();

    /**
     * Increase the size of the object by one
     */
    void increase();

    /**
     * Decrease the size of the object by one
     */
    void decrease();

    /**
     * Gets object's value
     * @return value
     */
    int getValue();

    /**
     * Gets object's count
     * @return count
     */
    int getCount();
}
