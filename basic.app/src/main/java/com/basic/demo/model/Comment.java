package com.basic.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String commentBody;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne(targetEntity = Entry.class)
    @JoinColumn(name = "entry_id", referencedColumnName = "entryId")
    private Entry entry;

    public Comment(String commentBody, User user, Entry entry) {
        this.commentBody = commentBody;
        this.user = user;
        this.entry = entry;
    }
}
