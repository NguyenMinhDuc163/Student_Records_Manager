package dao;

import model.Student;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAO implements DAOInterface<Subject> {
    public static SubjectDAO getInstance() {
        return new SubjectDAO();
    }
    @Override
    public int insert(Subject subject) throws SQLException {
        String url = "INSERT INTO classes (subjectID, subjectName, credit)VALUES(?, ?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,subject.getSubjectID());
            stmt.setString(2,subject.getSubjectName());
            stmt.setString(3,subject.getCredit());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Subject subject) {
        String url = "UPDATE students SET subjectName = ?, credit = ? WHERE subjectID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getCredit());
            stmt.setString(3, subject.getSubjectID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
    public int delete(Subject subject) {
        String url = "DELETE FROM subject WHERE subjectID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, subject.getSubjectID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Subject> selectAll() {
        String sql = "SELECT * FROM subject;";
        // try-with-resource
        ArrayList<Subject> subjects = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Subject x = new Subject(rs.getString("subjectID"), rs.getString("subjectName"),
                        rs.getString( "credit"));
                subjects.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    @Override
    public ArrayList<Subject> selectByCondition(String condition) {
        String sql = "SELECT * FROM subject WHERE ?;";
        // try-with-resource
        ArrayList<Subject> subjects = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Subject x = new Subject(rs.getString("subjectID"), rs.getString("subjectName"),
                        rs.getString( "credit"));
                subjects.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    public Subject selectByID(String subjectID) {
        String sql = "SELECT * FROM subject WHERE subjectName = ?";
        Subject subject = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,subjectID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                subject = new Subject(rs.getString("subjectID"), rs.getString("subjectName"),
                        rs.getString( "credit"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subject;
    }
}
