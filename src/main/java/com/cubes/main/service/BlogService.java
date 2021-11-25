package com.cubes.main.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cubes.main.entity.Blog;
import com.cubes.main.repository.BlogRepository;

public interface BlogService {

	Page<Blog> findPaginated(int pageNo,int pageSize);
	Page<Blog> findPaginatedCategory(Integer id, int pageNo,int pageSize);
	Page<Blog> findPaginatedUser(String username, int pageNo,int pageSize);
}
