package com.meet5.task.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int visitorId;

    private int visitedId;

    private long visitTime;

    public VisitRecord(int visitorId, int visitedId, long visitTime) {
        this.visitorId = visitorId;
        this.visitedId = visitedId;
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "VisitRecord{" +
                "id=" + id +
                ", visitorId=" + visitorId +
                ", visitedId=" + visitedId +
                ", visitTime=" + visitTime +
                '}';
    }
}
