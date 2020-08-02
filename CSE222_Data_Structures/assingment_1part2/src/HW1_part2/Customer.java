/**
 * 
 */
package HW1_part2;

import java.util.Scanner;

/**
 * Company's customer
 * @author Türker Tercan
 *
 */
public class Customer implements LoginInterface {
	
	/**
	 * Customer's name
	 */
	private String name;
	
	/**
	 * Customer's surname
	 */
	private String surname;
	
	/**
	 * Basic constructor for Customer
	 * @param name is customer's name
	 * @param surname is customer's surname
	 */
	public Customer(String name, String surname) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Log out for all actor objects
	 * @return null to exit
	 */
	@Override
	public LoginInterface LogOut() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Shows a menu according to that object
	 */
	@Override
	public void ShowMenu() {
		StringBuilder build = new StringBuilder();
		
		build.append("~ ~ ~ CUSTOMER MENU ~ ~ ~\n");
		build.append("1 - Track a shipment\n"
				+ "2 - Log Out\nChoice: ");
		
		System.out.print(build.toString());	
	}
	
	/**
	 * This method helps user interface. It provides access to necessary methods 
	 * @param choice is an integer to which method will progress
	 * @return this or calls LogOut()
	 * @throws Exception throws our exception if something bad is happened
	 */
	@SuppressWarnings("resource")
	@Override
	public LoginInterface Activity(int choice) throws Exception {
		// TODO Auto-generated method stub
		if( choice > 2 || choice < 1)
			throw new Exception("Invalid Choice");
		Scanner scan = new Scanner(System.in);
		switch( choice )
		{
		case 1:
			System.out.print("Enter your tracking number: ");
			int n = scan.nextInt();
			this.trackShipment(n);
			break;
		case 2: return null;
		}
		return this;
	}
	
	/**
	 * Checks all shipments if there is any match with trackingNumber, print it
	 * @param trackingNumber integer to track shipment
	 */
	public void trackShipment( int trackingNumber )
	{
		boolean isFound = false;
		for( int i = 0; i < LoginInterface.company.getBranches().size(); i++)
		{
			Branch b = LoginInterface.company.getBranches().get(i);
			for( int j = 0; j < b.getShipments().size(); j++)
			{
				if( b.getShipments().get(j).getTrackingNumber() == trackingNumber)
				{
					System.out.println(b.getShipments().get(j));
					isFound = true;
				}
			}
		}
		if( !isFound )
		{
			System.out.println("There is no shipment with that tracking number\n");
		}
	}
	
	/**
	 * Getter name
	 * @return name
	 */
	public String getName() { return name; }
	
	/**
	 * Getter surname
	 * @return surname
	 */
	public String getSurname() { return surname; }
	
	/**
	 * Prints customer
	 */
	@Override
	public String toString()
	{
		return String.format("%s %s", name, surname);
	}

	
}
