package com.meet5.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meet5.task.service.LikeProfileService;

@RestController
@RequestMapping("/user")
public class LikeProfileController {

    @Autowired
    LikeProfileService likeProfileService;

    @PostMapping("/like")
    public ResponseEntity<String> likeProfile(@RequestParam int userId, @RequestParam int likedUserId) {
        return likeProfileService.recordLike(userId, likedUserId);
    }
}
