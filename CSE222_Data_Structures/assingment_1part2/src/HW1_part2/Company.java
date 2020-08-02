package HW1_part2;

import java.util.ArrayList;

/**
 * Company class that stores every array
 * @author Türker Tercan
 *
 */
public class Company {

	/**
	 * Company's name
	 */
	private String companyName;
	
	/**
	 * ArrayList that stores admins
	 */
	private ArrayList<Administrator> admins = new ArrayList<Administrator>();
	
	/**
	 * ArrayList that stores customers
	 */
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	/**
	 * ArrayList that stores branches
	 */
	private ArrayList<Branch> branches = new ArrayList<Branch>();
	
	/**
	 * Basic constructor for company
	 * @param name company's name
	 */
	public Company( String name) {
		// TODO Auto-generated constructor stub
		companyName = name;
	}
	
	/**
	 * Getter for admins
	 * @return admins
	 */
	public ArrayList<Administrator> getAdmins()
	{
		return admins;
	}
	
	/**
	 * Getter for customers
	 * @return customers
	 */
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
	/**
	 * Getter for branches
	 * @return branches
	 */
	public ArrayList<Branch> getBranches()
	{
		return branches;
	}
	
	/**
	 * It checks all actor if there is any match returns it
	 * @param name actor's name
	 * @param surname actor's surname
	 * @param password actor's password
	 * @return if finds the actor return it. Otherwise, return null
	 */
	public LoginInterface Find( String name, String surname, String password )
	{
		for( int i = 0; i < admins.size(); i++)
		{
			if( admins.get(i).getName().equals(name) )
			{
				if( admins.get(i).getSurname().equals(surname))
				{
					if( admins.get(i).getPassword().equals(password) )
					{
						return admins.get(i);
					}
				}
			}
		}
			
			
		for( int i = 0; i < customers.size(); i++ )
			if( customers.get(i).getName().equals(name))
				if( customers.get(i).getSurname().equals(surname) )
					return customers.get(i);
		
		for( int i = 0; i < branches.size(); i++ )
		{
			for( int j = 0; j < branches.get(i).getEmployees().size(); j++ )
			{
				if( branches.get(i).getEmployees().get(j).getName().equals(name))
				{
					if( branches.get(i).getEmployees().get(j).getSurname().equals(surname))
					{
						if( branches.get(i).getEmployees().get(j).getPassword().equals(password))
						{
							return branches.get(i).getEmployees().get(j);
						}
					}
				}
			}
						
			for( int j = 0; j < branches.get(i).getPersonnels().size(); j++ )
				if( branches.get(i).getPersonnels().get(j).getName().equals(name))
					if( branches.get(i).getPersonnels().get(j).getSurname().equals(surname))
						if( branches.get(i).getPersonnels().get(j).getPassword().equals(password))
							return branches.get(i).getPersonnels().get(j);
		}
		System.err.print("No such personnel is found\n");
		return null;
	}
	
	/**
	 * Adds admin to ArrayList
	 * @param admin that will be added to Array
	 */
	public void CreateAdmin(Administrator admin)
	{
		admins.add(admin);
	}
	
	/**
	 * Return companyName
	 */
	@Override
	public String toString()
	{
		return companyName;
	}
}
