package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class StudentDetailsData {

    private static StudentDetailsData instance = new StudentDetailsData();

    private ObservableList<StudentDetails> studentDetailsList = FXCollections.observableArrayList();

    public ObservableList<StudentDetails> getStudentDetailsList() {
        return studentDetailsList;
    }

    private StudentDetailsData() {

    }

    public static StudentDetailsData getInstance() {
        return instance;
    }

    public void addNewStudent(StudentDetails studentDetails) {
        studentDetailsList.add(studentDetails);
    }

    public void loadStudentData() throws IOException {
        studentDetailsList.clear();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("students.txt"))) {
            String input;
            while ((input = bufferedReader.readLine())!=null) {
                String[] strings = input.split("   ");
                StudentDetails student = new StudentDetails(Integer.parseInt(strings[0]),strings[1],strings[2],strings[3]);
                studentDetailsList.add(student);
            }
        }catch (IOException e) {
            throw new IOException("I/O Exception");
        }
    }

    public void saveStudentData() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("students.txt"))) {
            for (StudentDetails student : getStudentDetailsList()) {
                bufferedWriter.write(student.getStudentID() + "   " +
                                            student.getName() + "   " +
                                                student.getCourse() + "   " +
                                                student.getSemester());
                bufferedWriter.newLine();
            }
        }
    }
}
