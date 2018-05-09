package se.frost.falldetectionproxyapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.frost.falldetectionproxyapi.service.alarm.AlarmService;
import se.frost.falldetectionproxyapi.service.users.UserService;

@RestController
@RequestMapping("/alarm")
public class AlarmResource {

    @Autowired
    AlarmService alarmService;

    @Autowired
    UserService userService;

    @RequestMapping
    public ResponseEntity<String> sendAlarmForUser(){
        alarmService.sendEmailAlarmForUser(userService.getCurrentUser());
        return ResponseEntity.ok("Alarm was sent");
    }
}
