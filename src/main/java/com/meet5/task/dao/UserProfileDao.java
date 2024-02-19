package com.meet5.task.dao;

import com.meet5.task.models.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {
    UserProfile findById(int Id);
}
