package dao;

import java.sql.*;

public class GetDataBase {
    private static String url = "jdbc:mysql://localhost:3306/StudentRecordsManager?autoReconnect=true&useSSL=false";
    private static String userName = "root";
    private static String passWord = "tumotden8";
    public static Connection con = null;
    public ResultSet resultSet;

    public GetDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passWord);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setDBlogin(String userName, String passWord, String studentID) throws SQLException {
        con.setAutoCommit(false); // tat che do tu dong ghi
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO user (userName, passWord, studentID)VALUES(?, ?, ? );");
            stmt.setString(1,userName);
            stmt.setString(2,passWord);
            stmt.setString(3,studentID);
            int row = stmt.executeUpdate();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
            con.rollback();
        }
        finally {
            con.close();
        }
    }
    public void deleteUser(String studentID) throws SQLException {
        con.setAutoCommit(false); // tat che do tu dong ghi
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM user WHERE studentID = ?");
            stmt.setString(1, studentID);
            int row = stmt.executeUpdate();
            con.commit();
            System.out.println("Số dữ liệu được cập nhật là: " + row);
        }
        catch (Exception e){
            e.printStackTrace();
            con.rollback();
        }
    }
    public void getDBLogin(String userName, String passWord) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM sinhvien");
            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " +  resultSet.getString("HoLot") + " " + resultSet.getString("Ten"));
            }
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            con.close();
        }
    }

    public void getListStudent() throws SQLException {

        try {
            Statement stmt = con.createStatement();
            String sqlQuery = "SELECT DISTINCT " +
                    "a.MaSV, a.HoLot, a.Ten, c.MaLop, d.TenNganh, c.TenMonHoc, " +
                    "b.KT, b.TH, b.CC, b.THI, b.KTHP, b.HeChu " +
                    "FROM sinhvien as a " +
                    "JOIN diemhp as b on a.MaSV = b.MaSV " +
                    "JOIN hocphan as c on b.MaMHKey = c.MaMHKey " +
                    "JOIN nganh as d on c.MaNganhKey = d.MaNganhKey";

            resultSet = stmt.executeQuery(sqlQuery);
//            stmt.close(); // dong no la khong goi duoc resul o ben ngoai
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getDBstudent(String studentID) throws SQLException{
        try {
            String sqlQuery = "SELECT DISTINCT " +
                    "a.MaSV, a.HoLot, a.Ten, c.MaLop, d.TenNganh, c.TenMonHoc, " +
                    "b.KT, b.TH, b.CC, b.THI, b.KTHP, b.HeChu " +
                    "FROM sinhvien as a " +
                    "JOIN diemhp as b on a.MaSV = b.MaSV " +
                    "JOIN hocphan as c on b.MaMHKey = c.MaMHKey " +
                    "JOIN nganh as d on c.MaNganhKey = d.MaNganhKey " +
                    "WHERE a.MaSV = ?";

            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1,studentID);
            resultSet = stmt.executeQuery(); // khong phai truyen sql vao day vi da truyen o tren roi
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDBUser(){
        try {
            String sqlQuery = "SELECT * FROM user";
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery(sqlQuery);

            int columnWidth = 15;
            System.out.format("%-" + columnWidth + "s | %-" + columnWidth + "s | %-" + columnWidth + "s | %-" + columnWidth + "s%n", "userID", "userName", "passWord", "studentID");
            System.out.format("%s%n", "-".repeat(4 * columnWidth + 3));

            while(resultSet.next()){
                System.out.format("%-" + columnWidth + "s | %-" + columnWidth + "s | %-" + columnWidth + "s | %-" + columnWidth + "s%n",
                        resultSet.getString("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("passWord"),
                        resultSet.getString("studentID"));
            }
            stmt.close();
            con.close();
            resultSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean checkAccount(String userName, String passWord){
        String sqlQuery = "SELECT * FROM user WHERE userName = ? AND passWord = ?";
        try (PreparedStatement stmt = con.prepareStatement(sqlQuery)) {
            stmt.setString(1, userName);
            stmt.setString(2, passWord);
            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Tồn tại trong cơ sở dữ liệu nếu resultSet.next() trả về true
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xảy ra lỗi trong quá trình truy vấn
        }
    }

    // test
    @Override
    public String toString() {
        boolean flag = false;
        StringBuilder resultBuilder = new StringBuilder();
        try (ResultSet rs = resultSet; Connection conn = con) { // de do phai try catch nhieu
            while (rs.next()) {
                flag = true; // kiem tra co thong tin sinh vien khong
                resultBuilder.append(String.format("| %-10s | %-20s | %-10s | %-10s | %-20s | %-20s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |%n",
                        rs.getString("MaSV"),
                        rs.getString("HoLot"),
                        rs.getString("Ten"),
                        rs.getString("MaLop"),
                        rs.getString("TenNganh"),
                        rs.getString("TenMonHoc"),
                        rs.getString("KT"),
                        rs.getString("TH"),
                        rs.getString("CC"),
                        rs.getString("THI"),
                        rs.getString("KTHP"),
                        rs.getString("HeChu")));
            }
            if(!flag) return "Không có thông tin của sinh viên này";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultBuilder.toString();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            GetDataBase db = new GetDataBase();
            db.getDBUser();
    }
}
