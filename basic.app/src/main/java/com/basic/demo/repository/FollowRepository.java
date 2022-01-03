package com.basic.demo.repository;

import com.basic.demo.model.Follow;
import com.basic.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Follow findByFollowerAndUser(User follower, User user);
}
