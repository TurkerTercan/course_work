package HW1;

/**
 * A user who uses that company
 * @author bjktr
 *
 */
public class Customer {
	/**
	 * String to store name
	 */
	private String name;
	
	/**
	 * String to store surname
	 */
	private String surname;
	
	/**
	 * Reference to AutomationSystem
	 */
	private AutomationSystem auto;
	
	/**
	 * Basic constructor to 
	 * @param newName
	 * @param newSurname
	 * @param newAuto
	 */
	Customer(String newName, String newSurname, AutomationSystem newAuto )
	{
		name = newName;
		surname = newSurname;
		auto = newAuto;
	}
	
	/**
	 * Override toString method
	 */
	public String toString()
	{
		return String.format("%s %s", name, surname);
	}
	
	/**
	 * If the track number is valid show that shipment
	 * @param track is integer for trackingNumber
	 */
	public void currentStatus( int track )
	{
		for(int i = 0; i < auto.getBranchCount(); i++)
		{
			for(int j = 0 ; j < auto.getBranches()[i].getShipmentCount(); j++)
			{
				if( auto.getBranches()[i].getShipments()[j].getTrackingNumber() == track )
				{
					System.out.println("Shipment is found!\n" + auto.getBranches()[i].getShipments()[j]);
					return;
				}
			}
		}
		System.out.println("Shipment is not found. Try again!\n");
	}
}
