package HW1_part2;

/**
 * This abstract class that will be superclass for Admin, Employee and TransportPersonnel
 * @author Türker Tercan
 *
 */
public abstract class Personnel implements LoginInterface {

	/**
	 * Personnel's name
	 */
	String name;
	
	/**
	 * Personnel's surname
	 */
	String surname;
	
	/**
	 * Personnel's password
	 */
	String password;
	
	/**
	 * Basic constructor for Personnel
	 * @param name is personnel's name
	 * @param surname is personnel's surname
	 * @param password is personnel's password
	 */
	Personnel( String name, String surname, String password )
	{
		this.name = name;
		this.surname = surname;
		this.password = password;
	}
	
	/**
	 * Getter name
	 * @return name
	 */
	public String getName() { return this.name; }
	
	/**
	 * Getter surname
	 * @return surname
	 */
	public String getSurname() { return this.surname; }
	
	/**
	 * Getter password
	 * @return password
	 */
	public String getPassword() { return this.password; }
	

	/**
	 * Prints personnel
	 */
	@Override
	public String toString()
	{
		return String.format("%s %s", name, surname);
	}
}
