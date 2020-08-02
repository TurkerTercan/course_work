import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The class for removedLinkedList. I implemented all necessary methods and fields for a linkedList
 * but also it has addNode and removeNode methods to keep Nodes
 * @param <E> GenericType
 * @author Türker Tercan 171044032
 */
public class RemovedLinkedList<E> extends AbstractList<E> {
    /**
     * Head reference
     */
    private Node<E> head;
    /**
     * Tail reference
     */
    private Node<E> tail;
    /**
     * How many elements in the list
     */
    private int size;

    /**
     * Basic constructor
     */
    public RemovedLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Gets element in specific index
     * @param index position
     * @return element E
     */
    @Override
    public E get(int index) {
        return new MyListIterator(index).next();
    }

    /**
     * List Size
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds node into the list
     * @param item Node
     */
    public void addNode(Node<E> item)
    {
        new MyListIterator(0).addNode(item);
    }

    /**
     * Removes a node from list
     * @return Node
     */
    public Node<E> removeNode()
    {
        MyListIterator iter = new MyListIterator(0);
        if(size <= 0)
            throw new NoSuchElementException();
        iter.next();
        return iter.removeNode();
    }

    /**
     * Basic ListIterator class but also it has removeNode() and addNode() methods
     */
    private class MyListIterator implements ListIterator<E>
    {
        /**
         * NextItem Node reference
         */
        private Node<E> lastItemReturned;
        /**
         * The item that is returned from next() or previous()
         */
        private Node<E> nextItem;
        /**
         * Index value
         */
        private int index;

        /**
         * Basic constructor
         * @param i index
         */
        public MyListIterator(int i)
        {
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            index = 0;
            nextItem = head;
            lastItemReturned = null;

            for (index = 0; index < i; index++)
                nextItem = nextItem.next;
        }

        /**
         * Adds a Node to the list
         * @param node given Node
         */
        public void addNode(Node<E> node)
        {
            if( head == null )
            {
                head = node;
                node.prev = null;
                node.next = null;
                size++;
            }
            else if( nextItem == head )
            {
                node.next = head;
                node.prev = null;
                head = node;
                size++;
            }
            else if( nextItem == null )
            {
                node.prev = tail;
                node.next = null;
                tail = node;
                size++;
                index++;
            }
            else
            {
                node.next = nextItem;
                node.prev = nextItem.prev;
                nextItem.prev.next = node;
                nextItem.next.prev = node;
                size++;
                index++;
            }
        }

        /**
         * Removes a node from the list
         * @return Node
         * @throws  IllegalStateException if lastItemReturned is null
         */
        public Node<E> removeNode()
        {
            if (lastItemReturned == null)
                throw new IllegalStateException();
            if (size == 1) {
                head = null;
                tail = null;
                size--;
            } else if (lastItemReturned.prev == null) {
                lastItemReturned.next.prev = null;
                head = lastItemReturned.next;
                size--;
            } else if (lastItemReturned.next == null) {
                lastItemReturned.prev.next = null;
                tail = lastItemReturned.prev;
                size--;
                index--;
            } else {
                lastItemReturned.next.prev = lastItemReturned.prev;
                lastItemReturned.prev.next = lastItemReturned.next;
                size--;
                index--;
            }
            lastItemReturned.next = null;
            lastItemReturned.prev = null;
            return lastItemReturned;
        }

        /**
         * Checks there is a node after nextItem
         * @return nextItem is null or not
         */
        @Override
        public boolean hasNext()  {
            return nextItem != null;
        }

        /**
         * Goes the next node and returns the element
         * @return element
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            index++;
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            return lastItemReturned.data;
        }

        /**
         * Checks if nextItem can go previous node
         * @return  if there is a node before it
         */
        @Override
        public boolean hasPrevious() {
            if( nextItem == null && size != 0)
                return true;
            if (nextItem != null){
                return nextItem.prev != null;
            }
            return false;
        }

        /**
         * Goes to the node before nextItem and returns nextItem's data
         * @return nextItem.data
         */
        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            if (nextItem == null)
                nextItem = tail;
            else
                nextItem = nextItem.prev;
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }
        /**
         * Index that will come
         * @return index + 1
         */
        @Override
        public int nextIndex() {
            return index + 1;
        }

        /**
         * Index that nextItem passed
         * @return index - 1
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * It removes lastItemReturned and store that Node in removed LinkedList
         * @throws IllegalStateException if lastItemReturned is null
         */
        @Override
        public void remove() {
            if (lastItemReturned == null)
                throw new IllegalStateException();
            if (size == 1) {
                lastItemReturned = null;
                size--;
            } else if (lastItemReturned.prev == null) {
                lastItemReturned.next.prev = null;
                head = lastItemReturned.next;
                size--;
            } else if (lastItemReturned.next == null) {
                lastItemReturned.prev.next = null;
                tail = lastItemReturned.prev;
                size--;
                index--;
            } else {
                lastItemReturned.next.prev = lastItemReturned.prev;
                lastItemReturned.prev.next = lastItemReturned.next;
                size--;
                index--;
            }
        }

        /**
         * Sets lastItemReturned.data to newData
         * @throws IllegalStateException if lastItemReturned is null
         * @param newData the new data that will be changed to
         */
        @Override
        public void set(E newData) {
            if (lastItemReturned == null)
                throw new IllegalStateException();
            lastItemReturned.data = newData;
        }

        /**
         * Adds an element to list. Checks removed list if there is node inside of it, does not create a node and uses it
         * @param obj the item that will be added
         */
        @Override
        public void add(E obj) {
            if (head == null) {
                head = new Node<>(obj);
                tail = head;
                size++;
            } else if (nextItem == head) {
                Node<E> newNode = new Node<>(obj);
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                size++;
            } else if (nextItem == null) {
                Node<E> newNode = new Node<>(obj);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                size++;
                index++;
            } else {
                Node<E> newNode = new Node<>(obj);
                newNode.next = nextItem;
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                nextItem.prev = newNode;
                size++;
                index++;
            }
        }
    }
}
