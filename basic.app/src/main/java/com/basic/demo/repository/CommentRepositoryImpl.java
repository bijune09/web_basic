package com.basic.demo.repository;

import com.basic.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentRepositoryImpl {
    @Autowired
    private ICommentRepository commentRepository;

    public void createComment(Comment comment){
        this.commentRepository.save(comment);
    }
}
