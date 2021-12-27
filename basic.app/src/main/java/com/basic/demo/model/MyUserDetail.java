package com.basic.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetail implements UserDetails {
    private final Account account;

    public MyUserDetail(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : this.account.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorityList;
    }


    @Override
    public String getPassword() {
        return account.getAccountPassword();
    }

    @Override
    public String getUsername() {
        return account.getAccountUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getAccountId(){
        return this.account.getAccountId();
    }

    public User getUser(){
        return this.account.getUser();
    }


}
