package Test;

import dao.*;
import model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Grade> grades = GradeDAO.getInstance().selectAll();
        grades.forEach(System.out::println);
    }
}
