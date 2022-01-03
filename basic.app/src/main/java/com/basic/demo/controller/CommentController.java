package com.basic.demo.controller;

import com.basic.demo.model.Comment;
import com.basic.demo.model.Entry;
import com.basic.demo.model.MyUserDetail;
import com.basic.demo.repository.CommentRepositoryImpl;
import com.basic.demo.repository.EntryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepositoryImpl commentRepository;

    @Autowired
    private EntryRepositoryImpl entryRepository;

    @PostMapping("/create")
    public String createComment(@RequestParam("comment") String comment,
                                @RequestParam("id") Integer id,
                                @AuthenticationPrincipal MyUserDetail userDetail) {
        Entry entry = this.entryRepository.findById(id);
        Comment newComment = new Comment(comment, userDetail.getUser(), entry);
        this.commentRepository.createComment(newComment);
        return "redirect:/";
    }

    @GetMapping
    public void createPost(){

    }
}
