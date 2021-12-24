package com.basic.demo.repository;

import com.basic.demo.model.Account;
import com.basic.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
