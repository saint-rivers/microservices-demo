package com.example.mail.app;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServerImpl {

    private static class EnvConfig {
        private final Dotenv dotenv;

        EnvConfig() {
            dotenv = Dotenv.configure().load();
        }
        public String getEmail() {
            return dotenv.get("EMAIL");
        }
        public String getPassword() {
            return dotenv.get("PASSWORD");
        }
    }

    public void sendMail(String recipient, String token) throws Exception {

        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");


        // set email credentials from env variables
        EnvConfig envConfig = new EnvConfig();
        String myAccountEmail = envConfig.getEmail();
        String password = envConfig.getPassword();

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recipient, token);

        //Send mail
        assert message != null;
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Here the token for you to reset your password.");

            // todo: write a proper email
            String content = "<html>\n" +

                    "<body style=\"font-family:verdana\">\n" +
                    "\n" +
                    "<p>Greeting from <b> Sahakka </b></p>\n"

                    + "<p>You are seeing this because you have requested to reset your password.</p>"
                    + "<p>What to do from now on is used provided token to request access to set your password with this email : </p>"
                    + "<p>Here is our token: <b> " + token + "</b> </p>"
                    + "<br>"
                    + "<p>Please kindly ignore this email if you already remember your password, "
                    + "or you have not made the request. Thank you and have a wonderful day.</p>"
                    + "<p>Best Regard From Sahakka </p> " +
                    "</body>\n" +
                    "</html>";

            message.setContent(content, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println("Prepare message exception : " + ex.getMessage());
        }
        return null;
    }
}
