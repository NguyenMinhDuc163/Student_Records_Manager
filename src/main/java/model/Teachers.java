package model;

public class Teachers {
    private String teacherID;
    private String teacherName;
    private String faculty;
    private String department;

    public Teachers(String teacherID, String teacherName, String faculty, String department) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.faculty = faculty;
        this.department = department;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.join(" ", teacherID, teacherName, faculty, department);
    }
}
