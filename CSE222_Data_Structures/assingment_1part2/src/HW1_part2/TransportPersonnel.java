/**
 * 
 */
package HW1_part2;

import java.util.Scanner;

/**
 * This class is a Transport Personnel who delivers the shipments
 * @author Türker Tercan
 *
 */
public class TransportPersonnel extends Personnel {
	
	/**
	 * Object's branch
	 */
	private Branch branch;
	
	/**
	 * To do some methods in activity i need to store selected shipment
	 */
	private Shipment selectedShipment;
	
	/**
	 * Basic constructor for Transport Personnel
	 * @param branch personnel's branch
	 * @param name personnel's name
	 * @param surname personnel's surname
	 * @param password personnel's password
	 */
	public TransportPersonnel(Branch branch, String name, String surname, String password) {
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
		
		build.append("~ ~ ~ TRANSPORT PERSONNEL MENU ~ ~ ~\n");
		build.append("1 - Select a shipment\n"
				+ "2 - Deliver selected shipment\n"
				+ "3 - Log Out\nChoice: ");
		
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
		if( choice < 1 || choice > 3)
			throw new Exception("Invalid Choice");
		
		Scanner scan = new Scanner(System.in);
		switch( choice )
		{
		case 1:
			int x = branch.ShowShipments();
			if( x == 0 )
				throw new Exception("There is no shipment to deliver");
			System.out.print("Select shipment: ");
			int y = scan.nextInt();
			if( y < 1 || y > x)
				throw new Exception("Invalid Choice");
			selectedShipment = branch.getShipments().get(y - 1);
			break;
		case 2:
			if( selectedShipment == null )
				throw new Exception("Selected shipment is null");
			this.DeliverShipment(selectedShipment);
			break;
		case 3: this.LogOut();
		}
		
		return this;
	}
	
	/**
	 * Delivers the given shipment
	 * @param shipment that will be delivered
	 */
	public void DeliverShipment( Shipment shipment )
	{
		shipment.changeStatus(Shipment.status.Delivered);
	}
	
	/**
	 * To print personnel
	 */
	@Override
	public String toString()
	{
		return String.format("%s %s", name, surname);
	}

	
}
