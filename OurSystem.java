import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

class OurSystem {
    private Map<String, Book> books;
    private Map<String, User0> users;
    private Map<String, String> loggedInUsers;
    private Map<String, String> loanedBooks; // Track loaned books

    public OurSystem() {
        books = new HashMap<>();
        users = new HashMap<>();
        loggedInUsers = new HashMap<>();
        loanedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public void addUser(User0 user) {
        users.put(user.getEmail(), user);
    }

    public boolean registerUser(String username, String password, String name, String surname, String email) {
        if (users.containsKey(email)) {
            return false; // My existing User
        }
        User0 newUser = new User0(username, password, name, surname, email);
        addUser(newUser);
        return true;
    }

    public boolean login(String email, String password) {
        User0 user = users.get(email);
        if (user != null && user.password.equals(password)) {
            loggedInUsers.put(email, password);
            return true;
        }
        return false;
    }

    public void checkOutBook(String email, String isbn) {
        User0 user = users.get(email);
        Book book = books.get(isbn);
        if (user != null && book != null && !loanedBooks.containsKey(isbn)) {
            user.loanBook(book);
            loanedBooks.put(isbn, email);
        }
    }

    public void returnBook(String email, String isbn) {
        User0 user = users.get(email);
        Book book = books.get(isbn);
        if (user != null && book != null && loanedBooks.containsKey(isbn) && loanedBooks.get(isbn).equals(email)) {
            user.returnBook(book);
            loanedBooks.remove(isbn);
        }
    }

    public void logout(String email) {
        loggedInUsers.remove(email);
    }

    public boolean isLoggedIn(String email) {
        return loggedInUsers.containsKey(email);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<User0> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Book> getBorrowedBooks(String email) {
        User0 user = users.get(email);
        if (user != null) {
            return user.getLoanedBooks();
        }
        return new ArrayList<>();
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (!loanedBooks.containsKey(book.getISBN())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
