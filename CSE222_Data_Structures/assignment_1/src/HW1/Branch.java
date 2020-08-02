package HW1;

/**
 * This Branch class is implemented to hold the branches of a cargo company
 * and it contains all employees and shipments inside
 * @author Türker Tercan
 *
 */
public class Branch {
	
	/**
	 * Company name String 
	 */
	private String companyName;
	
	/**
	 * Reference to AutomationSystem
	 */
	private AutomationSystem auto;
	
	/**
	 * Array used to store BranchEmployees in branch
	 */
	private BranchEmployee[] workers = new BranchEmployee[100];
	
	/**
	 * Array used to store TransportPersonnels in branch
	 */
	private TransportPersonnel[] transport = new TransportPersonnel[100];
	
	/**
	 * Array used to store Shipments in branch
	 */
	private Shipment[] shipments = new Shipment[100];
	
	/**
	 * Number of Employees in the array
	 */
	private int employeeCount = 0;
	
	/**
	 * Number of Personnels in the array
	 */
	private int transportCount = 0;
	
	/**
	 * Number of Shipments in the array
	 */
	private int shipmentCount = 0;
	
	/**
	 * Basic constructor for Branch
	 * @param name is String to store company name
	 * @param newAuto is reference to get AutomationSystem
	 */
	Branch( String name, AutomationSystem newAuto )
	{
		companyName = name;
		auto = newAuto;
	}
	
	/**
	 * To get AutomationSystem
	 * @return AutomationSystem reference 
	 */
	public AutomationSystem getAuto()
	{
		return auto;
	}
	
	/**
	 * To get Shipments 
	 * @return Shipment[] array
	 */
	public Shipment[] getShipments()
	{
		return shipments;
	}
	
	/**
	 * To get shipmentCount
	 * @return integer for how many Shipments in the array
	 */
	public int getShipmentCount()
	{
		return shipmentCount;
	}
	
	/**
	 * To change shipmentCount
	 * @param count is the new integer for shipmentCount
	 */
	public void setShipmentCount( int count )
	{
		shipmentCount = count;
	}
	
	/**
	 * To get BranchEmployees
	 * @return BranchEmployee[] array
	 */
	public BranchEmployee[] getWorkers()
	{
		return workers;
	}
	
	/**
	 * To get employeeCount
	 * @return integer for how many BranchEmployee in the array
	 */
	public int getEmloyeeCount()
	{
		return employeeCount;
	}
	
	/**
	 * To change employeeCount
	 * @param count is the new integer for employeeCount
	 */
	public void setEmployeeCount( int count )
	{
		employeeCount = count;
	}
	
	/**
	 * To get TransportPersonnels
	 * @return TransportPersonnel[] array
	 */
	public TransportPersonnel[] getTransport()
	{
		return transport;
	}
	
	/**
	 * To get transportCount
	 * @return integer for how many TransportPersonnel in the array
	 */
	public int getTransportCount()
	{
		return transportCount;
	}

	/**
	 * To change transportCount
	 * @param count is the new integer for transportCount
	 */
	public void setTransportCount( int count )
	{
		transportCount = count;
	}
	
	/**
	 * toString method 
	 */
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		build.append("Branch Name: " + companyName + "\n");
		if( employeeCount != 0 ) {
			build.append("Branch Employees:\n");
		}
		for(int i = 0; i < employeeCount; i++)
		{
			build.append(Integer.toString(i + 1) +". Employee: " + workers[i].toString() + "\n");
		}
		if( transportCount != 0 ) {
			build.append("Transport Personnel:\n");
		}
		for(int i = 0; i < employeeCount; i++)
		{
			build.append(Integer.toString(i + 1) +". Personnel: " + transport[i].toString() + "\n");
		}
		
		return build.toString();
	}
	
	/**
	 * Method to reset Branch
	 */
	public void makeNull()
	{
		companyName = "null";
		employeeCount = 0;
		transportCount = 0;
		shipmentCount = 0;
	}
}
