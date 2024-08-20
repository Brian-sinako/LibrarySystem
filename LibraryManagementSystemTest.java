import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystemTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OurSystem lms = new OurSystem(); // Instantiate OurSystem

        // Initialize Books
        Book book1 = new Book("Shadows In The Moonlight", "Santa Montefiore", "3456");
        Book book2 = new Book("The Instruments of Darkness", "John Connolly", "789");
        Book book3 = new Book("Camilla Lackberg", "Santa Montefiore", "65432");
        Book book4 = new Book("A Crown That Lasts - You Are Not Your Label", "Demi-Leigh Tebow", "123456");

        // Added books on My Library
        lms.addBook(book1);
        lms.addBook(book2);
        lms.addBook(book3);
        lms.addBook(book4);

        // My User Internally
        User0 user1 = new User0("user1", "sinako1", "Sinako", "Mzotsho", "sinako.brian@gmail.com");
        User0 user2 = new User0("user2", "iviwe3", "Iviwe", "Funda", "iviwe.funda@gmail.com");
        lms.addUser(user1);
        lms.addUser(user2);

        while (true) {
            System.out.println("\nLibrary System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Search Book");
            System.out.println("5. Checkout Book");
            System.out.println("6. Return Book");
            System.out.println("7. List All Books");
            System.out.println("8. List All Users");
            System.out.println("9. View Borrowed Books");
            System.out.println("10. Add Book Manually");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
                continue;
            }

            switch (option) {
                case 1:
                    // Register User
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    if (lms.registerUser(username, password, name, surname, email)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("User already exists.");
                    }
                    break;

                case 2:
                    // Login
                    System.out.print("Enter email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (lms.login(loginEmail, loginPassword)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Check your credentials.");
                    }
                    break;

                case 3:
                    // Logout
                    System.out.print("Enter email: ");
                    String logoutEmail = scanner.nextLine();
                    lms.logout(logoutEmail);
                    System.out.println("Logged out successfully.");
                    break;

                case 4:
                    // Search Book
                    System.out.print("Enter book title to search: ");
                    String title = scanner.nextLine();
                    List<Book> searchResults = lms.searchByTitle(title);
                    if (searchResults.isEmpty()) {
                        System.out.println("No books found with that title.");
                    } else {
                        System.out.println("Search results:");
                        for (Book book : searchResults) {
                            System.out.println(book);
                        }
                    }
                    break;

                case 5:
                    // Checkout Book
                    System.out.print("Enter email: ");
                    String checkoutEmail = scanner.nextLine();
                    if (!lms.isLoggedIn(checkoutEmail)) {
                        System.out.println("You must be logged in to checkout a book.");
                        break;
                    }
                    System.out.println("Available books:");
                    List<Book> availableBooks = lms.getAvailableBooks();
                    for (Book book : availableBooks) {
                        System.out.println(book);
                    }
                    System.out.print("Enter ISBN of book to checkout: ");
                    String checkoutIsbn = scanner.nextLine();
                    if (lms.getBook(checkoutIsbn) != null) {
                        lms.checkOutBook(checkoutEmail, checkoutIsbn);
                        System.out.println("Book checked out successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 6:
                    // Return Book
                    System.out.print("Enter email: ");
                    String returnEmail = scanner.nextLine();
                    if (!lms.isLoggedIn(returnEmail)) {
                        System.out.println("You must be logged in to return a book.");
                        break;
                    }
                    System.out.print("Enter ISBN of book to return: ");
                    String returnIsbn = scanner.nextLine();
                    if (lms.getBook(returnIsbn) != null) {
                        lms.returnBook(returnEmail, returnIsbn);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 7:
                    // List All Books
                    System.out.println("Listing all books:");
                    List<Book> books = lms.getAllBooks();
                    for (Book book : books) {
                        System.out.println(book);
                    }
                    break;

                case 8:
                    // List All Users
                    System.out.println("Listing all users:");
                    List<User0> users = lms.getAllUsers();
                    for (User0 user : users) {
                        System.out.println(user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");
                    }
                    break;

                case 9:
                    // View Borrowed Books
                    System.out.print("Enter email: ");
                    String viewEmail = scanner.nextLine();
                    List<Book> borrowedBooks = lms.getBorrowedBooks(viewEmail);
                    if (borrowedBooks.isEmpty()) {
                        System.out.println("No books borrowed.");
                    } else {
                        System.out.println("Borrowed books:");
                        for (Book book : borrowedBooks) {
                            System.out.println(book);
                        }
                    }
                    break;

                case 10:
                    // Add Book Manually
                    System.out.print("Enter book title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String newISBN = scanner.nextLine();
                    Book newBook = new Book(newTitle, newAuthor, newISBN);
                    lms.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 11:
                    // Exit
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
