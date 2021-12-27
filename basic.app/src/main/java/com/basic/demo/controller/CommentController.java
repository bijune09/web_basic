package com.basic.demo.controller;

import com.basic.demo.repository.CommentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepositoryImpl commentRepository;

    @PostMapping("/create")
    public String createComment(@RequestParam("comment") String comment,
                                @RequestParam("id") Integer id){
        System.out.println(comment);
        System.out.println(id);
        return "/";
    }
}
