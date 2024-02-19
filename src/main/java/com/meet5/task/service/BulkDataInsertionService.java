package com.meet5.task.service;

import com.meet5.task.models.LikeRecord;
import com.meet5.task.models.UserProfile;
import com.meet5.task.models.VisitRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class BulkDataInsertionService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void insertBulkUserData(List<UserProfile> userList, List<VisitRecord> profileVisitList, List<LikeRecord> userLikeList) {
        insertUsers(userList);
        insertProfileVisits(profileVisitList);
        insertUserLikes(userLikeList);
    }

    private void insertUsers(List<UserProfile> userList) {
        String sql = "INSERT INTO UserProfile (Name, Age) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                UserProfile user = userList.get(i);
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });
    }

    private void insertProfileVisits(List<VisitRecord> profileVisitList) {
        String sql = "INSERT INTO ProfileVisits (VisitorID, VisitedUserID, VisitTimestamp) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                VisitRecord profileVisit = profileVisitList.get(i);
                ps.setInt(1, profileVisit.getVisitorId());
                ps.setInt(2, profileVisit.getVisitedId());
            }

            @Override
            public int getBatchSize() {
                return profileVisitList.size();
            }
        });
    }

    private void insertUserLikes(List<LikeRecord> userLikeList) {
        String sql = "INSERT INTO LikeRecord (LikerID, LikedUserID, LikeTimestamp) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LikeRecord userLike = userLikeList.get(i);
                ps.setInt(1, userLike.getUserId());
                ps.setInt(2, userLike.getLikedUserId());
            }

            @Override
            public int getBatchSize() {
                return userLikeList.size();
            }
        });
    }
}

