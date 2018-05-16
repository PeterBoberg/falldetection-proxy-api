package se.frost.falldetectionproxyapi.service.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import se.frost.falldetectionproxyapi.dto.request.AlarmRequest;
import se.frost.falldetectionproxyapi.entities.Contact;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.exceptions.ExceptionType;


@Service
public class MailServiceImpl implements MailService {
    private static final String SENDER = "falldetectionproxy@gmail.com";
    private static final String LINE_BREAK_DELIMITER = "\r\n";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    /**
     * Send an email alarm to a users registered contacts
     *
     * @param user The user that the alarm is regarding
     */
    @Override
    public void sendEmailAlarmForUser(User user, AlarmRequest alarmRequest) {
        user.getContacts().forEach(contact -> sendMail(user, contact, alarmRequest));

    }

    @Override
    public String getSender() {
        return SENDER;
    }

    private String buildMail(User user, Contact contact, AlarmRequest alarmRequest) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("contact", contact);
        context.setVariable("alarmRequest", alarmRequest);
        return templateEngine.process("mail", context);
    }

    private void sendMail(User user, Contact contact, AlarmRequest alarmRequest) {
        MimeMessagePreparator preparator = mimeMessage -> {
//            mimeMessage.setFrom(SENDER);
//            mimeMessage.setTo(contact.getEmail());
//            mimeMessage.setSubject("A fall has bee detected for " + user.getFullName());
//            mimeMessage.setText(text);

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(SENDER);
            messageHelper.setTo(contact.getEmail());
            messageHelper.setSubject("A fall has bee detected for " + user.getFullName());
            String text = buildMail(user, contact, alarmRequest);
            messageHelper.setText(text, true);
        };

        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            e.printStackTrace();
        }


//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(getMailSubject(user));
//        message.setFrom(SENDER);
//        message.setTo(contact.getEmail());
//        message.setText(getMailContent(user, contact, alarmRequest));
//        mailSender.send(message);
    }

//    private String getMailContent(User user, Contact receiverContact, AlarmRequest alarmRequest) {
//        return "Hello " + receiverContact.getFullname() +
//                LINE_BREAK_DELIMITER +
//                LINE_BREAK_DELIMITER +
//                "A test fall has been detected for " + user.getFullName() +
//                LINE_BREAK_DELIMITER +
//                LINE_BREAK_DELIMITER +
//                "Known location of" + user.getFullName() + " is " + alarmRequest.prettyString() +
//
//                "This is an automated test. If you have received this email and don't know what it is, you can simply ignore it.";
//    }

    private String getMailSubject(User user){
        return "Fall detected for " + user.getFirstName() + " " + user.getLastName();
    }
}
