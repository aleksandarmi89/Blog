package com.cubes.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cubes.main.entity.Blog;
import com.cubes.main.repository.BlogRepository;
@Service
public class BlogServiceImpl implements BlogService{
@Autowired
	BlogRepository blogRepository;


	@Override
	public Page<Blog> findPaginated(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.blogRepository.findAllByEnabledTrueAndImportantTrueOrderByIdDesc(pageable);
	}


	@Override
	public Page<Blog> findPaginatedCategory(Integer id, int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.blogRepository.findAllByEnabledTrueAndCategoryId(id, pageable);
	}


	@Override
	public Page<Blog> findPaginatedUser(String username, int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.blogRepository.findAllByUserUsername(username, pageable);
	}



}
