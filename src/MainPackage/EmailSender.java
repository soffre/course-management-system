package MainPackage;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.swing.*;
import java.util.ArrayList;

public class EmailSender {
    private ArrayList<String> recipientList;
    public static void sendEmail(ArrayList<String> recipientList, String emailAddress, String emailPassword,String Text) {
        final String username = emailAddress; // Your Gmail email address
        final String password = emailPassword; // Your Gmail password or app password

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            for (String recipient : recipientList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            message.setSubject("Notification From Instructor");
            message.setText(Text);

            Transport.send(message);
            //JOptionPane.showMessageDialog(null,"Email send successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

