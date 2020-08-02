import java.util.*;

/**
 * My implemented deque class. It has 2 LinkedList class which are i implemented. One of them it stores and other one stores removed
 * nodes. If we need a node, instead of creating a new one, we can use a Node in removed list.
 * @param <E> Generic type
 * @author Türker Tercan 171044032
 */
public class MyDequeClass<E> extends AbstractCollection<E> implements Deque<E> {
    /**
     * Elements in deque
     */
    private final MyLinkedList<E> mylist;

    /**
     * It's the list that to keep Nodes are removed.
     */
    private final RemovedLinkedList<E> removed;

    /**
     * My LinkedList class, it contains all necessary methods and variables for a linked list. And keeps a reference of removed node,
     * to store the nodes are removed and adds if it needs them.
     * @param <E> Generic Type
     */
    private static class MyLinkedList<E> extends AbstractList<E> {
        /**
         * Head reference
         */
        private Node<E> head;
        /**
         * Tail reference
         */
        private Node<E> tail;
        /**
         * RemovedLinkedList reference
         */
        private final RemovedLinkedList<E> removed;

        /**
         * How many elements in the list
         */
        private int size;

        /**
         * Basic constructor
         * @param rmv RemovedLinkedList reference
         */
        public MyLinkedList(RemovedLinkedList<E> rmv) {
            size = 0;
            head = null;
            tail = null;
            removed = rmv;
        }

        /**
         * My ListIterator, it contains all necessary methods and fields. Also add and remove method uses
         * removed.addNode and removed.removeNode methods
         */
        private class MyListIterator implements ListIterator<E> {
            /**
             * NextItem Node reference
             */
            private Node<E> nextItem;

            /**
             * The item that is returned from next() or previous()
             */
            private Node<E> lastItemReturned;

            /**
             * Index value
             */
            private int index;

            /**
             * Basic constructor
             * @param i index
             */
            public MyListIterator(int i) {
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
             * Checks there is a node after nextItem
             * @return nextItem is null or not
             */
            public boolean hasNext() {
                return nextItem != null;
            }

            /**
             * Goes the next node and returns the element
             * @return element
             */
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
            public int nextIndex() {
                return index + 1;
            }

            /**
             * Index that nextItem passed
             * @return index - 1
             */
            public int previousIndex() {
                return index - 1;
            }

            /**
             * It removes lastItemReturned and store that Node in removed LinkedList
             * @throws IllegalStateException if lastItemReturned is null
             */
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
                lastItemReturned.prev = null;
                lastItemReturned.next = null;
                removed.addNode(lastItemReturned);
            }

            /**
             * Adds an element to list. Checks removed list if there is node inside of it, does not create a node and uses it
             * @param obj the item that will be added
             */
            public void add(E obj) {
                if (head == null) {
                    if(removed.size() > 0){
                        head = removed.removeNode();
                        head.data = obj;
                    }
                    else
                        head = new Node<>(obj);
                    tail = head;
                    size++;
                } else if (nextItem == head) {
                    Node<E> newNode;
                    if(removed.size() > 0) {
                        newNode = removed.removeNode();
                        newNode.data = obj;
                    }
                    else
                        newNode = new Node<>(obj);
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                    size++;
                } else if (nextItem == null) {
                    Node<E> newNode;
                    if(removed.size() > 0)
                        newNode = removed.removeNode();
                    else
                        newNode = new Node<>(obj);
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                    size++;
                    index++;
                } else {
                    Node<E> newNode;
                    if(removed.size() > 0)
                        newNode = removed.removeNode();
                    else
                        newNode = new Node<>(obj);
                    newNode.next = nextItem;
                    newNode.prev = nextItem.prev;
                    nextItem.prev.next = newNode;
                    nextItem.prev = newNode;
                    size++;
                    index++;
                }
            }

            /**
             * Sets lastItemReturned.data to newData
             * @throws IllegalStateException if lastItemReturned is null
             * @param newData the new data that will be changed to
             */
            public void set(E newData) {
                if (lastItemReturned == null)
                    throw new IllegalStateException();
                lastItemReturned.data = newData;
            }
        }

        /**
         * List size
         * @return size
         */
        @Override
        public int size() {
            return size;
        }

        /**
         * Returns an element in specific position
         * @param index position
         * @return element E
         */
        @Override
        public E get(int index) {
            return listIterator(index).next();
        }

        /**
         * Adds an element in first index of the list
         * @param e element
         */
        public void addFirst(E e) {
            MyListIterator temp = new MyListIterator(0);
            temp.add(e);
        }

        /**
         * Adds an element in last index of the list
         * @param e element
         */
        public void addLast(E e) {
            listIterator(size).add(e);
        }

        /**
         * Adds element in first index of the list. It is equivalent to addFirst()
         * @param e element
         * @return always true
         */
        public boolean add(E e) {
            addFirst(e);
            return true;
        }

        /**
         * Returns an iterator in the beginning of the list
         * @return Iterator
         */
        public Iterator<E> iterator() {
            return new MyListIterator(0);
        }

        /**
         * Returns an iterator in the ending of the list and elements are reversed
         * @return Iterator
         */
        public Iterator<E> descendingIterator() {
            return new MyListIterator(size);
        }

        /**
         * It creates and returns ListIterator which i implemented
         * @param i index
         * @throws IndexOutOfBoundsException if index is not valid
         * @return MyListIterator
         */
        @Override
        public ListIterator<E> listIterator(int i) {
            if (i < 0 || i > size)
                throw new IndexOutOfBoundsException("Invalid Index " + i);
            return new MyListIterator(i);
        }

        /**
         * It creates at the beginning of the list and returns a ListIterator which i implemented
         * @return MyListIterator
         */
        @Override
        public ListIterator<E> listIterator() {
            return new MyListIterator(0);
        }
    }

    /**
     * Basic Constructor
     */
    public MyDequeClass()
    {
        removed = new RemovedLinkedList<>();
        mylist = new MyLinkedList<>(removed);
    }

    /**
     * Adds an element to list. Equivalent to addLast()
     * @param e element
     * @return always true
     */
    public boolean add(E e)
    {
        addLast(e);
        return true;
    }

    /**
     * It creates and returns an iterator
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return mylist.iterator();
    }

    /**
     * It creates an iterator at end of the list and returns it
     * @return iterator
     */
    @Override
    public Iterator<E> descendingIterator() {
        return mylist.descendingIterator();
    }

    /**
     * Adds an element to first index of the list
     * @param e element
     */
    @Override
    public void addFirst(E e) {
        mylist.addFirst(e);
    }

    /**
     * Adds an element to last index of the list
     * @param e element
     */
    @Override
    public void addLast(E e) {
        mylist.addLast(e);
    }

    /**
     * Adds an element to first index of the list
     * @param e element
     * @return always true
     */
    @Override
    public boolean offerFirst(E e) {
        mylist.addFirst(e);
        return true;
    }

    /**
     * Adds an element to last index of the list
     * @param e element
     * @return always true
     */
    @Override
    public boolean offerLast(E e) {
        mylist.addLast(e);
        return true;
    }

    /**
     * Removes the element in first index of the list
     * @return first element
     */
    @Override
    public E removeFirst() {
        if(this.size() == 0)
            throw new NoSuchElementException();
        Iterator<E> iter = mylist.iterator();
        E temp = iter.next();
        iter.remove();
        return temp;
    }

    /**
     * Removes the element in last index of the list
     * @return last element
     */
    @Override
    public E removeLast() {
        if(this.size() == 0)
            throw new NoSuchElementException();
        ListIterator<E> iter = mylist.listIterator(mylist.size());
        E temp = iter.previous();
        iter.remove();
        return temp;
    }

    /**
     * It returns first element in the list. If list is empty return null
     * @return first element
     */
    @Override
    public E pollFirst() {
        if (mylist.size() == 0)
            return null;
        ListIterator<E> iter = mylist.listIterator(0);
        return iter.next();
    }

    /**
     * It returns last element in the list. If list is empty return null
     * @return last element
     */
    @Override
    public E pollLast() {
        if(mylist.size() == 0)
            return null;
        ListIterator<E> iter = mylist.listIterator(mylist.size());
        return iter.previous();
    }

    /**
     * Returns first element in the list
     * @return first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() {
        if (mylist.size() == 0)
            throw new NoSuchElementException();
        return mylist.get(0);
    }

    /**
     * Returns last element in the list
     * @return last element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getLast() {
        if (mylist.size() == 0)
            throw new NoSuchElementException();
        return mylist.get(mylist.size() - 1);
    }

    /**
     * It gets first element in the list. It returns null if list is empty
     * @return first element
     */
    @Override
    public E peekFirst() {
        if (mylist.size() == 0)
            return null;
        return mylist.get(0);
    }

    /**
     * It gets last element in the last. If the list is empty, returns null
     * @return last element
     */
    @Override
    public E peekLast() {
        if (mylist.size() == 0)
            return null;
        return mylist.get(mylist.size() - 1);
    }

    /**
     * It starts in the beginning of the list. If finds the object that is given, removes it and returns true. If it does not, return false
     * @param o the object in the list
     * @return true if element is in the list
     */
    @Override
    public boolean removeFirstOccurrence(Object o) {
        ListIterator<E> iter = mylist.listIterator(0);
        while(iter.hasNext()) {
            if (iter.next().equals(o)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * It starts in the ending of the list. It finds the object that is given, removes it and returns true. If it does not, return false
     * @param o the object in the list
     * @return true if element is in the list
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        ListIterator<E> iter = mylist.listIterator(mylist.size() - 1);
        while (iter.hasPrevious()){
            if (iter.previous().equals(o)){
                iter.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an element to last index of the list. Equivalent to offerLast()
     * @param e element
     * @return always true
     */
    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    /**
     * It removes first index of the list. Equivalent to removeFirst()
     * @return element in the first index
     */
    @Override
    public E remove() {
        return removeFirst();
    }

    /**
     * It gets first element in the list. Equivalent to pollFirst()
     * @return first element
     */
    @Override
    public E poll() {
        return pollFirst();
    }

    /**
     * Gets first element. Equivalent to getFirst()
     * @return first element
     */
    @Override
    public E element() {
        return getFirst();
    }

    /**
     * Returns first element in the list. Equivalent to peekFirst()
     * @return first element
     */
    @Override
    public E peek() {
        return peekFirst();
    }

    /**
     * Pushes new element to list. Equivalent to addFirst()
     * @param e element
     */
    @Override
    public void push(E e) {
        addFirst(e);
    }

    /**
     * Removes first element in the list. Equivalent to removeFirst()
     * @return e element
     */
    @Override
    public E pop() {
        return removeFirst();
    }

    /**
     * List size
     * @return size
     */
    @Override
    public int size() {
        return mylist.size();
    }

    /**
     * Method that writes all element in the list
     * @return string
     */
    @Override
    public String toString() {
        Iterator<E> iter = mylist.iterator();
        StringBuilder build = new StringBuilder();
        while( iter.hasNext() )
        {
            E temp = iter.next();
            build.append(temp).append(" ");
        }
        return build.toString();
    }
}