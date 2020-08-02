package HW3;

import java.util.ListIterator;

public class testLinkedArrayList {

	public static void main(String[] args) {
		LinkedArrayList<String> mylist = new LinkedArrayList<String>();
		
		mylist.add(new String("Element1"));
		mylist.add(new String("Element2"));
		mylist.add(new String("Element3"));
		mylist.add(new String("Element4"));	
		
		mylist.add(new String("Element5"));
		mylist.add(new String("Element6"));
		mylist.add(new String("Element7"));
		mylist.add(new String("Element8"));
		
		mylist.add(new String("Element9"));
		mylist.add(new String("Element10"));
		mylist.add(new String("Element11"));
		mylist.add(new String("Element12"));
		
		mylist.add(new String("Element13"));
		mylist.add(new String("Element14"));
		
		System.out.println(mylist);
		System.out.println();
		
		mylist.remove("Element13");
		mylist.remove("Element6");
		mylist.remove("Element8");
		
		System.out.println(mylist);
		System.out.println();
		ListIterator<String> iter = mylist.listIterator(5);
		iter.remove();
		iter.add("newElement");
		iter.set("new");
		
		System.out.println(mylist);
		System.out.println();
		
		mylist.add(7, "hello");
		
		System.out.println(mylist);
		System.out.println();
		
		mylist.remove("Element7");
		mylist.remove("new");
		System.out.println(mylist);
	}

}
