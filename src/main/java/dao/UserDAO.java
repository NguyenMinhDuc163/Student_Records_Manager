package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class UserDAO implements DAOInterface<User>{
    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public int insert(User user) {
        String url = "INSERT INTO users (userName, passWord, studentID)VALUES(?, ?, ?)";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassWord());
            stmt.setString(3,user.getStudentID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int update(User user) {
        String url = "UPDATE users SET passWord = ? WHERE studentID = ? and userName = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, user.getPassWord());
            stmt.setString(2, user.getStudentID());
            stmt.setString(3, user.getUserName());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int delete(User user) {
        String url = "DELETE FROM users WHERE studentID = ?";
        // try-with-resources
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(url)) {
            stmt.setString(1, user.getStudentID());
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được xóa là: " + row);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ArrayList<User> selectAll() {
        String sql = "SELECT * FROM users;";
        // try-with-resource
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User x = new User(rs.getString("userID"), rs.getString("userName"),
                        rs.getString( "passWord"), rs.getString("studentID"));
                users.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public ArrayList<User> selectByCondition(String condition) {
        String sql = "SELECT * FROM users WHERE ?;";
        // try-with-resource
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,condition);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User x = new User(rs.getString("userID"), rs.getString("userName"),
                        rs.getString( "passWord"), rs.getString("studentID"));
                users.add(x);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

//    @Override
    public User selectByID(String studentID) {
        String sql = "SELECT * FROM users WHERE studentID = ?";
        User user = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,studentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user = new User(rs.getString("userID"), rs.getString("userName"),
                        rs.getString( "passWord"), rs.getString("studentID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User selectByUserNamePassWord(String userName, String passWord) {
        String sql = "SELECT * FROM users WHERE userName = ? AND passWord = ?";
        User user = null;
        // try-with-resource
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,userName);
            stmt.setString(2,passWord);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user = new User(rs.getString("userID"), rs.getString("userName"),
                        rs.getString( "passWord"), rs.getString("studentID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
