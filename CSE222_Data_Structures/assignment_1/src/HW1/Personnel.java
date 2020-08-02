package HW1;

/**
 * This is a super class for all employees in the system
 * @author Türker Tercan
 *
 */
public abstract class Personnel {
	
	/**
	 * String for first name
	 */
	private String name;
	
	/**
	 * String for surname
	 */
	private String surname;
	
	/**
	 * Basic constructor for Personnel
	 * @param newName is String to store name
	 * @param newSurname is String to store surname
	 */
	Personnel( String newName, String newSurname )
	{
		name = newName;
		surname = newSurname;
	}
	
	/**
	 * Returns name
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns surname
	 * @return String surname
	 */
	public String getSurname()
	{
		return surname;
	}
	
	/**
	 * Change the name
	 * @param newName is String to change the name
	 */
	public void setName( String newName )
	{
		name = newName;
	}
	
	/**
	 * Change the surname
	 * @param newSurname is String to change the surname
	 */
	public void setSurname( String newSurname )
	{
		surname = newSurname;
	}
	
	/**
	 * Override toString method
	 */
	public String toString()
	{
		return String.format("%s %s", getName(), getSurname());
	}
	
}
