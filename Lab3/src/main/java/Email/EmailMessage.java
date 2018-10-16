package Email;


import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;


public class EmailMessage {
    private String from;
    private String passwd;
    private LinkedList<String> to = new LinkedList<String>();
    private String subject;
    private String content;
    private String mimeType;  // optional
    private LinkedList<String> cc = new LinkedList<String>(); //optional
    private LinkedList<String> bcc = new LinkedList<String>(); // optional


    protected EmailMessage() {
    }

    protected EmailMessage(Builder builder) {
        if (builder == null) {
            return;
        }
        this.from = builder.from;
        this.to = builder.to;
        this.passwd = builder.passwd;
        this.subject = builder.subject;
        this.content = builder.content;
        this.mimeType = builder.mimeType;  // optional
        this.cc = builder.cc; //optional
        this.bcc = builder.bcc; // optional

    }


    public void send() throws MessagingException {


        Properties props = new Properties();

        props.put("mail.smtp.host", "true");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, passwd);

            }

        });

        try {


            MimeMessage msg = new MimeMessage(session);



            for (int i = 0; i < this.to.size(); ++i) {
                msg.addRecipients(Message.RecipientType.TO, this.to.get(i));
            }
            for (int i = 0; i < this.cc.size(); ++i) {
                msg.addRecipients(Message.RecipientType.CC, this.cc.get(i));
            }
            for (int i = 0; i < this.bcc.size(); ++i) {
                msg.addRecipients(Message.RecipientType.BCC, this.bcc.get(i));
            }


            msg.setSubject(this.subject);

            msg.setText(this.content);

            Transport.send(msg);

            System.out.println("Mail has been sent successfully");

        } catch (MessagingException mex) {

            System.out.println("Unable to send an email" + mex);

        }

    }


    public static class Builder {
        private String from;
        private String passwd;
        private LinkedList<String> to = new LinkedList<String>();
        private String subject;
        public String content;
        private String mimeType;  // optional
        private LinkedList<String> cc = new LinkedList<String>(); //optional
        private LinkedList<String> bcc = new LinkedList<String>(); // optional

        public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public Builder() {
        }

        public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
        }


        public EmailMessage build() {
            return new EmailMessage(this);
        }

        public Builder setFrom(String from) {
            if (validate(from)) {
                this.from = from;
                return this;
            }
            return null;
        }

        public Builder setPasswd(String passwd) {
            this.passwd = passwd;
            return this;
        }

        public Builder setTo(String to) {
            if (validate(to)) {
                this.to.add(to);
            }
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder setCc(String cc) {
            if (validate(cc)) {
                this.cc.add(cc);
            }
            return this;
        }

        public Builder setBcc(String bcc) {
            if (validate(bcc)) {
                this.bcc.add(bcc);
            }
            return this;
        }

    }
}

