package dao;

import model.Subject;
import model.Teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDao implements DAOInterface<Teachers>{
    public static TeacherDao getInstance() {
        return new TeacherDao();
    }
    @Override
    public int insert(Teachers teachers) throws SQLException {
        String url = "INSERT INTO teachers (teacherID, teacherName, faculty, department)VALUES(?, ?, ?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,teachers.getTeacherID());
            stmt.setString(2,teachers.getTeacherName());
            stmt.setString(3,teachers.getFaculty());
            stmt.setString(4,teachers.getDepartment());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Teachers teachers) {
        String url = "UPDATE teachers SET teacherName = ?, faculty = ? ,department = ? WHERE teachersID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, teachers.getTeacherName());
            stmt.setString(2, teachers.getFaculty());
            stmt.setString(3, teachers.getDepartment());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Teachers> selectAll() {
        String sql = "SELECT * FROM teachers;";
        // try-with-resource
        ArrayList<Teachers> teachers = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Teachers x = new Teachers(rs.getString("teacherID"), rs.getString("teacherName"),
                        rs.getString( "faculty"), rs.getString("department"));
                teachers.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    @Override
    public ArrayList<Teachers> selectByCondition(String condition) {
        String sql = "SELECT * FROM teachers WHERE ?;";
        // try-with-resource
        ArrayList<Teachers> teachers = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Teachers x = new Teachers(rs.getString("teacherID"), rs.getString("teacherName"),
                        rs.getString( "faculty"), rs.getString("department"));
                teachers.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    public Teachers selectByID(String teacherID) {
        String sql = "SELECT * FROM teachers WHERE teacherID = ?";
        Teachers teachers = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,teacherID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                teachers =  new Teachers(rs.getString("teacherID"), rs.getString("teacherName"),
                        rs.getString( "faculty"), rs.getString("department"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    public int delete(Teachers teachers) {
        String url = "DELETE FROM teachers WHERE teacherID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, teachers.getTeacherID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
