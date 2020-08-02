package HW1;

/**
 * This is an employee class who does basic changes to shipments
 * @author Türker Tercan
 *
 */
public class BranchEmployee extends Personnel{
	
	/**
	 * Reference to branch
	 */
	private Branch branch;
	
	/**
	 * Basic constructor for BranchEmployee
	 * @param name is String for first name 
	 * @param surname is String for surname
	 * @param newBranch reference to branch
	 */
	BranchEmployee( String name, String surname, Branch newBranch )
	{
		super( name, surname);
		branch = newBranch;
	}
	
	/**
	 * Method to create Shipments
	 * @param sender Customer information for sender
	 * @param receiver Customer information for receiver
	 * @param track is integer to store tracking number
	 */
	public void createShipment( Customer sender, Customer receiver, int track)
	{
		if( branch.getShipmentCount() > 100 )
		{
			System.err.println("Capacity is reached!");
			return;
		}
		branch.getShipments()[branch.getShipmentCount()] =
				new Shipment(sender, receiver, track, Shipment.status.NOTShipped);
		branch.setShipmentCount( branch.getShipmentCount() + 1);
	}
	
	/**
	 * Method to delete a shipment in branch
	 * @param index is integer to which element in the array will be deleted
	 */
	public void removeShipment(int index)
	{
		if( index > branch.getShipmentCount())
		{
			System.err.println("No shipment in that index!");
			return;
		}
		for( int i = index; i < branch.getShipmentCount() - 1; i++)
		{
			branch.getShipments()[i] = branch.getShipments()[i + 1];
		}
		branch.setShipmentCount( branch.getShipmentCount() - 1);
	}
	
	/**
	 * Create a customer 
	 * @param name String to customer's name
	 * @param surname String to customer's surname
	 */
	public void addCustomer( String name, String surname)
	{
		if( branch.getAuto().getCustomerCount() > 100)
		{
			System.err.println("Capacity is reached!");
			return;
		}
		branch.getAuto().getCustomers()[branch.getAuto().getCustomerCount()] = new Customer(name, surname, branch.getAuto());
		branch.getAuto().setCustomerCount(branch.getAuto().getCustomerCount() + 1);
	}
	
	/**
	 * Delete the customer in that index
	 * @param index is specifies the integer to be deleted from the array
	 */
	public void removeCustomer( int index )
	{
		if( index > branch.getAuto().getCustomerCount())
		{
			System.err.println("No customer in that index!");
			return;
		}
		
		for( int i = index; i < branch.getAuto().getCustomerCount() - 1; i++)
		{
			branch.getAuto().getCustomers()[i] = branch.getAuto().getCustomers()[i + 1];
		}
		branch.getAuto().setCustomerCount( branch.getAuto().getCustomerCount() - 1);
	}
	
	/**
	 * Change shipment status
	 * @param index is the integer to which shipment will be change
	 * @param current is the status that will be changed
	 */
	public void changeShipmentStatus( int index, Shipment.status current)
	{
		if( index > branch.getShipmentCount() )
		{
			System.err.println("No shipment in that index!");
			return;
		}
		if( current == Shipment.status.Delivered )
		{
			System.err.println("Branch Employee can't change status to delivered!");
			return;
		}
		else
			branch.getShipments()[index].changeStatus(current);
		
	}
	/**
	 * Override toString method
	 */
	public String toString()
	{
		return String.format("%s", super.toString());
	}
}
