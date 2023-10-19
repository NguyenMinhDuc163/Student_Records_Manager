package dao;

import model.Classes;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassesDAO implements DAOInterface<Classes> {
    public static ClassesDAO getInstance() {
        return new ClassesDAO();
    }
    @Override
    public int insert(Classes classes) {
        String url = "INSERT INTO classes (studentID, classID)VALUES(?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,classes.getStudent().getStudentID());
            stmt.setString(2,classes.getClassID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Classes classes) {
        return 0;
    }

    @Override
    public int delete(Classes classes) {
        String url = "DELETE FROM classes WHERE studentID = ? AND classID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, classes.getStudent().getStudentID());
            stmt.setString(1, classes.getClassID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Classes> selectAll() {
        String sql = "SELECT * FROM classes;";
        // try-with-resource
        ArrayList<Classes> classes = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String classID = rs.getString("classID");
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                Classes x = new Classes(classID, student);
                classes.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

    @Override
    public ArrayList<Classes> selectByCondition(String condition) {
        String sql = "SELECT * FROM classes WHERE ?;";
        // try-with-resource
        ArrayList<Classes> classes = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String classID = rs.getString("classID");
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                Classes x = new Classes(classID, student);
                classes.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

//    @Override
    public Classes selectByID(String studentID) {
        String sql = "SELECT * FROM classes WHERE studentID = ?";
        Classes classes = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,studentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String classID = rs.getString("classID");
                Student student = StudentDAO.getInstance().selectByID(rs.getString("studentID"));
                classes = new Classes(classID, student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }
}
