package service;

import dao.GradeDAO;
import model.Grade;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ExportFileHandle {

    public static ExportFileHandle getInstance(){
        return new ExportFileHandle();
    }

    public boolean exportCsvFile(String directory, String studentID){
        ArrayList<Grade> grades = null;
        if(!studentID.matches("^B21DCCN\\d{3}$") && !studentID.equals(".")) return false;
        if(studentID.equals(".")){
                    grades = GradeDAO.getInstance().selectAll();
        }else {
            grades = GradeDAO.getInstance().selectByIDAllGrade(studentID.toUpperCase());
        }
        // Tên file CSV
        String directoryFile = directory + "\\BangDiemSinhVien-" + studentID + ".csv";
        System.out.println(directoryFile);
        try (BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryFile), StandardCharsets.UTF_8))) {
//             Ghi dữ liệu vào file CSV
            csvWriter.append("StudentId"); csvWriter.append(",");
            csvWriter.append("FullName"); csvWriter.append(",");
            csvWriter.append("AssignmentScore"); csvWriter.append(",");
            csvWriter.append("ExamScore"); csvWriter.append(",");
            csvWriter.append("PracticalScore"); csvWriter.append(",");
            csvWriter.append("FinalExamScore"); csvWriter.append(",");
            csvWriter.append("ComponentScore"); csvWriter.append(",");
            csvWriter.append("LetterGrade"); csvWriter.append(",");
            csvWriter.append("\n");

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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
