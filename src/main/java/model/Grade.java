package model;

public class Grade {
    private Student student = new Student();
    private Course course = new Course();
    private String Group;
    private String assignmentScore, examScore,practicalScore,attendanceScore, finalExamScore,componentScore;
    private String letterGrade;

    public Grade(Student student, Course course, String group, String assignmentScore, String examScore,
                 String practicalScore, String attendanceScore, String finalExamScore, String componentScore,
                 String letterGrade) {
        this.student = student;
        this.course = course;
        this.Group = group;
        this.assignmentScore = assignmentScore;
        this.examScore = examScore;
        this.practicalScore = practicalScore;
        this.attendanceScore = attendanceScore;
        this.finalExamScore = finalExamScore;
        this.componentScore = componentScore;
        this.letterGrade = letterGrade;
    }


    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getGroup() {
        return Group;
    }

    public String getAssignmentScore() {
        return assignmentScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public String getPracticalScore() {
        return practicalScore;
    }

    public String getAttendanceScore() {
        return attendanceScore;
    }

    public String getFinalExamScore() {
        return finalExamScore;
    }

    public String getComponentScore() {
        return componentScore;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-15s | %-10s | %-17s | %-10s | %-15s | %-17s | %-16s | %-15s | %-12s |",
                "Student Name", "Course Name", "Group", "Assignment Score", "Exam Score", "Practical Score", "Attendance Score", "Final Exam Score", "Component Score", "Letter Grade") +
                String.format("\n| %-15s | %-15s | %-10s | %-17s | %-10s | %-15s | %-17s | %-16s | %-15s | %-12s |",
                        student.getLastName(), course.getCourseName(), Group, assignmentScore, examScore, practicalScore, attendanceScore, finalExamScore, componentScore, letterGrade);
    }
}
