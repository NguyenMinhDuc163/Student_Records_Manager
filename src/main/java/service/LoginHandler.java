package service;

import dao.GetDataBase;

public class LoginHandler {
    public boolean isValidLogin(String username, String password) {
//        return "a".equals(username) && "a".equals(password);
        GetDataBase getDataBase = new GetDataBase();
        return getDataBase.checkAccount(username, password);
    }
}
