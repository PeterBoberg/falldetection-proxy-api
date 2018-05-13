package se.frost.falldetectionproxyapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.frost.falldetectionproxyapi.service.alarm.AlarmService;
import se.frost.falldetectionproxyapi.service.users.UserService;

@RestController
@RequestMapping("/alarm")
public class  AlarmResource {

    private AlarmService alarmService;
    private UserService userService;

    @RequestMapping
    public ResponseEntity<String> sendAlarmForCurrentUser(){
        alarmService.sendEmailAlarmForUser(userService.getCurrentUser());
        return ResponseEntity.ok("Alarm was sent");
    }

    @Autowired
    public void setAlarmService(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
