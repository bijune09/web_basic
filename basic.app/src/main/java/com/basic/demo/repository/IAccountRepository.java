package com.basic.demo.repository;

import com.basic.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountUsername(String username) throws UsernameNotFoundException;

    @Query(value = "select account_id, account_email, account_password, account_username\n" +
            "from account\n" +
            "order by account_id desc\n" +
            "limit 1",nativeQuery = true)
    Account findNewIdAccount();
}
