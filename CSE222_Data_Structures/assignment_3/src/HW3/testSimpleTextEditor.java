package HW3;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class testSimpleTextEditor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleTextEditor arrText = new SimpleTextEditor(true);
		SimpleTextEditor arrText2 = new SimpleTextEditor(true);
		SimpleTextEditor linkedText = new SimpleTextEditor(false);
		SimpleTextEditor linkedText2 = new SimpleTextEditor(false);
		
		Logger logger = Logger.getLogger("HW3Log");
		FileHandler fh;
		try {
			fh = new FileHandler("HW3LogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			logger.info("HW3 LOG");
		}
		catch(SecurityException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		try
		{
			System.out.print("Enter your smaller text file location: ");
			String st = scan.next();
			logger.info("Smaller Text File");
			long startTime = System.nanoTime();
			arrText.read(st);
			long endTime = System.nanoTime();
			logger.info("List is an ArrayList and iterator is used:");
			long arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			arrText.add(" HELLO ", 10);
			endTime = System.nanoTime();
			long arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			arrText.find("HELLO");
			endTime = System.nanoTime();
			long arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			arrText.replace('L','r');
			endTime = System.nanoTime();
			long arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			System.out.println(arrText);
			
			logger.info("List is an ArrayList and iterator is not used: ");
			startTime = System.nanoTime();
			arrText2.Read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			arrText2.Add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			arrText2.Find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			arrText2.Replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
			
			logger.info("List is a LinkedList and iterator is used");
			startTime = System.nanoTime();
			linkedText.read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			linkedText.add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			linkedText.find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			linkedText.replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
			
			logger.info("List is a LinkedList and iterator is not used");
			startTime = System.nanoTime();
			linkedText2.Read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			
			startTime = System.nanoTime();
			linkedText2.Add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			linkedText2.Find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			linkedText2.Replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try {
			System.out.print("Enter your larger text file location: ");
			String st = scan.next();
			logger.info("Larger Text File");
			
			long startTime = System.nanoTime();
			arrText.read(st);
			long endTime = System.nanoTime();
			logger.info("List is an ArrayList and iterator is used:");
			long arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			arrText.add(" HELLO ", 10);
			endTime = System.nanoTime();
			long arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			arrText.find("HELLO");
			endTime = System.nanoTime();
			long arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			arrText.replace('L','r');
			endTime = System.nanoTime();
			long arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
			logger.info("List is an ArrayList and iterator is not used: ");
			startTime = System.nanoTime();
			arrText2.Read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			arrText2.Add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			arrText2.Find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			arrText2.Replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
			
			logger.info("List is a LinkedList and iterator is used");
			startTime = System.nanoTime();
			linkedText.read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			startTime = System.nanoTime();
			linkedText.add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			linkedText.find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			linkedText.replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
			
			
			logger.info("List is a LinkedList and iterator is not used");
			startTime = System.nanoTime();
			linkedText2.Read(st);
			endTime = System.nanoTime();
			arrTime = endTime - startTime;
			logger.info("Read Time: " + arrTime);
			
			
			startTime = System.nanoTime();
			linkedText2.Add(" HELLO ", 10);
			endTime = System.nanoTime();
			arrTime1 = endTime - startTime;
			logger.info("Add Time: " + arrTime1);
			
			startTime = System.nanoTime();
			linkedText2.Find("HELLO");
			endTime = System.nanoTime();
			arrTime2 = endTime - startTime;
			logger.info("Find Time: " + arrTime2);
			
			startTime = System.nanoTime();
			linkedText2.Replace('L','r');
			endTime = System.nanoTime();
			arrTime3 = endTime - startTime;
			logger.info("Replace Time: " + arrTime3);
			arrTime = arrTime + arrTime1 + arrTime2 + arrTime3;
			logger.info("Total Time:" + arrTime);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		scan.close();
	}

}
