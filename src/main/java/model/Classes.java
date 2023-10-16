package model;

public class Classes {
    private String classID;
    private Student student = new Student();

    public Classes(String classID, Student student) {
        this.classID = classID;
        this.student = student;
    }

    public String getClassID() {
        return classID;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classID='" + classID + '\'' +
                ", student=" + student +
                '}';
    }
}
