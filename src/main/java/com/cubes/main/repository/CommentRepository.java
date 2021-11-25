package com.cubes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cubes.main.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

	public List<Comment> findAllByEnabledTrue();

	 @Query("SELECT count(*) FROM Comment m WHERE m.seen = 0")
	    long countComment();
	 
	
	 public Long countCommentBySeenFalseAndBlogUserUsername(String username);
	 public List<Comment> findAllByBlogUserUsername(String username);
}
