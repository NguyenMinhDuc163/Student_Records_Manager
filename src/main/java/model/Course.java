package model;

public class Course {
    private String courseID;
    private String courseName;

    public Course(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course() {
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }


    @Override
    public String toString() {
        return String.join(" ", courseID, courseName);
    }
}
