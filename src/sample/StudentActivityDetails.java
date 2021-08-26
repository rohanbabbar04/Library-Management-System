package sample;

import java.time.LocalDate;
import java.util.Objects;

public class StudentActivityDetails {

    private final int bookID;
    private final String title;
    private final int studentID;
    private final String studentName;
    private final LocalDate dateOfIssue;
    private LocalDate dateOfReturn;

    public StudentActivityDetails(int bookIDColumn, String title, int studentID, String studentName, LocalDate dateOfIssue, LocalDate dateOfReturn) {
        this.bookID = bookIDColumn;
        this.title = title;
        this.studentID = studentID;
        this.studentName = studentName;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentActivityDetails that = (StudentActivityDetails) o;
        return bookID == that.bookID && studentID == that.studentID && title.equals(that.title) && studentName.equals(that.studentName) && dateOfIssue.equals(that.dateOfIssue) && dateOfReturn.equals(that.dateOfReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, title, studentID, studentName, dateOfIssue, dateOfReturn);
    }

    @Override
    public String toString() {
        return "StudentActivityDetails{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfReturn=" + dateOfReturn +
                '}';
    }
}
