package com.meet5.task.dao;

import com.meet5.task.models.LikeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LikeRecordDao extends JpaRepository<LikeRecord, Integer> {
    List<LikeRecord> findByUserId(int userId, Date timestamp);
}
