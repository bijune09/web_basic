package com.basic.demo.repository;

import com.basic.demo.model.Account;
import com.basic.demo.model.MyUserDetail;
import com.basic.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountRepositoryImpl implements UserDetailsService {
    @Autowired
    private IAccountRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.userRepository.findByAccountUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
        return new MyUserDetail(account);
    }

    public void createAccount(Account account){
        this.userRepository.save(account);
    }

    public Account findNewAccount(){
        return this.userRepository.findNewIdAccount();
    }

}
