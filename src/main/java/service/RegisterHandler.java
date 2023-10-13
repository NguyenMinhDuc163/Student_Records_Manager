package service;

public class RegisterHandler {
    public boolean isRegister(String name, String username, String password, String confirm) {
        return password.equals(confirm) && !password.isEmpty() && !name.isEmpty() && !username.isEmpty() ;
    }
}
