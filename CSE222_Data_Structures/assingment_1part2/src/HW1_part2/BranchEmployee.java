/**
 * 
 */
package HW1_part2;

import java.util.Scanner;

/**
 * BranchEmployee class that provides all necessary methods
 * @author Türker Tercan
 *
 */
public class BranchEmployee extends Personnel {
	
	/**
	 * Object's branch
	 */
	private Branch branch;
	
	/**
	 * To do some methods in Activity() i need to store selected customer
	 */
	private Customer selectedCustomer;
	
	/**
	 * To do some methods in Activity() i need to store selected shipment
	 */
	private Shipment selectedShipment;
	
	/**
	 * Basic constructor
	 * @param branch is employee's branch
	 * @param name is employee's name
	 * @param surname is employee's surname
	 * @param password is employee's password
	 */
	public BranchEmployee(Branch branch ,String name, String surname, String password) {
		super(name, surname, password);
		// TODO Auto-generated constructor stub
		this.branch = branch;
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
		// TODO Auto-generated method stub
		StringBuilder build = new StringBuilder();
		build.append("~ ~ ~ BRANCH EMPLOYEE MENU ~ ~ ~\n"
				+ "1 - Add customer\n"
				+ "2 - Select a customer\n"
				+ "3 - Remove selected customer\n"
				+ "4 - Create shipment\n"
				+ "5 - Select a shipment\n"
				+ "6 - Remove selected shipment\n"
				+ "7 - Change selected shipment's current status\n"
				+ "8 - Log Out\nChoice: ");
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
		if( choice < 1 || choice > 8 )
			throw new Exception("Invalid Choice");
		
		Scanner scan = new Scanner(System.in);
		switch( choice )
		{
		case 1:
			System.out.print("Name: ");
			String customerName = scan.next();
			System.out.print("Surname: ");
			String customerSurname = scan.next();
			this.AddCustomer(customerName, customerSurname);
			break;
		case 2:
			int x = this.ShowCustomers();
			if( x == 0 )
			{
				throw new Exception("There are no customers to select");
			}
			System.out.print("Select a customer: ");
			int indexCustomer = scan.nextInt();
			if( indexCustomer < 0 || indexCustomer > x)
				throw new Exception("Invalid Choice");
			selectedCustomer = LoginInterface.company.getCustomers().get(indexCustomer - 1);
			break;
		case 3:
			if( selectedCustomer == null)
			{
				throw new Exception("Selected customer is null");
			}
			this.RemoveCustomer(selectedCustomer);
			break;
		case 4:
			int y = this.ShowCustomers();
			if( y == 0 )
			{
				throw new Exception("There are no customers to select");
			}
			
			System.out.print("Select a sender: ");
			int indexSender = scan.nextInt();
			if( indexSender < 1 || indexSender > y)
				throw new Exception("Invalid Choice");
			Customer sender = LoginInterface.company.getCustomers().get(indexSender - 1);
			y = ShowCustomers();
			System.out.print("Select a receiver: ");
			int indexReceiver = scan.nextInt();
			if( indexReceiver < 1 || indexReceiver > y)
				throw new Exception("Invalid Choice");
			Customer receiver = LoginInterface.company.getCustomers().get(indexReceiver - 1);
			if( sender == receiver )
			{
				throw new Exception("Sender and Receiver must be different customers");
			}
			System.out.print("Enter a tracking number: ");
			int trackingNumber = scan.nextInt();
			branch.getShipments().add(new Shipment( sender, receiver, trackingNumber));
			break;
		case 5:
			int z = branch.ShowShipments();
			if( z == 0 )
			{
				throw new Exception("There are no shipments to select");
			}
			System.out.print("Select: ");
			int indexShipment = scan.nextInt();
			if( indexShipment < 1 || indexShipment > z)
			{
				throw new Exception("Invalid Choice");
			}
			selectedShipment = branch.getShipments().get(indexShipment - 1);
			break;
		case 6:
			if( selectedShipment == null)
				throw new Exception("Selected Shipment is null");
			this.RemoveShipment(selectedShipment);
			break;
		case 7:
			if( selectedShipment == null)
				throw new Exception("Selected Shipment is null");
			System.out.print("1 - Not Shipped\n"
					+ "2 - Shipped\n"
					+ "Choice: ");
			int status = scan.nextInt();
			if( status == 1 )
				selectedShipment.changeStatus(Shipment.status.NOTShipped);
			else if( status == 2 )
				selectedShipment.changeStatus(Shipment.status.Shipped);
			else
				throw new Exception("Invalid Choice");
			break;
		case 8: return this.LogOut();
		}
		return this;
	}
	
	/**
	 * Prints all Customers in company
	 * @return customers size
	 */
	public int ShowCustomers()
	{
		StringBuilder build = new StringBuilder();
		for( int i = 0; i < LoginInterface.company.getCustomers().size(); i++)
		{
			build.append( Integer.toString(i + 1) + " - " + LoginInterface.company.getCustomers().get(i) + "\n");
		}
		System.out.print(build.toString());
		return LoginInterface.company.getCustomers().size();
	}
	
	/**
	 * Add customer to the company
	 * @param name is customer's name
	 * @param surname is customer's surname
	 */
	public void AddCustomer( String name, String surname )
	{
		LoginInterface.company.getCustomers().add(new Customer(name, surname));
		System.out.print("Customer succesfully added\n");
	}
	
	/**
	 * Removes given customer from ArrayList
	 * @param customer is object that will be removed.
	 * @throws Exception no such customer was found
	 */
	public void RemoveCustomer( Customer customer ) throws Exception
	{
		if( !LoginInterface.company.getCustomers().remove(customer) )
		{
			throw new Exception("No such customer was found!");
		}
		System.out.print("Customer succesfully removed\n");
	}
	
	/**
	 * Add new shipment to ArrayList
	 * @param sender customer that sends the cargo
	 * @param receiver customer that receives the cargo
	 * @param trackingNumber is integer that helps customer to track his/her shipment
	 */
	public void CreateShipment( Customer sender, Customer receiver, int trackingNumber)
	{
		branch.getShipments().add(new Shipment(sender, receiver, trackingNumber));
		System.out.print("Shipment successfully created\n");
	}
	
	/**
	 * Removes shipment from ArrayList
	 * @param shipment is the object that will be removed
	 * @throws Exception if the given object is not in ArrayList
	 */
	public void RemoveShipment( Shipment shipment ) throws Exception
	{
		if( !branch.getShipments().remove(shipment) )
		{
			throw new Exception("No such shipment was found!");
		}
		branch.getShipments().remove(shipment);
		System.out.print("Shipment succesfully removed\n");
	}
	
	/**
	 * Change shipment's status
	 * @param current status that will change
	 * @param shipment shipment that will change
	 * @throws Exception Branch Employee can not deliver a shipment
	 */
	public void ChangeShipmentStatus( Shipment.status current, Shipment shipment ) throws Exception
	{
		if( current == Shipment.status.Delivered )
		{
			throw new Exception("Branch Employee's not authorized to change a shipment status to Delivered!");
		}
		shipment.changeStatus(current);
		System.out.print("Shipment status succesfully changed\n");
	}

	
	
}
