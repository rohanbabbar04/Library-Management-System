package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentActivityData {

    private static StudentActivityData instance = new StudentActivityData();
    private ObservableList<StudentActivityDetails> observableList = FXCollections.observableArrayList();
    private final DateTimeFormatter formatter;

    private StudentActivityData() {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public static StudentActivityData getInstance() {
        return instance;
    }

    public ObservableList<StudentActivityDetails> getObservableList() {
        return observableList;
    }

    public void addToObservableList(StudentActivityDetails studentActivityDetails) {
        observableList.add(studentActivityDetails);
    }

    public void removeFromObservableList(StudentActivityDetails studentActivityDetails) {
        observableList.remove(studentActivityDetails);
    }

    public void loadData() throws IOException {
        observableList.clear();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("studentActivity.txt"))) {
            String input;
            while ((input = bufferedReader.readLine())!=null) {
                String[] stringArray = input.split("   ");
                LocalDate dateOfIssue = LocalDate.parse(stringArray[4]);
                LocalDate dateOfReturn = LocalDate.parse(stringArray[5]);
                observableList.add(new StudentActivityDetails(Integer.parseInt(stringArray[0]),stringArray[1],Integer.parseInt(stringArray[2]),stringArray[3],dateOfIssue,dateOfReturn));
            }
        }catch (IOException e) {
            throw new IOException("I/O Exception");
        }
    }

    public void saveData() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("studentActivity.txt"))) {
            for (StudentActivityDetails details : observableList) {
                bufferedWriter.write(details.getBookID() + "   "
                        + details.getTitle() + "   "
                        + details.getStudentID() + "   "
                        + details.getStudentName() + "   "
                        + details.getDateOfIssue() + "   "
                        + details.getDateOfReturn());
                bufferedWriter.newLine();
            }
        }catch (IOException e) {
            throw new IOException("I/O Exception");
        }

    }
}
