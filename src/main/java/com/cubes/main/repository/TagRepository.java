package com.cubes.main.repository;




import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cubes.main.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{
	
	@Query( "SELECT a FROM Tag a ORDER BY  a.blogs.size DESC" )
	public List<Tag>list();
	
	
}

