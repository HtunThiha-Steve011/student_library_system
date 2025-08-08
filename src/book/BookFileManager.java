package book;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class BookFileManager {
    private static File bookFile = new File("src/static/books.txt");
    
    public static List<Book> getBooksFromFile() throws IOException {
        if(!bookFile.exists()) {
            bookFile.createNewFile();
            BufferedWriter bookFileWriter = new BufferedWriter(new FileWriter(bookFile));
            bookFileWriter.write(
                "5;\n" +
                "Java 123;Tan Ken;89011;12.9;Programming;true;\n" +
                "Python for Data Science;Karen Lim;76111;29.9;Gen AI;true;\n" +
                "JavaScript for Beginner;Paul Mad;30011;28.9;Programming;true;\n" +
                "Learn to Design Mobile App;Joan Chan;66011;25.9;Design;true;\n" +
                "Flutter ABC;Tim Joe;32011;33.9;Mobile App;true;"
            );
            bookFileWriter.close();
        }
        
        List<Book> resultBooksArr = new ArrayList<>();
        BufferedReader bookFileReader = new BufferedReader(new FileReader(bookFile));
        
        String s;
        int lineCount = 0;
        while((s = bookFileReader.readLine()) != null) {
            lineCount++;
            if(lineCount != 1) {
                Book book = parseLineIntoBook(s);
                resultBooksArr.add(book);
            }
        }
        
        bookFileReader.close();
        return resultBooksArr;
    }
    
    private static Book parseLineIntoBook(String line) {
        String[] bookProperties = line.split(";");

        String name = bookProperties[0];
        String author = bookProperties[1];
        String isbn = bookProperties[2];
        double price = Double.parseDouble(bookProperties[3]);
        String category = bookProperties[4];
        boolean isAvailable = Boolean.parseBoolean(bookProperties[5]);
        
        return new Book(name, author, isbn, price, category, isAvailable);
    }
    
    public static void saveBooks() throws IOException {
        List<Book> latestBooksArr = BookManager.booksArr;
        int numOfBooks = latestBooksArr.size();
        bookFile.delete();
        bookFile.createNewFile();
        
        BufferedWriter bookFileWriter = new BufferedWriter(new FileWriter(bookFile));

        bookFileWriter.write(latestBooksArr.size() + ";\n");
        for(int i = 0; i < numOfBooks; i++) {
            Book book = latestBooksArr.get(i);
            bookFileWriter.write(String.format(
                    "%s;%s;%s;%.2f;%s;%s",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getCategory(),
                    String.valueOf(book.getAvailable())
            ));
            
            if(i != numOfBooks - 1) {
                bookFileWriter.write("\n");
            }
        }
        bookFileWriter.close();
    }
}
