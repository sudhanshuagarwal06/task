package com.meet5.task.service;

import com.meet5.task.dao.LikeRecordDao;
import com.meet5.task.dao.UserProfileDao;
import com.meet5.task.models.LikeRecord;
import com.meet5.task.models.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeProfileService extends UserProfileService{

    @Autowired
    UserProfileDao userProfiledao;

    @Autowired
    LikeRecordDao likeRecordDao;

    int FRAUD_THRESHOLD_LIKES = 100;
    long FRAUD_TIME_WINDOW_MS = 10 * 60 * 1000; // 10 minutes

    public ResponseEntity<String> recordLike(int userId, int likedUserId) {

        UserProfile user;
        try {
            user = userProfiledao.findById(userId);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Visitor with id " + userId + " not found");
        }

        UserProfile likedUser;
        try {
            likedUser = userProfiledao.findById(likedUserId);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Visitor with id " + likedUserId + " not found");
        }

        LikeRecord like = new LikeRecord();
        like.setUserId(user.getId());
        like.setLikedUserId(likedUser.getId());
        like.setLikeTime(System.currentTimeMillis());
        likeRecordDao.save(like);

        checkForFraudulentActivity(user.getId(), FRAUD_THRESHOLD_LIKES, FRAUD_TIME_WINDOW_MS);
        return  ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
