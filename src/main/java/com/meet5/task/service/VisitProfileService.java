package com.meet5.task.service;

import com.meet5.task.models.UserProfile;
import com.meet5.task.models.VisitRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.meet5.task.dao.UserProfileDao;
import com.meet5.task.dao.VisitRecordDao;

@Service
public class VisitProfileService extends UserProfileService {

    @Autowired
    UserProfileDao userProfiledao;

    @Autowired
    VisitRecordDao visitRecordDao;

    int FRAUD_THRESHOLD_VISITS = 100;
    long FRAUD_TIME_WINDOW_MS = 10 * 60 * 1000; // 10 minutes

    public ResponseEntity<String> recordVisit(int visitorId, int visitedId) {

        UserProfile visitor;
        try {
             visitor = userProfiledao.findById(visitorId);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Visitor with id " + visitorId + " not found");
        }

        UserProfile visited;
        try {
            visited = userProfiledao.findById(visitedId);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Visitor with id " + visitedId + " not found");
        }

        VisitRecord visit = new VisitRecord();
        visit.setVisitorId(visitor.getId());
        visit.setVisitedId(visited.getId());
        visit.setVisitTime(System.currentTimeMillis());
        visitRecordDao.save(visit);

        checkForFraudulentActivity(visitor.getId(), FRAUD_THRESHOLD_VISITS, FRAUD_TIME_WINDOW_MS);
        return  ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
