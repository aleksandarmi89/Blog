package com.cubes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cubes.main.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	
	 @Query("SELECT count(*) FROM Message m WHERE m.seen = 0")
	    long countmessage();
}
