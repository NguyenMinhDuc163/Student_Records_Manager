package service;

import dao.*;
import model.*;

public class UpdateDataHandle {
    public static UpdateDataHandle getInstance(){
        return new UpdateDataHandle();
    }

    public void createProfile(String studentID, String firstName, String lastName, String courseID,String courseName, String classRoom,
    String cc, String thi, String bt, String kt,String TH, String TBHP, String heChu) {
        Student student = new Student(studentID, firstName, lastName);
        Classes classes = new Classes(classRoom, student);
        Course course = new Course(courseID, courseName);
        Grade grade = new Grade(student, course, "5", bt, kt, TH, cc, thi, TBHP, heChu);
        try {
            Student student1 = StudentDAO.getInstance().selectByID(studentID);
            Classes classes1 = ClassesDAO.getInstance().selectByID(classRoom);
            Course course1 = CourseDAO.getInstance().selectByID(courseID);
            if(student1 == null){
                System.out.println(1);
                StudentDAO.getInstance().insert(student);
            }
            if(classes1 == null && student1 == null){
                System.out.println(2);
                ClassesDAO.getInstance().insert(classes);
            }

            if(course1 == null){
                System.out.println(3);
                CourseDAO.getInstance().insert(course);
            }
                GradeDAO.getInstance().insert(grade);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void updateGrade(String studentID, String courseID, String cc, String thi, String bt, String kt,
                            String TH, String TBHP, String heChu){
        Student student = StudentDAO.getInstance().selectByID(studentID);
        Course course = CourseDAO.getInstance().selectByID(courseID);
        Grade grade = new Grade(student, course, "5", bt, kt, TH, cc, thi, TBHP, heChu);
        GradeDAO.getInstance().update(grade);
    }
    public void deleteData(String studentID, String courseID){
        GradeDAO.getInstance().delete(studentID, courseID);
        ClassesDAO.getInstance().deleteByStudentID(studentID);
    }
}
