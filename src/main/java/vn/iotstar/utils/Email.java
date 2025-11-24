package vn.iotstar.utils;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.iotstar.entity.User;

public class Email {

    // Email và Mật khẩu ứng dụng (App Password) của bạn
    private final String FROM_EMAIL = "nguyenthetan60484@gmail.com"; 
    private final String PASSWORD = "tsbarqmawzsqemik"; // Mật khẩu ứng dụng (16 ký tự)

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user, String otp) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            };

            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(FROM_EMAIL, "Web manager"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail(), false));
            msg.setSubject("Mã xác thực OTP - Quên mật khẩu", "UTF-8");
            
            String content = "Xin chào " + user.getFullname() + ",\n\n"
                    + "Mã OTP của bạn là: <b>" + otp + "</b>\n\n"
                    + "Mã có hiệu lực trong 5 phút.";
            msg.setContent(content, "text/html; charset=UTF-8");

            Transport.send(msg);
            System.out.println("Gửi email thành công!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}