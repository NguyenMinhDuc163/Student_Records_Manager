package Test;

import dao.DAOInterface;
import dao.UserDAO;
import model.User;

import java.util.List;

public class TEST {
    public static void main(String[] args) {
        User users = new User("SV07", "nguyenVanA", "sdsad","B21DCCN001");
        UserDAO.getInstance().delete(users);
        System.out.println(users);
    }
}
