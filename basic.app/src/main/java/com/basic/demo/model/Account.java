package com.basic.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String accountUsername;

    private String accountPassword;

    private String accountEmail;

    @OneToOne(mappedBy = "userAccount")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Account(String accountUsername, String accountPassword, String accountEmail) {
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.accountEmail = accountEmail;
    }
}
