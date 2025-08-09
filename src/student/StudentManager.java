package student;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class StudentManager {
    public static final List<Student> studentsArr = new ArrayList<>();
    
    public static void loadStudents() throws IOException {
        List<Student> resultStudentArr = StudentFileManager.getStudentsFromFile();
        for(Student student : resultStudentArr) {
            studentsArr.add(student);
        }
    }
    
    public static void saveStudents() throws IOException {
        StudentFileManager.writeStudentsIntoFile(studentsArr);
    }
}
