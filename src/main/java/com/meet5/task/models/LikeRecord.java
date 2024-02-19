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
public class LikeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int likedUserId;

    private long likeTime;

    public LikeRecord(int userId, int likedUserId, long likeTime) {
        this.userId = userId;
        this.likedUserId = likedUserId;
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "LikeRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", likedUserId=" + likedUserId +
                ", likeTime=" + likeTime +
                '}';
    }
}
