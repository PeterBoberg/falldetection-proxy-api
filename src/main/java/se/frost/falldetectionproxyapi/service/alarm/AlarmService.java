package se.frost.falldetectionproxyapi.service.alarm;

import se.frost.falldetectionproxyapi.entities.User;

public interface AlarmService {
    /**
     * Send an email alarm to a users registered contacts
     *
     * @param user The user that the alarm is regarding
     */
    void sendEmailAlarmForUser(User user);

    /**
     * Get the sender email address
     *
     * @return The sender of the emails
     */
    String getSender();
}
