package com.basic.demo.repository;

import com.basic.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRepositoryImpl {
    @Autowired
    private IUserRepository iUserRepository;

    public void createUser(User user){
        this.iUserRepository.save(user);
    }
}
