package com.fees.management.app.controller;

import com.fees.management.app.service.ScheduledNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private ScheduledNotificationService scheduledNotificationService;

    @GetMapping
    public void triggerEmailAndUpdate(){
        scheduledNotificationService.sendNotifications();
    }
}
