package HW1_part2;

/**
 * This is an interface to use Login for all object and do something according to that object
 * @author Türker Tercan
 *
 */
public interface LoginInterface {
	/**
	 * A static public company in order to accessible for all object
	 */
	Company company = new Company("PTT Kargo");
	
	/**
	 * Log out for all actor objects
	 * @return null to exit
	 */
	LoginInterface LogOut( );
	
	/**
	 * Shows a menu according to that object
	 */
	void ShowMenu();
	
	/**
	 * This method helps user interface. It provides access to necessary methods 
	 * @param choice is an integer to which method will progress
	 * @return this or calls LogOut()
	 * @throws Exception throws our exception if something bad is happened
	 */
	LoginInterface Activity( int choice ) throws Exception;
	
}
