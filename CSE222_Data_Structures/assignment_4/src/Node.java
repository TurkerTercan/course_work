/**
 * The Node class is implemented to keep data and track of the list
 * @param <E> Generic Type
 * @author Türker Tercan 171044032
 */
public class Node<E>{
    /**
     * data
     */
    E data;
    /**
     * NextNode reference
     */
    Node<E> next;

    /**
     * Previous Node reference
     */
    Node<E> prev;

    /**
     * Basic Constructor
     * @param dataItem element
     */
    public Node(E dataItem)
    {
        data = dataItem;
        next = null;
        prev = null;
    }
}