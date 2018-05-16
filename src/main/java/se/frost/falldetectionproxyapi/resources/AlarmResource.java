package se.frost.falldetectionproxyapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.frost.falldetectionproxyapi.dto.request.AlarmRequest;
import se.frost.falldetectionproxyapi.service.alarm.MailService;
import se.frost.falldetectionproxyapi.service.users.UserService;

@RestController
@RequestMapping("/alarm")
public class AlarmResource {

    private MailService mailService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> sendAlarmForCurrentUser(@RequestBody AlarmRequest alarmRequest) {
        mailService.sendEmailAlarmForUser(userService.getCurrentUser(), alarmRequest);
        return ResponseEntity.ok("Alarm was sent");
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
