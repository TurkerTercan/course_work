package HW1;

/**
 * Designed for a shipment
 * @author Türker Tercan
 *
 */
public class Shipment {
	
	/**
	 * Sender customer information
	 */
	private Customer sender;
	
	/**
	 * Receiver customer information
	 */
	private Customer receiver;
	
	/**
	 * Integer to track Shipment
	 */
	private int trackingNumber;

	/**
	 * Shipment statuses
	 * @author Türker Tercan
	 *
	 */
	enum status{
		NOTShipped,
		Shipped,
		Delivered
	}
	
	/**
	 * To store status
	 */
	private status current;
	
	/**
	 * Basic constructor for Shipment
	 * @param newSender Customer sender information
	 * @param newReceiver Customer receiver information
	 * @param track	integer to track the shipment
	 * @param newCurrent shipment status
	 */
	Shipment( Customer newSender, Customer newReceiver, int track, status newCurrent)
	{
		sender = newSender;
		receiver = newReceiver;
		trackingNumber = track;
		current = newCurrent;
	}
	
	/**
	 * Override toString method
	 */
	public String toString()
	{
		return String.format("Shipment Information:\n"
				+ "Sender: %s\t Receiver: %s\n"
				+ "Tracking Number: %d\t"
				+ "Status: %s\n", sender, receiver, trackingNumber, current);
	}
	
	/**
	 * To change status of that shipment
	 * @param newCurrent is status 
	 */
	public void changeStatus(status newCurrent)
	{
		current = newCurrent;
	}
	
	/**
	 * To get trackingNumber
	 * @return integer to tracking number of that shipment
	 */
	public int getTrackingNumber()
	{
		return trackingNumber;
	}
}
