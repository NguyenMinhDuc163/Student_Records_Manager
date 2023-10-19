package dao;

import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO implements DAOInterface<Student> {
    public static StudentDAO getInstance() {
        return new StudentDAO();
    }
    @Override
    public int insert(Student student) throws SQLException {
        String url = "INSERT INTO students (studentID, firstName, lastName)VALUES(?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,student.getStudentID());
            stmt.setString(2,student.getFirstName());
            stmt.setString(3,student.getLastName());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Student student) {
        String url = "UPDATE students SET firstName = ?, lastName = ? WHERE studentID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getStudentID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Student student) {
        String url = "DELETE FROM students WHERE studentID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, student.getStudentID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Student> selectAll() {
        String sql = "SELECT * FROM students;";
        // try-with-resource
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Student x = new Student(rs.getString("studentID"), rs.getString("firstName"),
                        rs.getString( "lastName"));
                students.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public ArrayList<Student> selectByCondition(String condition) {
        String sql = "SELECT * FROM students WHERE ?;";
        // try-with-resource
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Student x = new Student(rs.getString("studentID"), rs.getString("firstName"),
                        rs.getString( "lastName"));
                students.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

//    @Override
    public Student selectByID(String studentID) {
        String sql = "SELECT * FROM students WHERE studentID = ?";
        Student student = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,studentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                student = new Student(rs.getString("studentID"), rs.getString("firstName"),
                        rs.getString( "lastName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
