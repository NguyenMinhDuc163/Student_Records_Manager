package app;
import dao.SubjectDAO;
import model.Subject;

import java.util.Locale;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        var subject = SubjectDAO.getInstance().selectByID("ELE1330");
        System.out.println(subject);
    }
}
