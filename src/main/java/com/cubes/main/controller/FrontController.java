package com.cubes.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cubes.main.entity.Blog;
import com.cubes.main.entity.Comment;
import com.cubes.main.entity.Message;
import com.cubes.main.repository.BlogRepository;
import com.cubes.main.repository.CategoryRepository;
import com.cubes.main.repository.CommentRepository;
import com.cubes.main.repository.MessageRepository;
import com.cubes.main.repository.SliderRepository;
import com.cubes.main.repository.TagRepository;
import com.cubes.main.repository.UserRepository;
import com.cubes.main.service.BlogService;




@Controller
public class FrontController {
	
	@Autowired
	BlogService blogService;
	@Autowired
	SliderRepository sliderRepository;
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	 @GetMapping(value = { "/index","/"})
	  public String getIndexPage(Model model) {
		 List<Blog> list = blogRepository.findFirst12ByEnabledTrueOrderByIdDesc();
		 model.addAttribute("sliders", sliderRepository.findAllByEnabledTrueOrderByPriorityDesc());
		 model.addAttribute("blogs", blogRepository.findFirst3ByEnabledTrueAndImportantTrueOrderByIdDesc());
		 model.addAttribute("blogovi", list);
		 model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		 model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
		  return "index";
	  }
	 
	 
	 @RequestMapping(value = "/contact")
		public String  getContactPage (Model model) {
		 Message m=new Message();
		    model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
		    model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
		    model.addAttribute("blogs", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
			model.addAttribute("message",m);
			model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
			model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
			return "contact-form";
		}
		@PostMapping(value = "/contact-save")
		public String  getContactSavePage (@ModelAttribute Message message) {
			messageRepository.save(message);
			return "redirect:contact";
		}
		 @GetMapping(value = "/blogs/{pageNo}")
		  public String getBlogsPage( @PathVariable( value="pageNo")int pageNo,  Model model) {
			 List<Blog> list = blogRepository.findFirst12ByEnabledTrueOrderByIdDesc();
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			// model.addAttribute("blogs", blogRepository.findFirst12ByEnabledTrueOrderByIdDesc());
			 model.addAttribute("blogovi", list);
		     model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		     model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
		     model.addAttribute("tags", tagRepository.list());
		     
		     
		     
		     int pageSize=10;
		     
		     Page<Blog> page=blogService.findPaginated(pageNo, pageSize);
		     List<Blog> list1=page.getContent();
		     model.addAttribute("currentPage", pageNo);
		     model.addAttribute("totalPage", page.getTotalPages());
		     model.addAttribute("listBlogs", list1);
		     
		   
			  return "blogs";
		 }
		 
		 
		 
		 @GetMapping(value = "/blog-post/{ceoTitle}/{id}")
		  public String getBlogPost(@PathVariable int id,
				  Model model) {
			 Blog blog=blogRepository.getById(id);
			 blog.setViews(blog.getViews()+1);
			 blogRepository.save(blog);
			 model.addAttribute("tags", tagRepository.list());
			 model.addAttribute("blog", blog);
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
			 model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			 model.addAttribute("comment", new Comment());
		  
			 List<Blog> b=blogRepository.findByEnabledTrueAndIdLessThan(id);
		 
		  
		  if(b.size()!= 0)
		{Blog b18=b.get(b.size()-1);
		
	     model.addAttribute("ba", b18);
			
		} else
			{model.addAttribute("ba", blog);}

		
		List<Blog> baaa=blogRepository.findByEnabledTrueAndIdGreaterThan(id);
		
		      if(!baaa.isEmpty()) {
		    	  Blog b019=baaa.get(0);
				 	model.addAttribute("ban", b019);}
			 		else {model.addAttribute("ban", blog);}
			 
			 return "blog-post";
		 }
		 @PostMapping(value = "/comment-save/{ceoTitle}/{id}")
			public String  getCommentSavePage ( @Validated @ModelAttribute Comment comment,@PathVariable int id) {
			
			 	Blog blog= blogRepository.getById(id);
				comment.setDateCreated(new Date());
		        comment.setBlog(blog); 
		      //  comment.setId(0);
		        comment.setEnabled(true);
		        commentRepository.save(comment);
		 
		 
				return "redirect:/blog-post/"+blog.getCeoTitle()+"/"+id;
		 }
		 @GetMapping(value = "/blog-search")
		  public String getBlogSearchPage(Model model,@RequestParam String text) {
			 
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			 model.addAttribute("tags", tagRepository.list());
			 model.addAttribute("blogs", blogRepository.findByEnabledTrueAndTitleLikeOrDescriptionLikeOrPostLikeOrderByIdDesc("%"+text+"%", "%"+text+"%","%"+text+"%"));
		     model.addAttribute("text", text);
		     model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		     model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
			  return "blog-search";
		 }
		 @GetMapping(value = "/blog-category/{ceoTitle}/{id}/{pageNoe}")
		  public String getBlogCategoryPage(Model model ,@PathVariable int id,  @PathVariable( value="pageNoe")int pageNoe) {
			 model.addAttribute("category", categoryRepository.getById(id));
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			 model.addAttribute("tags", tagRepository.list());
		//	 model.addAttribute("blogs", blogRepository.findAllByEnabledTrueAndCategoryId(id));
		
		     model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		     model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
		     
  int pageSize=2;
		     
		     Page<Blog> page=blogService.findPaginatedCategory(id, pageNoe, pageSize);
		     List<Blog> list1=page.getContent();
		     model.addAttribute("currentPage1", pageNoe);
		     model.addAttribute("totalPage", page.getTotalPages());
		     model.addAttribute("listBlogs", list1);
		     
		     
		     
		     
			  return "blog-category";
		 }
		 @GetMapping(value = "/blog-tag/{ceoTitle}/{id}")
		  public String getBlogTagPage(Model model ,@PathVariable int id ) {
			 model.addAttribute("tag", tagRepository.getById(id));
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			 model.addAttribute("tags", tagRepository.list());
			 model.addAttribute("blogs", blogRepository.findAllByTagsId(id));
		
		     model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		     model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
			  return "blog-tag";
		 }
		 @GetMapping(value = "/blog-author/{ceoTitle}/{username}/{pageNoe}")
		  public String getBlogTagPage(Model model ,@PathVariable String username, @PathVariable( value="pageNoe")int pageNoe ) {
			 model.addAttribute("user", userRepository.getById(username));
			 model.addAttribute("categoriess", categoryRepository.findFirst5ByOrderByPriorityAsc());
			 model.addAttribute("b", blogRepository.findFirst3ByEnabledTrueOrderByViewsDesc());
			 model.addAttribute("tags", tagRepository.list());
			// model.addAttribute("blogs", blogRepository.findAllByEnabledTrueAndUserUsername(username));
		
		     model.addAttribute("categories", categoryRepository.findFirst4ByOrderByPriorityAsc());
		     model.addAttribute("blogFooter", blogRepository.findFirst3ByEnabledTrueOrderByIdDesc());
		     
		     
 int pageSize=2;
		     
		     Page<Blog> page=blogService.findPaginatedUser(username, pageNoe, pageSize);
		     List<Blog> list1=page.getContent();
		     model.addAttribute("currentPage1", pageNoe);
		     model.addAttribute("totalPage", page.getTotalPages());
		     model.addAttribute("listBlogs", list1);
		     
		     
		     
		     
		     
		     
			  return "blog-author";
		 }
		 
}
