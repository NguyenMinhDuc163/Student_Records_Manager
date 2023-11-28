package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static String url = "jdbc:mysql://localhost:3306/StudentRecordsManager?autoReconnect=true&useSSL=false";
    private static String userName = "root";
    private static String passWord = "tumotden8";
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            System.out.println("Lỗi: Chưa kết nối cơ sở dữ liệu!!!");
            e.printStackTrace();
        }
        return con;
    }
}
