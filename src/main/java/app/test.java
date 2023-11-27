package app;

class test{
    public static void main(String[] args) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String email = "ducnm.b21cn249@stu.ptit.deu.vn";
        System.out.println(email.matches(EMAIL_REGEX));
    }
}