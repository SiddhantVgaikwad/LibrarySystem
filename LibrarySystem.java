import java.lang.reflect.Array;
import java.util.*;

class Author{
    private String name;
    private String biography;

    public Author(String name, String biography){
        this.name = name;
        this.biography = biography;
    }

    public String getName(){
        return name;

    }

    public String getBiography()
    {
        return biography;
    }
}

class Book {
    private String title;
    private Author author;
    private int yearPublished;
    private boolean isAvailable;

    public Book(String title, Author author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// BookTransaction class
class BookTransaction {
    private Book book;
    private Date checkoutDate;
    private Date returnDate;

    public BookTransaction(Book book, Date checkoutDate) {
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

class Library {
    private List<Book> books;
    private List<BookTransaction> transactions;

    public Library() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void checkoutBook(Book book) {
        if (book.isAvailable()) {
            BookTransaction transaction = new BookTransaction(book, new Date());
            transactions.add(transaction);
            book.setAvailable(false);
            System.out.println("Book \"" + book.getTitle() + "\" checked out successfully.");
        } else {
            System.out.println("Sorry, the book \"" + book.getTitle() + "\" is not available for checkout.");
        }
    }

    public void returnBook(Book book) {
        for (BookTransaction transaction : transactions) {
            if (transaction.getBook().equals(book) && transaction.getReturnDate() == null) {
                transaction.setReturnDate(new Date());
                book.setAvailable(true);
                System.out.println("Book \"" + book.getTitle() + "\" returned successfully.");
                return;
            }
        }
        System.out.println("Error: This book was not checked out from the library.");
    }
}

public class LibrarySystem {
    public static void main(String[] args){

        Author author1 = new Author("new world","An accomplished author.");
                Author author2 = new Author("Jane Smith", "A prolific writer.");

        // Creating books
        Book book1 = new Book("Book 1", author1, 2020);
        Book book2 = new Book("Book 2", author2, 2018);

        // Creating a library
        Library library = new Library();

        // Adding books to the library
        library.addBook(book1);
        library.addBook(book2);

        // Checking out and returning books
        library.checkoutBook(book1);
        library.returnBook(book1);

        // Trying to return a book that wasn't checked out
        library.returnBook(book2);
        

    }
    
}
