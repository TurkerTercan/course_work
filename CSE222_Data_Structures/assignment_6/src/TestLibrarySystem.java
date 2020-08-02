public class TestLibrarySystem {
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        System.out.println(system);
        LibrarySystem.LibraryAdmin admin = system.createAdmin("password");
        LibrarySystem.LibraryUser user = system.createUser();

        //Add books to the system
        admin.addBook("F. Scott Fitzgerald", "The Great Gatsby", "1c1s.1", LibrarySystem.Status.available);
        admin.addBook("William Shakespeare", "Hamlet", "1c1s.2", LibrarySystem.Status.available);
        admin.addBook("Charlotte Brontë", "Jane Eyre", "1c1s.3", LibrarySystem.Status.available);
        admin.addBook("Oscar Wilde", "The Picture of Dorian Gray", "2c1s.3", LibrarySystem.Status.available);
        admin.addBook("Geoffrey Chaucer", "The Canterbury Tales", "2c1s.1", LibrarySystem.Status.available);
        admin.addBook("Jane Austen", "Pride and Prejudice", "1c2s.1", LibrarySystem.Status.available);
        admin.addBook("George Orwell", "Nineteen Eighty-Four", "3c3s.2", LibrarySystem.Status.available);
        admin.addBook("Mark Twain", "Adventures of Huckleberry Finn", "2c2s.1", LibrarySystem.Status.available);
        admin.addBook("Fyodor Dostoyevsky", "Crime and Punishment", "2c1s.2", LibrarySystem.Status.available);
        admin.addBook("William Shakespeare", "Macbeth", "2c1s.4", LibrarySystem.Status.available);
        admin.addBook("Mark Twain", "Adventures of Huckleberry Finn", "2c2s.1", LibrarySystem.Status.not_available);
        admin.addBook("William Shakespeare", "Hamlet", "3c3s.1", LibrarySystem.Status.available);

        //Search admin
        admin.searchByAuthor("William Shakespeare");

        //Update Info
        admin.updateInformation("Oscar Wilde","The Picture of Dorian Gray","2c1s.3", "3c1s.1", LibrarySystem.Status.not_available);
        admin.updateInformation("Jane Austen", "Pride and Prejudice", "1c2s.1", "2c2s.3", LibrarySystem.Status.reserved);
        admin.updateInformation("William Shakespeare", "Macbeth", "2c1s.4", "2c3s.1",LibrarySystem.Status.available);

        //Delete Book
        admin.deleteBook("Fyodor Dostoyevsky", "Crime and Punishment", "2c1s.2");
        admin.deleteBook("Jane Austen", "Pride and Prejudice", "1c2s.1");

        //User search by title
        user.searchByTitle("Hamlet");

        //Print all system
        System.out.println(system);
    }
}
