package book;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class BookManager {
    public static final List<Book> booksArr = new ArrayList<>();
    
    public static void loadBooks() throws IOException {
        List<Book> resultBookArr = BookFileManager.getBooksFromFile();
        for(Book book : resultBookArr) {
            booksArr.add(book);
        }
    }
    
    public static void saveBooks() throws IOException {
        BookFileManager.writeBooksIntoFile(booksArr);
    }
}
