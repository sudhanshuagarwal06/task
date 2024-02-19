package com.meet5.task.dao;

import com.meet5.task.models.VisitRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VisitRecordDao extends JpaRepository<VisitRecord, Integer>{
    List<VisitRecord> findByVisitorId(int visitorId, Date timestamp);
}
