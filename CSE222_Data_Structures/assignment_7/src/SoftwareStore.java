
/**
 * Menu-driven program for managing a software store.
 * The system has two types of users:
 * administrators who can update information and users who browse software.
 * The system maintains the information about each
 * software which at least includes name (including version), quantity, and price.
 */
public class SoftwareStore {
    //Nested Classes
    /**
     * Class to store software
     */
    @SuppressWarnings("rawtypes")
    public static class Software implements Comparable{
        /**
         * Software's name (including version)
         */
        private final String name;
        /**
         * Software's quantity
         */
        private int quantity;
        /**
         * Software's price
         */
        private String price;

        /**
         * Basic constructor
         * @param name The name of the software including version
         * @param price Price of the software
         */
        public Software(String name, String price) {
            this.name = name;
            this.price = price;
            quantity = 1;
        }
        /** Method to increase quantity */
        public void increase() { quantity++; }
        /** Method to decrease quantity */
        public void decrease() { quantity--; }
        /**
         * Method to check quantity is 0
         * @return if quantity is 0, return true; otherwise, false
         */
        public boolean isEmpty() { return quantity <= 0; }

        /**
         * Overrided equals method to check two software's names and prices are equal
         * @param obj object that will be checked and it should be Software
         * @return return true if names and prices are equal, otherwise; false
         */
        @Override
        public boolean equals(Object obj) {
            return name.equals(((Software) obj).name) && price.equals(((Software) obj).price);
        }

        /**
         * Compares two Software object's names
         * @param o object that will be compared
         * @return return name.compareTo(o.name)
         */
        @Override
        public int compareTo(Object o) {
            return name.compareTo(((Software)o).name);
        }

        /**
         * Return a string representation of this object.
         * The name, price and quantity value is appended to the contents
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return "Software: " + name + ", Price: " + price + ", Quantity: " + quantity;
        }
    }
    //Data fields
    /**
     * To store data in a SearchTree interface
     * it should be possible to use any data
     * structure implanting search tree interface
     */
    private final SearchTree<Software> table;
    /**
     * Password to check admin's password is correct.
     */
    private static final String password = "password";

    //Constructor

    /**
     * Basic constructor
     * It adds some data to table
     * @param st SearchTree interface to store data
     */
    public SoftwareStore(SearchTree<Software> st) {
        table = st;
        table.add(new Software("Adobe Photoshop 6.0", "120$"));
        table.add(new Software("Adobe Photoshop 6.2", "40$"));
        table.add(new Software("Norton 4.5", "30$"));
        table.add(new Software("Norton 5.5", "20₺"));
        table.add(new Software("Adobe Flash 3.3", "FREE"));
        table.add(new Software("Adobe Flash 4.0", "FREE"));
    }

    /**
     * Administrator enters the system with a password, to be able to add, delete, update
     * information.
     */
    public class Admin {
        /**
         * Method to add a software to the table
         * @param name Software's name
         * @param price Software's price
         */
        public void add(String name, String price) {
            Software s = new Software(name, price);
            Software st = table.find(s);
            if (st != null) {
                st.increase();
            } else {
                table.add(s);
            }
        }

        /**
         * Delete a software from table
         * @param name Software's name
         * @param price Software's price
         */
        public void delete(String name, String price) {
            table.remove(new Software(name, price));
        }

        /**
         * Update the price of the software
         * @param name Software's name
         * @param price Software's price
         * @param newPrice Software's new price
         */
        public void update(String name, String price, String newPrice) {
            Software s = table.find(new Software(name, price));
            if (s != null) {
                s.price = newPrice;
            }
        }

        /**
         * Searches the table with given name
         * @param name Software's name
         */
        public void searchByName(String name) {
            Software s = table.find(new Software(name, ""));
            if (s != null)
                System.out.println(s.toString());
        }
    }

    /**
     * User enters the system. She/He can search store, search by name of the software
     * and buy a software
     */
    public class User {
        /**
         * Searches the table with given name
         * @param name Software's name
         */
        public void searchByName(String name) {
            Software s = table.find(new Software(name, ""));
            if (s != null)
                System.out.println(s.toString());
        }

        /**
         * Prints all store
         */
        public void searchStore() {
            System.out.println(table.toString());
        }

        /**
         * Buys a software from store. Finds the element from the table.
         * If it is null, return false. Otherwise, decrease the quantity of software
         * if there software is out of stock deletes it
         * @param name Software's name
         * @param price Software's price
         */
        public void buy(String name, String price) {
            Software s = table.find(new Software(name, price));
            if ( s == null )
                return;
            s.decrease();
            if (s.isEmpty())
                table.remove(s);
        }
    }

    /**
     * Return a string representation of this object.
     * The table of toString() is appended to the contents
     * @return String representation of this object
     */
    public String toString() {
        return table.toString();
    }

    /**
     * Creates an admin and returns it, if password is correct.
     * @param pass given password
     * @return if password is correct, return new object otherwise; null
     */
    public Admin createAdmin(String pass) {
        if (pass.equals(password))
            return new Admin();
        return null;
    }

    /**
     * Creates an user and returns it.
     * @return new User
     */
    public User createUser() {
        return new User();
    }

}
