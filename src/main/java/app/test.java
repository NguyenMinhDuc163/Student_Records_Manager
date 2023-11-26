package app;
import dao.ClassesDAO;
import dao.GradeDAO;
import dao.StudentDAO;
import model.Classes;
import model.Grade;
import model.Student;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
class test{
    public static void main(String[] args) {
//        ArrayList<Grade> grades = GradeDAO.getInstance().selectAll();
        ArrayList<Grade> grades = GradeDAO.getInstance().selectByID("B21DCCN001");

        // Tên file CSV
        String csvFileName = "example.csv";
        try (BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFileName), "UTF-8"))) {
//             Ghi dữ liệu vào file CSV
            int  i = 0;
            for(Grade x : grades){
                csvWriter.append(x.getStudent().getStudentID()); csvWriter.append(",");
                csvWriter.append(x.getStudent().getFirstName() + " " + x.getStudent().getLastName()); csvWriter.append(",");
                csvWriter.append(x.getAssignmentScore()); csvWriter.append(",");
                csvWriter.append(x.getExamScore()); csvWriter.append(",");
                csvWriter.append(x.getPracticalScore()); csvWriter.append(",");
                csvWriter.append(x.getFinalExamScore()); csvWriter.append(",");
                csvWriter.append(x.getComponentScore()); csvWriter.append(",");
                csvWriter.append(x.getLetterGrade());
                csvWriter.append("\n");
            }

            System.out.println("File CSV đã được tạo thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
    // Phương thức để xử lý trường hợp các ký tự đặc biệt trong dữ liệu CSV
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data;
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = "\"" + data.replaceAll("\"", "\"\"") + "\"";
        }
        return escapedData;
    }
}