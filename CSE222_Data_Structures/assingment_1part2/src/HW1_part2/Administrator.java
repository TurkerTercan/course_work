package HW1_part2;

import java.util.Scanner;

/**
 * This class implements all needed methods for admininistrator
 * @author Türker Tercan
 *
 */
public class Administrator extends Personnel {

	/**
	 * I need to store selected branch in order to do remove it
	 */
	private Branch selectedBranch;
	/**
	 * Selected branch's index in ArrayList
	 */
	private int indexBranch = -1;
	
	/**
	 * I need to store selected branch employee in order to do remove it
	 */
	private BranchEmployee selectedEmployee;
	/**
	 * Selected branch employee's index in ArrayList
	 */
	private int indexEmployee = -1;
	
	/**
	 * I need to store selected transport personnel in order to do remove it
	 */
	private TransportPersonnel selectedPersonnel;
	
	/**
	 * Selected transport personnel's index in ArrayList
	 */
	private int indexPersonnel = -1;
	/**
	 * Basic Constructor for admins
	 * @param name is a String for first name
	 * @param surname is a String for second name
	 * @param password is a String for password
	 */
	public Administrator(String name, String surname, String password) {
		super(name, surname, password);
		// TODO Auto-generated constructor stub
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
		
		build.append("~ ~ ~ ADMIN MENU ~ ~ ~\n");
		build.append("1 - Create Branch\n"
				+ "2 - Select a Branch\n"
				+ "3 - Remove selected Branch\n"
				+ "4 - Create Branch Employee within selected Branch\n"
				+ "5 - Select a Branch Employee from selected Branch\n"
				+ "6 - Remove selected Branch Employee\n"
				+ "7 - Create Transport Personnel within selected Branch\n"
				+ "8 - Select a Transport Personnel from selected Branch\n"
				+ "9 - Remove selected Transport Personnel\n"
				+ "10- Log Out\nChoice: ");
		
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
	public LoginInterface Activity( int choice ) throws Exception
	{
		if( choice < 1 || choice > 10 )
			throw new Exception("Invalid Choice");
		
		Scanner scan = new Scanner(System.in);
		switch(choice)
		{
		case 1:
			System.out.print("Enter your branch name: ");
			String branchName = scan.next();
			CreateBranch(new Branch(branchName));
			break;
		case 2: int i = ShowBranches();
			if( i == 0 )
			{
				throw new Exception("There is no branch to select");
			}
			System.out.print("Select a branch: ");
			indexBranch = scan.nextInt();
			if( indexBranch < 1 || indexBranch > i)
			{
				indexBranch = -1;
				throw new Exception("Invalid Choice");
			}
			selectedBranch = LoginInterface.company.getBranches().get(indexBranch - 1);
			break;
		case 3:
			if( selectedBranch == null)
			{
				throw new Exception("Selected Branch is null");
			}
			this.RemoveBranch(selectedBranch);
			break;
		case 4:
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			System.out.print("Name: ");
			String employeeName = scan.next();
			System.out.print("Surname: ");
			String employeeSurname = scan.next();
			System.out.print("Password: ");
			String employeePassword = scan.next();
			CreateBranchEmployee( indexBranch - 1, employeeName, employeeSurname, employeePassword);
			break;
		case 5:
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			int x = selectedBranch.ShowBranchEmployees();
			if( x == 0 )
			{
				throw new Exception("There is no branch employee in selected branch");
			}
			System.out.print("Select a branch employee: ");
			indexEmployee = scan.nextInt();
			if( indexEmployee < 1 || indexEmployee > x )
			{
				indexEmployee = -1;
				throw new Exception("Invalid Choice");
			}
			this.selectedEmployee = selectedBranch.getEmployees().get(indexEmployee - 1);
			break;
		case 6:
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			if( selectedEmployee == null || indexEmployee == -1)
			{
				throw new Exception("Selected Branch Employee is null");
			}
			this.RemoveBranchEmployee(indexBranch -1, selectedEmployee);
			break;
		case 7:
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			System.out.print("Name: ");
			String personnelName = scan.next();
			System.out.print("Surname: ");
			String personnelSurname = scan.next();
			System.out.print("Password: ");
			String personnelPassword = scan.next();
			
			this.CreateTransportPersonnel(indexBranch - 1, personnelName, personnelSurname, personnelPassword);
			break;
		case 8: 
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			int y = selectedBranch.ShowTransportPersonnels();
			if( y == 0 )
			{
				throw new Exception("There is no transport personnel in selected branch");
			}
			System.out.print("Select a transport personnel: ");
			indexPersonnel = scan.nextInt();
			if( indexPersonnel < 1 || indexPersonnel > y )
			{
				indexPersonnel = -1;
				throw new Exception("Invalid Choice");
			}
			this.selectedPersonnel = selectedBranch.getPersonnels().get(indexPersonnel - 1);
			break;
		case 9:
			if( selectedBranch == null || indexBranch == -1)
			{
				throw new Exception("Selected Branch is null");
			}
			if( selectedPersonnel == null || indexPersonnel == -1)
			{
				throw new Exception("Selected Transport Personnel is null");
			}
			this.RemoveTransportPersonnel(indexBranch -1, selectedPersonnel);
			break;
		case 10: return this.LogOut();
		}
		return this;
	}
	
	/**
	 * Prints branches
	 * @return Branches size
	 */
	public int ShowBranches()
	{
		StringBuilder build = new StringBuilder();
		for( int i = 0; i < LoginInterface.company.getBranches().size(); i++)
		{
			build.append(Integer.toString(i + 1) + " - " + LoginInterface.company.getBranches().get(i) + "\n");
		}
		System.out.print(build.toString());
		
		return LoginInterface.company.getBranches().size();
	}
	
	/**
	 * Adds a branch to ArrayList
	 * @param branch is new branch that will be added to ArrayList
	 */
	public void CreateBranch(Branch branch)
	{
		LoginInterface.company.getBranches().add(branch);
		System.out.println("Branch successfully created");
	}
	
	/**
	 * Adds a branch employee to ArrayList in given branch index
	 * @param index Branch's index in ArrayList
	 * @param name new employee's name
	 * @param surname new employee's surname
	 * @param password new employee's password
	 * @throws Exception No branch that index
	 */
	public void CreateBranchEmployee( int index, String name, String surname, String password ) throws Exception
	{
		if( LoginInterface.company.getBranches().size() < index )
		{
			throw new Exception("There is no branch at this index");
		}
		
		else
		{
			LoginInterface.company.getBranches().get(index).getEmployees()
				.add(new BranchEmployee(LoginInterface.company.getBranches().get(index), name, surname, password));
			System.out.println("Branch Employee successfully created");
		}
	}
	
	/**
	 * Adds a transport personnel to ArrayList in given branch index
	 * @param index index Branch's index in ArrayList
	 * @param name new transport personnel's name
	 * @param surname new transport personnel's surname
	 * @param password new transport personnel's password
	 * @throws Exception Exception No branch that index
	 */
	public void CreateTransportPersonnel( int index, String name, String surname, String password  ) throws Exception
	{
		if( LoginInterface.company.getBranches().size() < index )
		{
			throw new Exception("There is no branch at this index");
		}
		
		else
		{
			LoginInterface.company.getBranches().get(index).getPersonnels()
				.add(new TransportPersonnel(LoginInterface.company.getBranches().get(index), name, surname, password));
			System.out.println("Transport Personnel successfully created");
		}
	}
	
	/**
	 * Removes given branch from ArrayList
	 * @param branch the branch that will be erased
	 * @throws Exception No such branch in ArrayList
	 */
	public void RemoveBranch( Branch branch ) throws Exception
	{
		if( !LoginInterface.company.getBranches().remove(branch) )
		{
			throw new Exception("No such branch was found!");
		}
		indexBranch = -1;
		System.out.println("Branch successfully removed");
	}
	
	/**
	 * Removes given branch employee from ArrayList at given branch index
	 * @param index branch index in ArrayList
	 * @param employee the employee that will be removed
	 * @throws Exception no branch at that index or no employee like that in ArrayList
	 */
	public void RemoveBranchEmployee( int index, BranchEmployee employee ) throws Exception
	{
		if( LoginInterface.company.getBranches().size() < index )
		{
			throw new Exception("There is no branch at this index");
		}
		
		if( !LoginInterface.company.getBranches().get(index).getEmployees().remove(employee) )
		{
			throw new Exception("No such employee was found!");
		}
		indexEmployee = -1;
		System.out.println("Branch Employee successfully removed");
	}
	
	/**
	 * Removes given transport personnel from ArrayList at given branch index
	 * @param index index branch index in ArrayList
	 * @param personnel the personnel that will be removed
	 * @throws Exception no branch at that index or no employee like that in ArrayList
	 */
	public void RemoveTransportPersonnel( int index, TransportPersonnel personnel ) throws Exception
	{
		if( LoginInterface.company.getBranches().size() < index )
		{
			throw new Exception("There is no branch at this index");
		}
		
		if( !LoginInterface.company.getBranches().get(index).getPersonnels().remove(personnel) )
		{
			throw new Exception("No such personnel was found!");
		}
		indexPersonnel = -1;
		System.out.println("Transport Personnel successfully removed");
	}

	
}
