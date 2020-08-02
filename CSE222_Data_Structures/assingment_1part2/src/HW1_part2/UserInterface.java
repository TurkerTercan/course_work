package HW1_part2;

import java.util.Scanner;

/**
 * Main class with user interface
 * @author Türker Tercan
 *
 */
public class UserInterface {

	/**
	 * Test main class
	 * @param args none
	 */
	public static void main(String[] args) {
		Company company = LoginInterface.company;
		company.CreateAdmin(new Administrator("turker","tercan","asd"));

		Scanner scan = new Scanner(System.in);
		boolean exit = false;
		LoginInterface login = null;
		
		while( !exit )
		{
			while( login == null )
			{
				String name, surname, password;
				System.out.print("~ Welcome to "
						+ company
						+ " ~\n"
						+ "~ ~ ~ Login System ~ ~ ~\n"
						+ "1 - Personnel\t2 - Customer\t 3- Exit\n"
						+ "Choice: ");
				int choice = scan.nextInt();
				scan.nextLine();
				if( choice == 1 )
				{
					System.out.print("Name: ");
					name = scan.nextLine();
					System.out.print("Surname: ");
					surname = scan.nextLine();
					System.out.print("Password: ");
					password = scan.nextLine();

					login = company.Find(name, surname, password);
				}
				else if( choice == 2 )
				{
					System.out.print("Name: ");
					name = scan.nextLine();
					System.out.print("Surname: ");
					surname = scan.nextLine();
					
					login = new Customer(name, surname);
				}
				else if( choice == 3)
				{
					exit = true;
					break;
				}
			}
			while( login != null )
			{
				login.ShowMenu();
				int x = scan.nextInt();
				try {
					login = login.Activity(x);
				}
				catch(Exception e)
				{
					System.err.print(e.getMessage() + "\n");
				}
			}
		}
		
		scan.close();
	}

}
