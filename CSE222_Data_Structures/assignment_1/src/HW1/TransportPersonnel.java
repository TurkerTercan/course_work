package HW1;

/**
 * This class is a Transport Personnel who delivers the shipments
 * @author Türker Tercan
 *
 */
public class TransportPersonnel extends Personnel {
	
	/**
	 * Reference to branch
	 */
	private Branch branch;
	
	/**
	 * Basic constructor for TransportPersonnel
	 * @param name is String to save first name
	 * @param surname is String to save last name
	 * @param newBranch is Branch to save reference to its branch
	 */
	TransportPersonnel( String name, String surname, Branch newBranch )
	{
		super(name, surname);
		branch = newBranch;
	}
	
	/**
	 * To change a shipment status to delivered
	 * @param index is the integer to point which shipment is delivered in the array
	 */
	public void shipmentDelivered( int index )
	{
		if( index > branch.getShipmentCount())
		{
			System.err.println("No shipment in that index!");
			return;
		}
		branch.getShipments()[index].changeStatus(Shipment.status.Delivered);
	}
	
	/**
	 * Override toString method
	 */
	public String toString()
	{
		return String.format("%s", super.toString());
	}
}
