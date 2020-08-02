package HW1;

/**
 * This class is for whole system and it contains all necessary arrays
 * @author Türker Tercan
 *
 */
public class AutomationSystem {
	
	/**
	 * Array used to store Administrators
	 */
	private Administrator[] admins = new Administrator[100];
	
	/**
	 * Array used to store Customers
	 */
	private Customer[] customers = new Customer[100];
	
	/**
	 * Array used to store Branches
	 */
	private Branch[] branches = new Branch[100];
	
	/**
	 * Number of Administrators stored in the array
	 */
	private int adminCount = 0;
	
	/**
	 * Number of Customers stored in the array
	 */
	private int customerCount = 0;
	
	/**
	 * Number of Braches stored in the array
	 */
	private int branchCount = 0;
	
	/**
	 * Default constructor
	 */
	AutomationSystem()
	{
		//Empty
	}
	
	/**
	 * Method to create Administrators
	 */
	public void createAdmin()
	{
		if(adminCount > 100)
		{
			System.err.println("Capacity is reached!");
			return;
		}
		
		admins[adminCount++] = new Administrator( Integer.toString(adminCount + 1) + ".", "Admin", this);
	}
	
	/**
	 * Method to delete Administrators
	 * @param index is which Administrator will be deleted 
	 */
	public void deleteAdmin( int index )
	{
		if( index > adminCount )
		{
			System.err.println("No admin in that index!");
			return;
		}
		
		for( int i = index; i < adminCount - 1; i++)
		{
			admins[i] = admins[i + 1];
		}
		adminCount--;
	}
	
	/**
	 * To get Administrators
	 * @return Administrators in array
	 */
	public Administrator[] getAdmins()
	{
		return admins;
	}
	
	/**
	 * To get adminCount
	 * @return integer for how many Administrators in the array
	 */
	public int getAdminCount()
	{
		return adminCount;
	}
	
	/**
	 * To change to adminCount
	 * @param count is the new integer for adminCount
	 */
	public void setAdminCount( int count )
	{
		adminCount = count;
	}
	
	/**
	 * To get Branches in the array
	 * @return Branch[] array
	 */
	public Branch[] getBranches()
	{
		return branches;
	}
	
	/**
	 * To get branchCount
	 * @return integer for how many Branches in the array
	 */
	public int getBranchCount() {
		return branchCount;
	}
	
	/**
	 * To change to branchCount
	 * @param count is the new integer for branchCount
	 */
	public void setBranchCount(int count)
	{
		branchCount = count;
	}
	
	/**
	 * To get Customers in the array
	 * @return Customer[] array
	 */
	public Customer[] getCustomers()
	{
		return customers;
	}
	
	/**
	 * To get customerCount
	 * @return integer for how many Customers in the array
	 */
	public int getCustomerCount()
	{
		return customerCount;
	}
	
	/**
	 * To change to customerCount
	 * @param count is the new integer for customerCount
	 */
	public void setCustomerCount(int count)
	{
		customerCount = count;
	}
}
