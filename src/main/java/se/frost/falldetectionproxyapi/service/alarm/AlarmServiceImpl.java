package se.frost.falldetectionproxyapi.service.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.Contact;
import se.frost.falldetectionproxyapi.entities.User;

@Service
public class AlarmServiceImpl implements AlarmService {
    private static final String SENDER = "falldetectionproxy@gmail.com";
    private static final String LINE_BREAK_DELIMITER = "\r\n";

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send an email alarm to a users registered contacts
     *
     * @param user The user that the alarm is regarding
     */
    @Override
    public void sendEmailAlarmForUser(User user) {
        user.getContacts().forEach(contact -> sendMail(user, contact));
    }

    @Override
    public String getSender() {
        return SENDER;
    }

    private void sendMail(User user, Contact receiverContact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(getMailSubject(user));
        message.setFrom(SENDER);
        message.setTo(receiverContact.getEmail());
        message.setText(getMailContent(user, receiverContact));
        mailSender.send(message);
    }

    private String getMailContent(User user, Contact receiverContact) {
        return "Hello " +
                receiverContact.getFirstName() + " " +
                receiverContact.getLastName() + "," + LINE_BREAK_DELIMITER +
                LINE_BREAK_DELIMITER +
                "A test fall has been detected for " + user.getFirstName() + " " + user.getLastName() + LINE_BREAK_DELIMITER
                + LINE_BREAK_DELIMITER +
                "This is an automated test. If you have received this email and don't know what it is, you can simply ignore it.";
    }

    private String getMailSubject(User user){
        return "Fall detected for " + user.getFirstName() + " " + user.getLastName();
    }
}
