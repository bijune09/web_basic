package com.basic.demo.repository;

import com.basic.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment,Integer> {
}
