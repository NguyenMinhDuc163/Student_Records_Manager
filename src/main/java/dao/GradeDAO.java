package dao;

import model.Course;
import model.Grade;
import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeDAO implements DAOInterface<Grade> {
    public static GradeDAO getInstance() {
        return new GradeDAO();
    }
    @Override
    public int insert(Grade grade) throws SQLException {
        String url = "INSERT INTO grade (studentID, courseID, Group, assignmentScore, examScore, practicalScore, " +
                "attendanceScore, finalExamScore, componentScore, letterGrade)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,grade.getStudent().getStudentID());
            stmt.setString(2,grade.getCourse().getCourseID());
            stmt.setString(3,grade.getGroup());
            stmt.setString(4,grade.getAssignmentScore());
            stmt.setString(5,grade.getExamScore());
            stmt.setString(6,grade.getPracticalScore());
            stmt.setString(7,grade.getAttendanceScore());
            stmt.setString(8,grade.getFinalExamScore());
            stmt.setString(9,grade.getComponentScore());
            stmt.setString(10,grade.getLetterGrade());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Grade grade) {
        String url = "UPDATE grade SET Group = ?, assignmentScore = ? , examScore = ?, practicalScore = ?," +
                "attendanceScore = ?, finalExamScore = ?, componentScore = ?, letterGrade = ? " +
                "WHERE studentID = ? AND courseID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,grade.getGroup());
            stmt.setString(2,grade.getAssignmentScore());
            stmt.setString(3,grade.getExamScore());
            stmt.setString(4,grade.getPracticalScore());
            stmt.setString(5,grade.getAttendanceScore());
            stmt.setString(6,grade.getFinalExamScore());
            stmt.setString(7,grade.getComponentScore());
            stmt.setString(8,grade.getLetterGrade());
            stmt.setString(9,grade.getStudent().getStudentID());
            stmt.setString(10,grade.getCourse().getCourseID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Grade grade) {
        String url = "DELETE FROM grade WHERE studentID = ? AND courseID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, grade.getStudent().getStudentID());
            stmt.setString(2, grade.getCourse().getCourseID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Grade> selectAll() {
        String sql = "SELECT * FROM grade;";
        // try-with-resource
        ArrayList<Grade> grades = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                Course course = CourseDAO.getInstance().selectByID(rs.getString("courseID"));
                String group = rs.getString("Group");
                String assignmentScore = rs.getString("assignmentScore");
                String examScore = rs.getString("examScore");
                String practicalScore = rs.getString("practicalScore");
                String attendanceScore = rs.getString("attendanceScore");
                String finalExamScore = rs.getString("finalExamScore");
                String componentScore = rs.getString("componentScore");
                String letterGrade = rs.getString("letterGrade");

                Grade result = new Grade(student, course, group, assignmentScore, examScore, practicalScore,
                        attendanceScore, finalExamScore, componentScore, letterGrade);
                grades.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grades;
    }

    @Override
    public ArrayList<Grade> selectByCondition(String condition) {
        String sql = "SELECT * FROM grade WHERE ?;";
        // try-with-resource
        ArrayList<Grade> grades = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                Course course = CourseDAO.getInstance().selectByID(rs.getString("courseID"));
                String group = rs.getString("Group");
                String assignmentScore = rs.getString("assignmentScore");
                String examScore = rs.getString("examScore");
                String practicalScore = rs.getString("practicalScore");
                String attendanceScore = rs.getString("attendanceScore");
                String finalExamScore = rs.getString("finalExamScore");
                String componentScore = rs.getString("componentScore");
                String letterGrade = rs.getString("letterGrade");

                Grade result = new Grade(student, course, group, assignmentScore, examScore, practicalScore,
                        attendanceScore, finalExamScore, componentScore, letterGrade);
                grades.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grades;
    }

    @Override
    public Grade selectByID(String studentID) {
        String sql = "SELECT * FROM grade WHERE studentID = ?";
        Grade grade = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,studentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                Course course = CourseDAO.getInstance().selectByID(rs.getString("courseID"));
                String group = rs.getString("Group");
                String assignmentScore = rs.getString("assignmentScore");
                String examScore = rs.getString("examScore");
                String practicalScore = rs.getString("practicalScore");
                String attendanceScore = rs.getString("attendanceScore");
                String finalExamScore = rs.getString("finalExamScore");
                String componentScore = rs.getString("componentScore");
                String letterGrade = rs.getString("letterGrade");

                grade = new Grade(student, course, group, assignmentScore, examScore, practicalScore, attendanceScore,
                        finalExamScore, componentScore, letterGrade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grade;
    }
}
