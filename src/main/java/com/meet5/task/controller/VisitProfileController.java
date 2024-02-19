package com.meet5.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meet5.task.service.VisitProfileService;

@RestController
@RequestMapping("/user")
public class VisitProfileController {

    @Autowired
    VisitProfileService visitProfileService;

    @PostMapping("/visit")
    public ResponseEntity<String> visitProfile(@RequestParam int visitorId, @RequestParam int visitedUserId) {
        return visitProfileService.recordVisit(visitorId, visitedUserId);
    }
}
