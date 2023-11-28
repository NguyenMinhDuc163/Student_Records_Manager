package util;

import dao.StudentDAO;
import dao.TeacherDao;
import model.Student;
import model.Teachers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class EmailHandler {
    final static String from = "hotrohoctap163@gmail.com";
    final static String password = "rbaq rlfc wemx bktu";


    public static boolean sendMail(String to, String newPassWord, String personIDcode ){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };


        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Mật khẩu mới của bạn ");
            msg.setSentDate(new Date());
            String Name;
            if(personIDcode .matches("^B21DCCN\\d{3}$")){
                Student student = StudentDAO.getInstance().selectByID(personIDcode);
                Name = student.getFirstName() + " " + student.getLastName();
            }else {
                Teachers teachers = TeacherDao.getInstance().selectByID(personIDcode);
                Name = teachers.getTeacherName();
            }


            String updatedPassword = "<strong><em>" + newPassWord + "</em></strong>";
            String studentName = "<strong><em>" + Name + "</em></strong>";

            String emailContent = "Chào " + studentName + "," + "<br><br>" +
                    "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn. Dưới đây là thông tin mật khẩu mới để bạn đăng nhập vào tài khoản của mình:" + "<br><br>" +
                    "Mật khẩu mới: " + updatedPassword + "<br><br>" +
                    "Chúng tôi khuyến khích bạn đăng nhập bằng mật khẩu mới này và sau đó đổi lại mật khẩu thành một mật khẩu mà bạn dễ nhớ nhưng cũng đảm bảo an toàn." + "<br><br>" +
                    "Nếu bạn không thực hiện yêu cầu đặt lại mật khẩu này, vui lòng liên hệ với chúng tôi ngay lập tức để chúng tôi có thể hỗ trợ bạn." + "<br><br>" +
                    "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi." + "<br><br>" +
                    "Trân trọng," + "<br>" +
                    "Nguyễn Minh Đức" + "<br>" +
                    "Nhóm 3" + "<br>" +
                    "hotrohoctap163@gmail.com";

            msg.setContent(emailContent, "text/html; charset=utf-8");
            Transport.send(msg);
            System.out.println("Email sent successfully");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Email sent false");
            return false;
        }
    }

}
