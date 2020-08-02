import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Library automation system. The system has two types of users: administrators who can update
 * information and users who browse books.
 */
public class LibrarySystem {
    /**
     * Password to gain access by admins
     */
    private String password = "password";

    /**
     * the outermost map, the author name is used as a key,
     * the value is another map whose keys are book names. In the inner map, the values are sets showing the
     * location (or locations if there is more than one copy) of that book.
     */
    private Map<String,Map<String,Map<String,String>>> outerMap;

    /**
     * I used enum to store status
     */
    enum Status {
        available, not_available, reserved;
        @Override
        public String toString() {
            if (this == Status.available)
                return "available";
            else if (this == Status.not_available)
                return "not available";
            else
                return "reserved";
        }
    }

    /**
     * A normal library user can only does search methods
     */
    public class LibraryUser {
        /**
         * Private scanner
         */
        private Scanner scan;

        /**
         * Basic constructor
         */
        LibraryUser() {
            scan = new Scanner(System.in);
        }

        /**
         * Search whole library by author name.
         * When searching by author name, all books of the author in the library will be listed on the screen.
         * Then, whichever book the user chooses, the location(s) of that book will be displayed.
         * @param author author's name
         */
        public void searchByAuthor(String author) {
            if (outerMap.get(author) != null) {
                int i = 1;
                for (Map.Entry<String, Map<String, String>> title : outerMap.get(author).entrySet()) {
                    System.out.println(i++ + "- " + author + " : " + title.getKey());
                }
                System.out.println("Choice: ");
                int index = scan.nextInt();
                if (index < 1 || index > i) {
                    System.out.println("Invalid choice!");
                    return;
                }
                i = 1;
                for (Map.Entry<String, Map<String, String>> entry : outerMap.get(author).entrySet()) {
                    if ( i == index) {
                        for (Map.Entry<String, String> entry1 : outerMap.get(author).get(entry.getKey()).entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry1.getKey() + " - " + entry1.getValue());
                        }
                    }
                    i++;
                }
            }
            else
                System.out.println("No such author is found");
        }

        /**
         * Search whole library by book title.
         * When searching by book title, author name, location and status will be shown
         * @param title Book's title
         */
        public void searchByTitle(String title) {
            int i = 1;
            for (Map.Entry<String, Map<String, Map<String, String>>> entry : outerMap.entrySet()) {
                for (Map.Entry<String, Map<String, String>> entry1 : outerMap.get(entry.getKey()).entrySet()) {
                    if (entry1.getKey().equals(title)) {
                        for (Map.Entry<String, String> entry2 : entry1.getValue().entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry1.getKey() + " - " + entry2.getKey() + " - " + entry2.getValue());
                        }
                    }
                }
            }
        }
    }

    /**
     * When the administrator enters the system with a password, he will be able to add books, delete
     * books, update information.
     */
    public class LibraryAdmin {
        /**
         * Private scanner to take int from user
         */
        private Scanner scan;

        /**
         * Basic constructor
         */
        LibraryAdmin() {
            scan = new Scanner(System.in);
        }

        /**
         * Search whole library by author name.
         * When searching by author name, all books of the author in the library will be listed on the screen.
         * Then, whichever book the user chooses, the location(s) of that book will be displayed.
         * @param author author's name
         */
        public void searchByAuthor(String author) {
            if (outerMap.get(author) != null) {
                int i = 1;
                for (Map.Entry<String, Map<String, String>> title : outerMap.get(author).entrySet()) {
                    System.out.println(i++ + "- " + author + " : " + title.getKey());
                }
                System.out.println("Choice: ");
                int index = scan.nextInt();
                if (index < 1 || index > i) {
                    System.out.println("Invalid choice!");
                    return;
                }
                i = 1;
                for (Map.Entry<String, Map<String, String>> entry : outerMap.get(author).entrySet()) {
                    if ( i == index) {
                        for (Map.Entry<String, String> entry1 : outerMap.get(author).get(entry.getKey()).entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry1.getKey() + " - " + entry1.getValue());
                        }
                    }
                    i++;
                }
            }
            else
                System.out.println("No such author is found");
        }
        /**
         * Search whole library by book title.
         * When searching by book title, author name, location and status will be shown
         * @param title Book's title
         */
        public void searchByTitle(String title) {
            int i = 1;
            for (Map.Entry<String, Map<String, Map<String, String>>> entry : outerMap.entrySet()) {
                for (Map.Entry<String, Map<String, String>> entry1 : outerMap.get(entry.getKey()).entrySet()) {
                    if (entry1.getKey().equals(title)) {
                        for (Map.Entry<String, String> entry2 : entry1.getValue().entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry1.getKey() + " - " + entry2.getKey() + " - " + entry2.getValue());
                        }
                    }
                }
            }
        }

        /**
         * Adds books to map
         * @param author book's author
         * @param title book's title
         * @param location book's location in library
         * @param status book's current status
         */
        public void addBook(String author, String title, String location, Status status) {
            if (outerMap.get(author) == null) {
                Map<String, String> temp = new HashMap<>();
                temp.put(location,status.toString());
                Map<String, Map<String,String>> tempTitle = new HashMap<>();
                tempTitle.put(title, temp);
                outerMap.put(author, tempTitle);
            }
            else {
                if (outerMap.get(author).get(title) == null) {
                    Map<String, String> temp = new HashMap<>();
                    temp.put(location,status.toString());
                    outerMap.get(author).put(title, temp);
                }
                else {
                    outerMap.get(author).get(title).put(location,status.toString());
                }
            }
        }

        /**
         * Deletes the book in the library
         * @param author book's author
         * @param title book's title
         * @param location book's location
         */
        public void deleteBook(String author, String title, String location) {
            outerMap.get(author).get(title).remove(location);
        }

        /**
         * Updates information about the given book
         * @param author book's author
         * @param title book's title
         * @param location book's old location
         * @param newLocation book's new location
         * @param newStatus book's new status
         */
        public void updateInformation(String author, String title, String location, String newLocation, Status newStatus) {
            outerMap.get(author).get(title).remove(location);
            addBook(author,title,newLocation,newStatus);
        }
    }

    /**
     * Basic constructor for outerMap
     */
    public LibrarySystem() {
        outerMap = new HashMap<>();
    }

    /**
     * Overrided toString method to print whole library
     * @return StringBuilder.toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Map<String, Map<String, String>>> entryAuthor : outerMap.entrySet()) {
            for (Map.Entry<String, Map<String, String>> entryTitle : outerMap.get(entryAuthor.getKey()).entrySet()) {
                for (Map.Entry<String, String> entryLocation : outerMap.get(entryAuthor.getKey()).get(entryTitle.getKey()).entrySet()){
                    sb.append(i++).append("- ").append(entryAuthor.getKey()).append(" -> ").append(entryTitle.getKey()).
                            append(" -> ").append(entryLocation.getKey()).append(" -> ").append(entryLocation.getValue()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Creates admin
     * @param password checks password matches or not
     * @return new admin
     */
    public LibraryAdmin createAdmin(String password) {
        if (password.equals(this.password))
            return new LibraryAdmin();
        return null;
    }

    /**
     * Creates a new user
     * @return new user
     */
    public LibraryUser createUser() {
        return new LibraryUser();
    }


}
