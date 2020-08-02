/**
 * 
 */
package HW1_part2;

import java.util.ArrayList;

/**
 * Branch class that stores some arrays
 * @author Türker Tercan
 *
 */
public class Branch {

	/**
	 * Branch name string
	 */
	private String branchName;
	
	/**
	 * ArrayList that stores branchEmployees
	 */
	private ArrayList<BranchEmployee> employees = new ArrayList<BranchEmployee>();
	/**
	 * ArrayList that stores TransportPersonnels
	 */
	private ArrayList<TransportPersonnel> personnels = new ArrayList<TransportPersonnel>();
	/**
	 * ArrayList that stores Shipments
	 */
	private ArrayList<Shipment> shipments = new ArrayList<Shipment>();
	
	
	/**
	 * Basic constructor
	 * @param name String branchName
	 */
	public Branch( String name) {
		// TODO Auto-generated constructor stub
		branchName = name;
	}
	
	/**
	 * Getter for emplooyes
	 * @return employees
	 */
	public ArrayList<BranchEmployee> getEmployees()
	{
		return employees;
	}
	
	/**
	 * Getter for personnels
	 * @return personnels
	 */
	public ArrayList<TransportPersonnel> getPersonnels()
	{
		return personnels;
	}
	
	/**
	 * Getter for shipments
	 * @return shipments
	 */
	public ArrayList<Shipment> getShipments()
	{
		return shipments;
	}
	
	/**
	 * To print branchName
	 */
	@Override
	public String toString()
	{
		return String.format("%s", branchName);
	}
	
	/**
	 * Prints all BranchEmployees in object's branch
	 * @return employees size
	 */
	public int ShowBranchEmployees()
	{
		StringBuilder build = new StringBuilder();
		for( int i = 0; i < employees.size(); i++)
		{
			build.append( Integer.toString(i + 1) + " - " + employees.get(i) + "\n");
		}
		System.out.print(build.toString());
		return employees.size();
	}
	
	/**
	 * Prints all TransportPersonnels in object's branch
	 * @return personnels size
	 */
	public int ShowTransportPersonnels()
	{
		StringBuilder build = new StringBuilder();
		for( int i = 0; i < personnels.size(); i++)
		{
			build.append( Integer.toString(i + 1)  + " - " + personnels.get(i) + "\n");
		}
		System.out.print(build.toString());
		return personnels.size();
	}
	
	/**
	 * Prints all Shipments in object's branch
	 * @return shipments size
	 */
	public int ShowShipments()
	{
		StringBuilder build = new StringBuilder();
		for( int i = 0; i < shipments.size(); i++ )
		{
			build.append( Integer.toString(i + 1) + " - " + shipments.get(i) + "\n");
		}
		System.out.print(build.toString());
		return shipments.size();
	}
}
