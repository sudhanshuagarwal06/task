package com.meet5.task.service;

import com.meet5.task.dao.LikeRecordDao;
import com.meet5.task.dao.VisitRecordDao;
import com.meet5.task.models.LikeRecord;
import com.meet5.task.models.VisitRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserProfileService {

    @Autowired
    LikeRecordDao likeRecordDao;

    @Autowired
    VisitRecordDao visitRecordDao;

    void checkForFraudulentActivity(int userId, int threshold, long timeWindowMs) {

        List<VisitRecord> recentVisits = visitRecordDao.findByVisitorId(userId, new Date(System.currentTimeMillis() - timeWindowMs));
        List<LikeRecord> recentLikes = likeRecordDao.findByUserId(userId, new Date(System.currentTimeMillis() - timeWindowMs));

        if (recentVisits.size() + recentLikes.size() >= threshold) {
            System.out.println("Potential fraudulent activity detected for user " + userId);
        }
    }
}
