package dao;

import model.Classes;
import model.Course;
import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO implements DAOInterface<Course> {
    public static CourseDAO getInstance() {
        return new CourseDAO();
    }
    @Override
    public int insert(Course course) throws SQLException {
        String url = "INSERT INTO courses (courseID, courseName)VALUES(?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,course.getCourseID());
            stmt.setString(2,course.getCourseName());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Course course) {
        String url = "UPDATE courses SET courseName = ? WHERE courseID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Course course) {
        String url = "DELETE FROM courses WHERE courseID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, course.getCourseID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Course> selectAll() {
        String sql = "SELECT * FROM courses;";
        // try-with-resource
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Course x = new Course(rs.getString("courseID"), rs.getString("courseName"));
                courses.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public ArrayList<Course> selectByCondition(String condition) {
        String sql = "SELECT * FROM courses WHERE ?;";
        // try-with-resource
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Course x = new Course(rs.getString("courseID"), rs.getString("courseName"));
                courses.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public Course selectByID(String studentID) {
        String sql = "SELECT * FROM courses WHERE courseID = ?";
        Course course = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,studentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                course = new Course(rs.getString("courseID"), rs.getString("courseName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }
}
