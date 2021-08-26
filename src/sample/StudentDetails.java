package sample;

import java.util.Objects;

public class StudentDetails {

    private final int studentID;
    private final String name;
    private final String course;
    private final String semester;

    public StudentDetails(int studentID,String name,String course,String semester) {
        this.studentID = studentID;
        this.name = name;
        this.course = course;
        this.semester = semester;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDetails that = (StudentDetails) o;
        return studentID == that.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID);
    }

    @Override
    public String toString() {
        return studentID + "";
    }
}