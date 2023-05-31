package com.ssafy.enjoyTrip.attraction.controller;

import com.ssafy.enjoyTrip.attraction.dto.RegistrationLog;
import com.ssafy.enjoyTrip.attraction.model.service.RegistrationLogService;
import com.ssafy.enjoyTrip.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/registration")
@CrossOrigin("*")
public class RegistrationLogController {
    @Autowired
    private RegistrationLogService registrationLogService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addRegistrationLog(@RequestBody Map<String, String> map) throws SQLException{
        System.out.println(map);
        RegistrationLog registrationLog = new RegistrationLog();
        registrationLog.setContentId(Integer.parseInt(map.get("contentId")));
        registrationLog.setAccountUid(userService.getUserUidToId(map.get("accountId")));

        RegistrationLog value =  registrationLogService.alreadyRegistration(registrationLog);

        if(value == null){
            registrationLogService.addRegistrationLog(registrationLog);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping("{contentId}")
    public ResponseEntity<?> getRegistrationList(@PathVariable int contentId) throws SQLException{
        List<RegistrationLog> regiList =  registrationLogService.selectByContentId(contentId);
        return ResponseEntity.ok(regiList);
    }

    @GetMapping("/change/{contentId}")
    public ResponseEntity<?> changeCheckToTrue(@PathVariable int contentId) throws SQLException{
        registrationLogService.changeCheckToTrue(contentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/already")
    public ResponseEntity<?> alreadyRegistration(@RequestBody Map<String, Object> map) throws SQLException{
        RegistrationLog registrationLog = new RegistrationLog();
        registrationLog.setContentId(Integer.parseInt((String)map.get("contentId")));
        registrationLog.setAccountUid(userService.getUserUidToId((String)map.get("accountId")));

        RegistrationLog value =  registrationLogService.alreadyRegistration(registrationLog);

        if(value == null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();

    }

}
