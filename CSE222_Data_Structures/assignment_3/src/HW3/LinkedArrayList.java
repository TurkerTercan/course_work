/**
 * Package for homework 3
 */
package HW3;

import java.util.*;

/**
 * @author Türker Tercan
 * LinkedArrayList class it is simply like java double linked list but also 
 * every node has constant capacity arrays
 */
public class LinkedArrayList<E> extends AbstractList<E> implements List<E>{
	
	/**
	 * Head reference
	 */
	private Node<E> head;
	
	/**
	 * Tail reference
	 */
	private Node<E> tail;
	
	/**
	 * Node size
	 */
	private int size;
	
	private int nodeCount;
	
	/**
	 * Basic constructor
	 */
	public LinkedArrayList() {
		size = 0;
		nodeCount = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * 
	 * @author Türker Tercan
	 * Node class has data array with constant capacity.
	 * it is used to store data and next, previous node references
	 * @param <E> not primitive object type
	 */
	private static class Node<E>
	{
		/**
		 * Data array
		 */
		private E[] data;
		
		/**
		 * How many elements are used
		 */
		private int used;
		
		/**
		 * Next node location
		 */
		private Node<E> next;
		
		/**
		 * Previous node location
		 */
		private Node<E> previous;
		
		/**
		 * Maximum capacity;
		 */
		private static final int CAPACITY = 4;
		
		/**
		 * Basic constructor
		 * @param dataItem that will be stored first element in array
		 */
		@SuppressWarnings("unchecked")
		private Node(E dataItem)
		{
			data = (E[]) new Object[CAPACITY];
			used = 0;
			data[used++] = dataItem;
			next = null;
			previous = null;
		}
		
		/**
		 * Constructor
		 * @param dataItem that will be stored first element in array
		 * @param nextNode next node location
		 * @param prevNode previous node location
		 */
		@SuppressWarnings("unchecked")
		private Node(E dataItem, Node<E> nextNode, Node<E> prevNode)
		{
			data = (E[]) new Object[CAPACITY];
			used = 0;
			data[used++] = dataItem;
			next = nextNode;
			previous = prevNode;
		}
		
		/**
		 * Method for node is capable of accepts new element
		 * @return if it is reached the capacity or not
		 */
		public boolean hasCapacity()
		{
			return ( used < CAPACITY );
		}
	} 
	
	/**
	 * 
	 * @author Türker Tercan
	 * My ListIterator class to keep track on nodes
	 */
	private class MyListIter implements ListIterator<E>
	{
		private Node<E> nextItem;
		private int index;
		private int arrayIndex;
		
		public MyListIter(int i)
		{
			if( i < 0 || i > size)
			{
				throw new IndexOutOfBoundsException("Invalid index " + i);
			}
			index = 0;
			arrayIndex = 0;
			
			nextItem = head;
			
			for( index = 0 ; index < i; index++ )
			{
				if( arrayIndex < nextItem.used && arrayIndex < Node.CAPACITY - 1 )
				{
					arrayIndex++;
				}
				else
				{
					arrayIndex = 0;
					nextItem = nextItem.next;
				}
			}
		}
		
		/**
		 * Checks if iterator is reached to end or not
		 */
		@Override
		public boolean hasNext()
		{
			return index < size;
		}
		
		/**
		 * It goes the next element and returns that element
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			index++;
			E temp = nextItem.data[arrayIndex++];
			if( arrayIndex >= nextItem.used && index != size)
			{
				arrayIndex = 0;
				nextItem = nextItem.next;
			}
			return temp;
		}
		
		/**
		 * Checks if iterator is beginning of the list or not
		 */
		@Override
		public boolean hasPrevious() {
			return (nextItem == null && size != 0) || nextItem.previous != null || arrayIndex != 0;
		}
		
		/**
		 * It goes to previous node and returns that element
		 */
		@Override
		public E previous() {
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			index--;
			E temp = nextItem.data[arrayIndex--];
			if( arrayIndex == 0 && hasPrevious())
			{
				nextItem = nextItem.previous;
				arrayIndex = nextItem.used - 1;
			}
			return temp;
		}
		
		/**
		 * The index after iterator
		 */
		@Override
		public int nextIndex() {
			return index + 1;
		}
		
		/**
		 * Returns previous index
		 */
		@Override
		public int previousIndex() {
			return index  - 1;
		}
		
		/**
		 * To see which element we are in the array
		 * @return arrayIndex
		 */
		public int getArrayIndex()
		{
			return arrayIndex;
		}
		
		/**
		 * It deletes the nextItem.data[arrayIndex] element and shifts the right elements to left 
		 * if that array is empty, deletes that node
		 */
		@Override
		public void remove()
		{
			if(nextItem == null)
				throw new IllegalStateException();
			if(index != 0 && arrayIndex == 0) {
				arrayIndex = nextItem.previous.used - 1;
				nextItem = nextItem.previous;
			}
			else if( index != 0 )
				arrayIndex--;
			
			for(int i = arrayIndex; i < nextItem.used - 1; i++)
				nextItem.data[i] = nextItem.data[i + 1];
		
			nextItem.used--;
			size--;
			
			if( nextItem.used <= 0 )
			{
				if(nextItem.previous == null)	//head
				{
					nextItem.next.previous = null;
					head = nextItem.next;
				}
				else if(nextItem.next == null)
				{
					nextItem.previous.next = null;
					tail = nextItem.previous;
				}
				else
				{
					nextItem.next.previous = nextItem.previous;
					nextItem.previous.next = nextItem.next;
				}
				nodeCount--;
			}
		}
		
		/**
		 * Sets the current elements data to newData
		 */
		@Override
		public void set(E newData) {
			if(nextItem == null || arrayIndex >= nextItem.used) {
				throw new IllegalStateException();
			}
			nextItem.data[arrayIndex] = newData;
		}
		
		/**
		 * Adds new element to list
		 * if the node is full creates another node
		 */
		@Override
		public void add(E obj)
		{
			if(head == null)
			{
				head = new Node<E>(obj);
				tail = head;
				size++;
				nodeCount++;
			}
			
			else if(nextItem == null)
			{
				Node<E> newNode = new Node<E>(obj);
				tail.next = newNode;
				newNode.previous = tail;
				tail = newNode;
				nodeCount++;
				size++;
				index++;
				arrayIndex = 0;
			}
			else
			{
				
				if(nextItem.hasCapacity())
				{
					nextItem.data[nextItem.used++] = obj;
					size++;
					arrayIndex++;
				}
				else
				{
					Node<E> newNode = new Node<E>(obj);
					newNode.previous = nextItem;
					newNode.next = nextItem.next;
					nextItem.next = newNode;
					nextItem = nextItem.next;
					arrayIndex = 0;
					nodeCount++;
					
					if(newNode.next == null)
					{
						tail = newNode;
					}
					
					index++;
					size++;
				}
			}
		}
	}

	/**
	 * Adds new element to LinkedArrayList
	 */
	public boolean add(E obj)
	{
		add(size, obj);
		return true;
	}
	
	/**
	 * It creates new ListIterator<E> and returns it
	 */
	public ListIterator<E> listIterator()
	{
		return new MyListIter(0);
	}
	
	/**
	 * It creates new ListIterator<E> and it goes where index points to 
	 */
	public ListIterator<E> listIterator(int index)
	{
		return new MyListIter(index);
	}
	
	/**
	 * Adds new element to specific index
	 */
	public void add(int index, E obj)
	{
		MyListIter iter = new MyListIter(index);
		iter.add(obj);
	}
	
	/**
	 * It adds new element to first space the list has
	 * @param obj new element
	 */
	public void addFirst(E obj)
	{
		add(0, obj);
	}
	
	/**
	 * It creates a new node to end of the list and adds new element to it
	 * @param obj new element
	 */
	public void addLast(E obj)
	{
		add(size, obj);
	}
	
	/**
	 * Gets first node's first element
	 * @return first data
	 */
	public E getFirst()
	{
		return head.data[0];
	}
	
	/**
	 * Gets last node's last element
	 * @return last data
	 */
	public E getLast()
	{
		return tail.data[tail.used];
	}
	
	/**
	 * It finds the given object in the list. If it finds, removes the element and shift the remaining elements
	 * If there are no elements in array, it also deletes the node
	 */
	public boolean remove(Object o)
	{
		MyListIter iter = new MyListIter(0);
		while( iter.hasNext() )
		{
			if( iter.next().equals(o) )
			{
				iter.remove();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * It deletes the data in that index
	 */
	public E remove(int index)
	{
		MyListIter iter = new MyListIter(index);
		E temp = iter.next();
		iter.remove();
		return temp;
	}
	
	/**
	 * Gets first element in the where index points to what node
	 */
	@Override
	public E get(int index) {
		ListIterator<E> iter = new MyListIter(index);
		return iter.next();
	}

	/**
	 * Returns node size
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * toString method to write all elements in this class
	 */
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		MyListIter iter = new MyListIter(0);
		
		while(iter.hasNext())
		{
			build.append(iter.next() + " ");
			if( iter.getArrayIndex() == 0)
				build.append("\n");
		}
	
		return build.toString();
	}
}
