package HW1;

/**
 * This class implements all needed methods for admininistrator
 * @author Türker Tercan	
 *
 */
public class Administrator extends Personnel{
	/**
	 * Reference to AutomationSystem 
	 */
	private AutomationSystem auto;
	
	/**
	 * Basic Constructor for admins
	 * @param name is a String for first name
	 * @param surname is a String for second name
	 * @param newSystem reference to AutomationSystem
	 */
	Administrator( String name, String surname, AutomationSystem newSystem)
	{
		super(name, surname);
		auto = newSystem;
	}
	
	/**
	 * Method is to create new branch
	 * @param companyName is a String for branch name
	 */
	public void createBranch( String companyName )
	{
		Branch[] branches = auto.getBranches();
		int branchCount = auto.getBranchCount();
		
		if(branchCount >= 100)
		{
			System.err.println("Capacity is reached!");
			return;
		}
		
		branches[branchCount] = new Branch(companyName, auto);
		auto.setBranchCount( auto.getBranchCount() + 1);
	}
	/**
	 * Delete that branch
	 * @param i is index to erase
	 */
	public void eraseBranch( int i )
	{
		if( i > auto.getBranchCount())
		{
			System.err.println("No branch in that index!");
			return;
		}
		
		Branch[] branches = auto.getBranches();
		for(int a = i; a < auto.getBranchCount() - 1; a++)
		{
			branches[a] = branches[a + 1];
		}
		branches[auto.getBranchCount() - 1].makeNull();
		
		auto.setBranchCount( auto.getBranchCount() - 1);
		
	}
	
	/**
	 * Create emplooye in a branch
	 * @param whichBranch is branch's index
	 * @param name is String for emplooye's first name
	 * @param surname is String for emplooye's second name
	 */
	public void createBranchEmployee( int whichBranch, String name, String surname )
	{
		if( whichBranch > auto.getBranchCount())
		{
			System.err.println("No branch in that index!");
			return;
		}
		BranchEmployee[] employees = auto.getBranches()[whichBranch].getWorkers();
		int employeeCount = auto.getBranches()[whichBranch].getEmloyeeCount();
		
		employees[employeeCount] = new BranchEmployee(name , surname, auto.getBranches()[whichBranch]);
		auto.getBranches()[whichBranch].setEmployeeCount( auto.getBranches()[whichBranch].getEmloyeeCount() + 1);
	}
	
	/**
	 * Erase an employee in a branch
	 * @param whichBranch is a integer for branch index
	 * @param index is the position of the emplooye's that will be deleted
	 */
	public void eraseBranchEmployee( int whichBranch, int index )
	{
		if( whichBranch > auto.getBranchCount())
		{
			System.err.println("No branch in that index!");
			return;
		}
		if( index > auto.getBranches()[whichBranch].getEmloyeeCount())
		{
			System.err.println("No employee in that index!");
			return;
		}
		BranchEmployee[] employees = auto.getBranches()[whichBranch].getWorkers();
		for(int i = index; i < auto.getBranches()[whichBranch].getEmloyeeCount() -1; i++)
		{
			employees[i] = employees[i + 1];
		}
		auto.getBranches()[whichBranch].setEmployeeCount( auto.getBranches()[whichBranch].getEmloyeeCount() - 1);
	}
	
	/**
	 * Create a personnel in branch 
	 * @param whichBranch is branch's index
	 * @param name is String for Transport Personnel name
	 * @param surname is String for Transport Personnel surname
	 */
	public void createTransportPersonnel(int whichBranch, String name, String surname)
	{
		if( whichBranch > auto.getBranchCount())
		{
			System.err.println("No branch in that index!");
			return;
		}
		
		TransportPersonnel[] transport = auto.getBranches()[whichBranch].getTransport();
		int transportCount = auto.getBranches()[whichBranch].getTransportCount();
		
		transport[transportCount] = new TransportPersonnel(name, surname, auto.getBranches()[whichBranch]);
		auto.getBranches()[whichBranch].setTransportCount( auto.getBranches()[whichBranch].getTransportCount() + 1);
	}
	
	/**
	 * Method for erase a personnel in branch
	 * @param whichBranch is the index of branch
	 * @param index is position of personnel that will be deleted
	 */
	public void eraseTransportPersonnel( int whichBranch, int index )
	{
		if( whichBranch > auto.getBranchCount())
		{
			System.err.println("No branch in that index!");
			return;
		}
		if( index > auto.getBranches()[whichBranch].getTransportCount())
		{
			System.err.println("No personnel in that index!");
			return;
		}
		TransportPersonnel[] transport = auto.getBranches()[whichBranch].getTransport();
		for(int i = index; i < auto.getBranches()[whichBranch].getTransportCount() -1; i++)
		{
			transport[i] = transport[i + 1];
		}
		auto.getBranches()[whichBranch].setTransportCount( auto.getBranches()[whichBranch].getTransportCount() - 1);
	}
}
