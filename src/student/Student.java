package student;

import book.Book;
import java.util.List;
import java.util.ArrayList;

public class Student {

    private String name;
    private String adminNumber;
    private List<Book> booksBorrowed = new ArrayList<>();

    public Student(String name, String adminNumber) {
        this.name = name;
        this.adminNumber = adminNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminNumber() {
        return this.adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }
    
    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
}
