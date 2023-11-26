package service;

import dao.GradeDAO;
import model.Grade;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ExportFileHandle {

    public static ExportFileHandle getInstance(){
        return new ExportFileHandle();
    }

    public boolean exportCsvFile(String directory, String studentID){
        ArrayList<Grade> grades = null;
        if(!studentID.startsWith("B") && !studentID.equals(".")) return false;
        if(studentID.equals(".")){
                    grades = GradeDAO.getInstance().selectAll();
        }else {
            grades = GradeDAO.getInstance().selectByID(studentID.toUpperCase());
        }
        // Tên file CSV
        String directoryFile = directory + "\\bangDiem.csv";
        System.out.println(directoryFile);
        try (BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryFile), "UTF-8"))) {
//             Ghi dữ liệu vào file CSV

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
