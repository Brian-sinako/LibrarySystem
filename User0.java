import java.util.ArrayList;
import java.util.List;

public class User0 {
    protected String username;
    protected String password;
    protected String name;
    protected String surname;
    protected String email;
    private List<Book> loanedBooks;

    public User0(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.loanedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public void loanBook(Book book) {
        loanedBooks.add(book);
    }

    public void returnBook(Book book) {
        loanedBooks.remove(book);
    }
}

