package se.frost.falldetectionproxyapi.service.alarm;

import se.frost.falldetectionproxyapi.dto.request.AlarmRequest;
import se.frost.falldetectionproxyapi.entities.User;

public interface MailService {
    /**
     * Send an email alarm to a users registered contacts
     *
     * @param user The user that the alarm is regarding
     */
    void sendEmailAlarmForUser(User user, AlarmRequest alarmRequest);

    /**
     * Get the sender email address
     *
     * @return The sender of the emails
     */
    String getSender();
}
