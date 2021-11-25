package com.cubes.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cubes.main.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

	   
	   public List<Blog> findFirst3ByEnabledTrueOrderByIdDesc();
	
		public List<Blog> findFirst12ByEnabledTrueOrderByIdDesc();
		
		public List<Blog> findFirst3ByEnabledTrueAndImportantTrueOrderByIdDesc();
		
		
		public List<Blog> findFirst3ByEnabledTrueOrderByViewsDesc();	
		public List<Blog> findAllByEnabledTrueAndCategoryId(Integer id);
		public List<Blog> findAllByCategoryId(Integer id);
		public List<Blog> findAllByTagsId(Integer id);
		public List<Blog> findAllByUserUsername(String username);
		public List<Blog> findAllByEnabledTrueAndUserUsername(String username);
		public List<Blog> findByEnabledTrueAndTitleLikeOrDescriptionLikeOrPostLikeOrderByIdDesc(String text,String t,String p);
		public  List<Blog> findByEnabledTrueAndIdLessThan(int id);
		public  List<Blog> findByEnabledTrueAndIdGreaterThan(int id);
		public Page<Blog>findAllByEnabledTrueAndImportantTrueOrderByIdDesc(Pageable pageable);
		public Page<Blog> findAllByEnabledTrueAndCategoryId(Integer id,Pageable pageable);
		public Page<Blog> findAllByUserUsername(String username,Pageable pageable);
		public  List<Blog> findByIdAfter(int id);
		
		// metoda za filter na admin delu:
		
		
		@Query("SELECT b FROM Blog b WHERE (:title is null or b.title LIKE %:title%)  and " + 
		"(:category is null or b.category.id = : category) and " +		
		"(:username is null" + " or b.user.username = : username) and " +
		"(:enabled is null" + " or b.enabled = : enabled) " +
		"ORDER BY b.id Desc")

		public List<Blog>findBlogByCategoryIdAndUserUsernameAndEnabled( @Param ("title") String title, 
				@Param ("category") Integer category, 
				@Param ("username") String username,
				@Param ("enabled") Boolean enabled);



	}	
		
		

