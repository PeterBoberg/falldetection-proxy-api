//package se.frost.falldetectionproxyapi.service;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.junit4.SpringRunner;
//import se.frost.falldetectionproxyapi.dto.request.AlarmRequest;
//import se.frost.falldetectionproxyapi.entities.Contact;
//import se.frost.falldetectionproxyapi.entities.User;
//import se.frost.falldetectionproxyapi.service.alarm.MailService;
//import se.frost.falldetectionproxyapi.service.alarm.MailServiceImpl;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//public class MailServiceTest {
//
//    @TestConfiguration
//    static class MailServiceTestConfig {
//        @Bean
//        public MailServiceImpl alarmService() {
//            return new MailServiceImpl();
//        }
//    }
//
//    @Autowired
//    MailService mailService;
//
//    @MockBean
//    private JavaMailSender javaMailSender;
//
//    @Before
//    public void setup() {
//
//    }
//
//    @Test
//    public void testSendEmail() {
//        // Given
//        Map<String, Integer> sentEmails = new HashMap<>();
//        AtomicInteger numberOfTimesCalled = new AtomicInteger();
//        User user = new User("kallekula", "kallekula", "Kalle", "Kula", null);
//        Contact contact = new Contact("Bertil", "Svensson", "andreas.lagerstrom@gmail.com", "123123123");
//        Contact secondContact = new Contact("Ylva", "Bertilsson", "andreas.lagerstrom@gmail.com", "123123123");
//        user.setContacts(Arrays.asList(contact, secondContact));
//
//        Mockito.doAnswer(invocation -> {
//            SimpleMailMessage message = invocation.getArgument(0);
//            assertThat(message.getFrom()).isEqualTo(mailService.getSender());
//            Assert.assertTrue("Message did not contain user's full name.", message.getText().contains("Kalle Kula"));
//            Assert.assertTrue("Message did not contain receiver's full name.", message.getText().contains("Ylva Bertilsson") || message.getText().contains("Bertil Svensson"));
//
//            for (String receiverEmail : message.getTo()) {
//                if (sentEmails.containsKey(receiverEmail)) {
//                    sentEmails.put(receiverEmail, sentEmails.get(receiverEmail) + 1);
//                } else {
//                    sentEmails.put(receiverEmail, 1);
//                }
//            }
//
//            numberOfTimesCalled.getAndIncrement();
//            return null;
//        }).when(javaMailSender).send(any(SimpleMailMessage.class));
//
//        // When
//        try {
//            mailService.sendEmailAlarmForUser(user, new AlarmRequest(59.11, 11.45));
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//
//        // Then
//        assertThat(numberOfTimesCalled.get()).isEqualTo(user.getContacts().size());
//        for (Contact c : user.getContacts()){
//            assertThat(sentEmails.containsKey(c.getEmail()));
//            Integer numberOfSentEmailsToThisContact = sentEmails.get(c.getEmail());
//
//            Assert.assertTrue("Wrong number of sent emails to " + c.getEmail(), numberOfSentEmailsToThisContact == 2);
//        }
//    }
//}
