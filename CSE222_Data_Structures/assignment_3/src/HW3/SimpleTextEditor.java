/**
 * 
 */
package HW3;

import java.io.*;
import java.util.*;

/**
 * @author Türker Tercan
 * SimpleTextEditor class is implemented to read and edit a text file
 * in order to that, it stores text as characters in a list.
 * All methods implemented two times (one for ListIterator one for basic for loop)
 * because we want to measure which one is faster
 */
public class SimpleTextEditor {
	/**
	 * Text list
	 */
	private List<Character> text;
	
	/**
	 * Basic constructor
	 * @param choice which list do you want to use true-ArrayList / false-LinkedList
	 */
	public SimpleTextEditor(boolean choice)
	{
		if(choice == true)
			text = new ArrayList<Character>();
		else
			text = new LinkedList<Character>();
	}
	
	/**
	 * Reads file without iterator
	 * @param location where is text file stored
	 * @throws Exception FileIsNotFoundException
	 */
	public void Read(String location) throws Exception
	{
		text.clear();
		File file = new File(location);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int c = 0;
		while((c = br.read()) != -1)
		{
			char temp = (char) c;
			text.add(temp);
		}
		
		br.close();
	}
	
	/**
	 * Reads text file and stores all characters to text list with iterator
	 * @param location where the text file is stored
	 * @throws Exception FileNotFoundException
	 */
	public void read(String location) throws Exception
	{
		text.clear();
		File file = new File(location);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		ListIterator<Character> iter = text.listIterator();
		int c = 0;
		while((c = br.read()) != -1)
		{
			char temp = (char) c;
			iter.add(temp);
		}
		
		br.close();
	}
	
	
	/**
	 * Adds given word to given index without iterator
	 * @param word given word
	 * @param index where it will be added
	 */
	public void Add(String word, int index)
	{
		if( index < 0 || index > text.size())
			throw new IndexOutOfBoundsException("Invalid index " + index);
		
		for(int i = 0; i < word.length(); i++)
		{
			int temp = index + i;
			text.add(temp, word.charAt(i));
		}
	}
	
	/**
	 * Adds given word to given index with iterator
	 * @param word given word
	 * @param index where it will be added
	 */
	public void add(String word, int index)
	{
		if( index < 0 || index > text.size())
			throw new IndexOutOfBoundsException("Invalid index " + index);
		
		ListIterator<Character> iter = text.listIterator(index);
		for(int i = 0; i < word.length(); i++)
			iter.add(word.charAt(i));
	}
	
	/**
	 * Searches the given group of characters in the list and returns first occurrence without iterator
	 * @param word the given characters
	 * @return index of first occurrence
	 */
	public int Find(String word)
	{
		if(text.isEmpty())
			throw new IllegalStateException();
		
		for(int i = 0; i < text.size(); i++)
		{
			int j;
			for( j = 0; j < word.length(); j++)
			{
				if( !text.get(i + j).equals(word.charAt(j)))
					break;
			}
			if( j == word.length())
			{
				return i;
			}
				
		}
		return -1;
	}
	
	/**
	 * Searches the given group of characters in the list and returns first occurrence with iterator
	 * @param word the given characters
	 * @return index of first occurrence
	 */
	public int find(String word)
	{
		if(text.isEmpty())
			throw new IllegalStateException();
		
		ListIterator<Character> iter = text.listIterator();
		while(iter.hasNext())
		{
			int i = iter.nextIndex();
			int j;
			for( j = 0; j < word.length(); j++)
			{
				if( !iter.next().equals(word.charAt(j)))
					break;
			}
			if( j == word.length())
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Replaces all occurrences of a character with another character without iterator
	 * @param occurs character that'll be searched
	 * @param replace character that'll be changed to
	 */
	public void Replace(char occurs, char replace)
	{
		if(text.isEmpty())
			throw new IllegalStateException();
		
		for(int i = 0; i < text.size(); i++)
		{
			if(text.get(i).equals(occurs))
				text.set(i, replace);
		}
	}
	
	/**
	 * Replaces all occurrences of a character with another character with iterator
	 * @param occurs character that'll be searched
	 * @param replace character that'll be changed to
	 */
	public void replace(char occurs, char replace)
	{
		if(text.isEmpty())
			throw new IllegalStateException();
		
		ListIterator<Character> iter = text.listIterator();
		while(iter.hasNext())
		{
			if(iter.next().equals(occurs))
				iter.set(replace);
		}
	}
	
	/**
	 * To see every element in the list 
	 */
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		for(int i = 0 ; i < text.size(); i++)
		{
			build.append(text.get(i));
		}
		return build.toString();
	}
}
