/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javastudent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author henar
 */
public class StudentRegistry {
    // Get the file of the students data
    private static File getFile() {
        String fileRoute = System.getProperty("user.dir");
        String separator = File.separator;
        
        // Create directory for files in project route if not existed
        File directory = new File(fileRoute + separator + "files");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        // Create file to collect students in files directory if not existed
        File record = new File(directory + separator + "record.txt");
        if (!record.exists()) {
            try {
                record.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }
        return record;
    }
    
    // Create a bufferedReader to read the file that contains students info 
    private static BufferedReader getBufferedReaderFile() {
        File record = getFile();
        BufferedReader brFile = null;
        try {
            FileReader fr = new FileReader(record);
            brFile = new BufferedReader(fr); 
        } catch (IOException e) {
            System.out.println("Error reading to file " + record.getName());
        }
        return brFile;
    }
    
    // Create a bufferedWriter to write the file that contains students info 
    private static BufferedWriter getBufferedWriter(Boolean overwrite) {
        File record = getFile();
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(record, overwrite);
            bw = new BufferedWriter(fw);
            return bw;
        } catch (IOException e) {
            System.out.println("Error writing to file " + record.getName());
        }
        return bw;
    }
       
    // Get an ArrayList of students to edit the file
    public static ArrayList<Student> getStudentsFromFile() {
        ArrayList<Student> students = new ArrayList<>();
        BufferedReader brFile = getBufferedReaderFile();
        try {
            String line;
            String [] lineValues;
            // For each line of the file if there's info collect it to the ArrayList.
            while (true) {
                line = brFile.readLine();
                
                if (line == null) {
                    break;
                }
                
                if (line.isEmpty()) {
                    continue;
                }
                
                lineValues = line.split(";");
                students.add(new Student(lineValues[0], lineValues[1], Integer.valueOf(lineValues[2]), lineValues[3], lineValues[4]));
            }
            brFile.close(); 
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return students;
    }
    
    // Add the student info to the end of the file
    public static void addStudent(String firstName, String lastName, int age, String course, String nid) {
        Student student = new Student(firstName, lastName, age, course, nid);

        BufferedWriter bw = getBufferedWriter(true);
        try {
            // Add to the last line the info of a student in a formmated way
            bw.write(student.getFirstName() + ";" + student.getLastName() + ";" + student.getAge() + ";" + student.getCourse() + ";" + student.getNid());
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
    
    public static void removeStudent(String nid) {
        // Collect all the data of the file to an ArrayList
        ArrayList<Student> students = getStudentsFromFile();

        Student studentToRemove = null;
        // Search all the ArrayList until a student is found by the NID
        for (Student student : students) {
            if (student.getNid().equals(nid)) {    
                studentToRemove = student;
                break;
            }
        }
        // Remove that student found
        students.remove(studentToRemove);
        
        // Write again all the data from the ArrayList to the file but without the student that was removed 
        String content = "";
        for (Student student : students) {
            content += student.getFirstName() + ";" + student.getLastName() + ";" + student.getAge() + ";" + student.getCourse() + ";" + student.getNid() + "\n";
        }
        
        BufferedWriter bwFile = getBufferedWriter(false);
        try {
            bwFile.write(content);
            bwFile.flush();
            bwFile.close(); 
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }   
    }
    
    public static Student searchStudentByNid(String nid) {
        ArrayList<Student> students = getStudentsFromFile();
        
        for (Student student : students) {
            if (student.getNid().equals(nid)) {    
                return student;
            }
        }
        
        return null;
    }
}