/**
 * 
 */
package HW1_part2;

/**
 * Designed for a shipment
 * @author Türker Tercan
 *
 */
public class Shipment {
	
	/**
	 * Customer that sends the cargo
	 */
	private Customer sender;
	
	/**
	 * Customer that receives the cargo
	 */
	private Customer receiver;
	
	/**
	 * Shipments track number
	 */
	private int trackingNumber;
	
	/**
	 * Shipment's status
	 */
	private status current;
	
	/**
	 * Enum for status
	 * @author Türker Tercan
	 *
	 */
	enum status{
		NOTShipped,
		Shipped,
		Delivered
	}
	
	/**
	 * Basic constructor for Shipment
	 * @param sender that sends cargo
	 * @param receiver that receives cargo
	 * @param trackingNumber integer to keep track
	 */
	public Shipment( Customer sender, Customer receiver, int trackingNumber) {
		// TODO Auto-generated constructor stub
		this.sender = sender;
		this.receiver = receiver;
		this.trackingNumber = trackingNumber;
		current = status.NOTShipped;
	}
	
	/**
	 * Change status
	 * @param change new status
	 */
	public void changeStatus( status change )
	{
		current = change;
	}
	
	/**
	 * Prints shipment
	 */
	public String toString()
	{
		return String.format("Shipment Information:\n"
				+ "Sender: %s\t Receiver: %s\n"
				+ "Tracking Number: %d\t"
				+ "Status: %s\n", sender, receiver, trackingNumber, current);
	}
	
	public int getTrackingNumber()
	{
		return this.trackingNumber;
	}
}
