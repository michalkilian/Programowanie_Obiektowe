package Email;

import javax.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {
        EmailMessage msg = new EmailMessage.Builder().setFrom("name@gmail.com")
                .setTo("mail")
                .setContent("Temat")
                .setSubject("Temat")
                .setPasswd("password")
                .build();
        msg.send();
    }
}
