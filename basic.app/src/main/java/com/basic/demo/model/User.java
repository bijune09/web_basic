package com.basic.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String userBirthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private Account userAccount;

    @OneToMany(mappedBy = "user")
    private Set<Comment> user;

    @OneToMany(mappedBy = "follower")
    private List<Follow> userList;

    public User(String userName, String userBirthday, Account userAccount) {
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userAccount = userAccount;
    }
}
