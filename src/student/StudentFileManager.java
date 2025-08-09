package student;

import book.Book;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class StudentFileManager {
    private static File studentFile = new File("src/static/students.txt");
    
    public static List<Student> getStudentsFromFile() throws IOException {
        if(!studentFile.exists()) {
            studentFile.createNewFile();
            BufferedWriter studentFileWriter = new BufferedWriter(new FileWriter(studentFile));
            studentFileWriter.write(
                "3;\n" +
                "Tan Ben;2501234;\n" +
                "2;\n" +
                "Java 123;Tan Ken;89011;12.9;Programming;\n" +
                "Learn to Design Mobile App;Joan Chan;66011;25.9;Design;\n" +
                "Janet Lee;2589238;\n" +
                "0;\n" +
                "Danny Ang;2523833;\n" +
                "1;\n" +
                "JavaScript for Beginner;Paul Mad;30011;28.9;Programming;"
            );
            studentFileWriter.close();
        }
        
        List<Student> resultStudentsArr = new ArrayList<>();
        BufferedReader studentFileReader = new BufferedReader(new FileReader(studentFile));
        
        String s;
        int lineCount = 0;
        int currentStudentBookAdditionIndex = -1;
        while((s = studentFileReader.readLine()) != null) {
            lineCount++;
            if(lineCount == 1) continue;
            String[] properties = s.split(";");
            if(properties.length == 2) {
                currentStudentBookAdditionIndex++;
                Student currentStudent = parseLineIntoStudent(properties);
                resultStudentsArr.add(currentStudent);
            }
            
            if(properties.length > 2) {
                Book book = parseLineIntoBook(properties);
                resultStudentsArr.get(currentStudentBookAdditionIndex).getBooksBorrowed().add(book);
            }
        }
        
        studentFileReader.close();
        return resultStudentsArr;
    }
    
    private static Student parseLineIntoStudent(String[] studentProperties) {
        String name = studentProperties[0];
        String adminNumber = studentProperties[1];
        Student student = new Student(name, adminNumber);
        return student;
    }
    
    private static Book parseLineIntoBook(String[] bookProperties) {

        String name = bookProperties[0];
        String author = bookProperties[1];
        String isbn = bookProperties[2];
        double price = Double.parseDouble(bookProperties[3]);
        String category = bookProperties[4];
        
        return new Book(name, author, isbn, price, category, false);
    }
    
    public static void writeStudentsIntoFile(List<Student> latestStudentsArr) throws IOException {
        int numOfStudents = latestStudentsArr.size();
        studentFile.delete();
        studentFile.createNewFile();
        
        BufferedWriter studentFileWriter = new BufferedWriter(new FileWriter(studentFile));

        studentFileWriter.write(latestStudentsArr.size() + ";\n");
        for(int i = 0; i < latestStudentsArr.size(); i++) {
            Student student = latestStudentsArr.get(i);
            List<Book> booksBorrowed = student.getBooksBorrowed();
            int numOfBooks = booksBorrowed.size();
            
            studentFileWriter.write(String.format("%s;%s\n", student.getName(), student.getAdminNumber()));
            studentFileWriter.write(numOfBooks + ";");
            if(!(numOfBooks == 0 && i == (latestStudentsArr.size() - 1))) {
                studentFileWriter.write("\n");
            }
            for(Book book : booksBorrowed) {
                studentFileWriter.write(String.format(
                        "%s;%s;%s;%.2f;%s;",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPrice(),
                        book.getCategory()
                ));
                if(!(booksBorrowed.indexOf(book) ==(booksBorrowed.size() - 1) && i == (latestStudentsArr.size() -1))) {
                    studentFileWriter.write("\n");
                }
                
            }
        }
        studentFileWriter.close();
    }
}
