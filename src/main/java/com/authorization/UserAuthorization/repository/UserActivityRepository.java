package com.authorization.UserAuthorization.repository;

import com.authorization.UserAuthorization.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity,Integer> {

    List<UserActivity> findByUserId(int userId);

    UserActivity findTopByUserIdOrderByLoginTimeDesc(int userId);
}
