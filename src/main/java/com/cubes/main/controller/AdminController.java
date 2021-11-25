package com.cubes.main.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cubes.main.entity.Blog;
import com.cubes.main.entity.Category;
import com.cubes.main.entity.ChangePassword;
import com.cubes.main.entity.Comment;
import com.cubes.main.entity.Message;
import com.cubes.main.entity.Slider;
import com.cubes.main.entity.Tag;
import com.cubes.main.repository.BlogRepository;
import com.cubes.main.repository.CategoryRepository;
import com.cubes.main.repository.CommentRepository;
import com.cubes.main.repository.MessageRepository;
import com.cubes.main.repository.RoleRepository;
import com.cubes.main.repository.SliderRepository;
import com.cubes.main.repository.TagRepository;
import com.cubes.main.repository.UserRepository;


import com.cubes.main.entity.User;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SliderRepository sliderRepository;
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	MessageRepository messageRepository;
	
	
	@RequestMapping(value = "/")
	public String getDashBoardPage( Model model,Principal p) {
		
		
		long messageNum=messageRepository.countmessage();
	long commentNum=commentRepository.countCommentBySeenFalseAndBlogUserUsername(p.getName());
	      model.addAttribute("num",  commentRepository.countComment());
	     model.addAttribute("b",  commentNum);
	      model.addAttribute("mes", messageNum);
		return "dashboard";
	}
	
	
	//---------------------Category--------------------------------------
	@GetMapping(value = "/categories")
	public String getCategoryList(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "category-list";
	}
	@GetMapping(value = "/category-form")
	public String getCategoryFormPage(Model model) {
		model.addAttribute("category", new Category());
		return "category-form";}
		
		@PostMapping(value = "/category-save")
		public String getCategorySave(@ModelAttribute Category category) {
		categoryRepository.save(category);
		return "redirect:categories";
	}
	@GetMapping( value = "/category-update")
	public String getCategoryUpdatePage(@RequestParam int id,Model model) {
		Category category=categoryRepository.getById(id);
		model.addAttribute("category", category);
		
		return "category-form";
	}
	@GetMapping(value = "/category-delete")
	public String getCategoryDelete(@RequestParam int id,Model model) {
		
		categoryRepository.deleteById(id);
	   
		return"redirect:categories";
	}
	
	//--------------------------------------Tags----------------------------------------------------------
	@GetMapping(value = "/tags")
	public String getTagsList(Model model) {
		model.addAttribute("tags", tagRepository.findAll());
		return "tag-list";
	}
	@GetMapping(value = "/tag-form")
	public String getTagFormPage(Model model) {
		model.addAttribute("tag", new Tag());
		return "tag-form";}
		
		@PostMapping(value = "/tag-save")
		public String getTagSave(@ModelAttribute Tag tag) {
		tagRepository.save(tag);
		return "redirect:tags";
	}
	@GetMapping( value = "/tag-update")
	public String getTagUpdatePage(@RequestParam int id,Model model) {
		Tag tag=tagRepository.getById(id);
		model.addAttribute("tag", tag);
		
		return "tag-form";
	}
	@GetMapping(value = "/tag-delete")
	public String getTagDelete(@RequestParam int id,Model model) {
		
		tagRepository.deleteById(id);
	   
		return"redirect:tags";
	}
	//------------------------------User-------------------------------------------------------
	 @GetMapping(value = "/users")
	  public String getUserListPage(Principal principal,Model model) {
		  
		  model.addAttribute("users",userRepository.findAll());
		  
		  
		  return "user-list";
	  }
	  
	  
	  @GetMapping(value = "/user-enable")
	  public String getUserEnabled(@RequestParam String username) {
		  User user=userRepository.getById(username);
		user.setEnable(!user.getEnable());
		userRepository.save(user);
		  return "redirect:users";
	  }
	  
	  
	  @GetMapping(value = "/user-form")
	  public String getUserForm(Model model ) {
		
		 model.addAttribute("user", new User());
		model.addAttribute("userRoles", roleRepository.findAll());
		
		  return "user-form";
	  }
	
	  @RequestMapping(value = "/user-save")
	  public String getUserSave(HttpSession session,
			  @ModelAttribute User user, Model model ) {
	  
	/*  if( user.getFile()!=null  ||  !user.getFile().isEmpty()  ) {  
		  
		  user.setImage(user.getFile().getOriginalFilename());
		  String  filename=user.getFile().getOriginalFilename();
		  String path=session.getServletContext().getRealPath("/")
				  +"resources"+File.separator+"admin"+File.separator+"slike"+File.separator+filename;
		  System.out.println("putanja"+path);
		  try {byte barr[]=user.getFile().getBytes();
		  
		  FileOutputStream fos=new FileOutputStream(path);
		  fos.write(barr);
		  fos.close();
		  
		  }catch(Exception e) {System.out.println(e);} */
		
	   user.setEnable(true);
	   user.generateBCPassword();
		 
		 userRepository.save(user);
	 
			 return "redirect:/admin/";}
	  
	  
	  @GetMapping(value = "/user-delete")
	  public String getUserDelete(@RequestParam String username) {
		
		 userRepository.deleteById(username);
		
		
		  return "redirect:users";
		  
		  
	  }
	  
	  
	  
	  @GetMapping( value = "/user-update")
		public String getUserUpdatePage(@RequestParam String username,Model model) {
			User user=userRepository.getById(username);
			model.addAttribute("user", user);
			model.addAttribute("userRoles", roleRepository.findAll());
			model.addAttribute("isAdmin", true);
			return "user-form-update";}
	  
	  @GetMapping(value = "/user-myupdate")
	  public String getUserMyUpdate(Principal principal,Model model) {
			User u =userRepository.getById(principal.getName());
			User l=userRepository.getById(principal.getName());
		
	  	model.addAttribute("user", u);
	  	model.addAttribute("user1", l);
	  	return "user-form-update";}
	  
	  
	  @GetMapping(value = "/user-change-password")
	  public String getChangePassword(Principal principal,Model model) {
	  	model.addAttribute("changePassword", new ChangePassword());
		model.addAttribute("user", userRepository.getById(principal.getName()));
		
	  	return "user-form-change-password";}
	  
	  
	  @PostMapping(value = "/user-save-change-password")
	  public String getChangePasswordSave(@ModelAttribute ChangePassword changePassword,Principal principal,Model model) {
		  User user=userRepository.getById(principal.getName());
		  BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		  if(!changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {
			  //ako password nije isti
			  model.addAttribute("message", "Nova lozinka i potvrda nove lozinke se ne poklapaju.");
			  model.addAttribute("changePassword", changePassword);
		
		  }
		  else if(!encoder.matches(changePassword.getOldPassword(), user.getEncodedPassword())) {
			  //ako stari password ne odgovara korisniku
			  model.addAttribute("message", "Stara lozinka nije odgovarajuca.");
			  model.addAttribute("changePassword", changePassword);
			 
		  }
		  
		  else {
			  user.setPassword(changePassword.getNewPassword());
			  user.generateBCPassword();
			  userRepository.save(user);
			  model.addAttribute("message", "Uspesno ste promenili password.");
			  model.addAttribute("changePassword", new ChangePassword());
			  model.addAttribute("alert", true);
		  }
		  
		  return "user-form-change-password";
	  }
	  
	  @ModelAttribute("loginUser")
		public User getLoginUser(Principal p) {
			User user=userRepository.getById(p.getName());
					return user;
		}
	  
	  
	 
	  
	  //-------------------------------------------SLIDERS--------------------------------
	  @GetMapping(value = "/sliders")
		public String getSlidersList(Model model) {
			model.addAttribute("sliders", sliderRepository.findAll());
			return "slider-list";
		}
		@GetMapping(value = "/slider-form")
		public String getSlidersFormPage(Model model) {
			model.addAttribute("slider", new Slider());
			return "slider-form";}
			
			@PostMapping(value = "/slider-save")
			public String getSliderSave(@ModelAttribute Slider slider) {
				slider.setEnabled(true);
			sliderRepository.save(slider);
			return "redirect:sliders";
		}
		@GetMapping( value = "/slider-update")
		public String getSliderUpdatePage(@RequestParam int id,Model model) {
			Slider slider=sliderRepository.getById(id);
			
			model.addAttribute("slider", slider);
			
			return "slider-form";
		}
		@GetMapping(value = "/slider-delete")
		public String getSliderDelete(@RequestParam int id,Model model) {
			
			sliderRepository.deleteById(id);
		   
			return"redirect:sliders";
		}
		  @GetMapping(value = "/slider-enabled")
		  public String getSliderEnabled(@RequestParam int id) {
			  Slider slider=sliderRepository.getById(id);
			slider.setEnabled(!slider.getEnabled());
			sliderRepository.save(slider);
			  return "redirect:sliders";
		  }
		  //---------------------------------------Comment---------------------------------------------------
		  
		  @GetMapping(value = "/comments")
		  public String getCommentList( Model model,Principal principal) {
		  	
		  	List<Comment> list=commentRepository.findAll();
		  	model.addAttribute("comments", list);
		  	model.addAttribute("bloger", commentRepository.findAllByBlogUserUsername(principal.getName()));
		  	return "comment-list";
		  }
		  @GetMapping(value = "/comment-seen")
		  public String getCommentSeenPage(@RequestParam int id) {
		  	Comment comment=commentRepository.getById(id);
		  	comment.setSeen(true);;
		  	commentRepository.save(comment);
		  	return "redirect:comments";
		  }
		  @GetMapping(value = "/comment-enabled")
		  public String getCommentEnabled(@RequestParam int id) {
			  Comment comment=commentRepository.getById(id);
			comment.setEnabled(!comment.getEnabled()); 
			
			commentRepository.save(comment);
			  return "redirect:comments";
		  }
		  
		  //---------------------------------BLOG------------------------------------------------------------
		  
		  
	
		  
		  
		  
		  
		  @GetMapping(value="/blogs")
			public String getBlogList(@RequestParam(required = false) String title, @RequestParam(required = false) Integer category, 
					@RequestParam(required = false) String username, @RequestParam(required = false) Boolean enabled, Model model, Principal p) {
				
				if(username!= null && username.length() == 0) {
				username = null;}
				
				if(title!=null && title.length() == 0) {
					title = null;
					}
				
				
				List<Blog>list1 = blogRepository.findBlogByCategoryIdAndUserUsernameAndEnabled(title, category, p.getName(), enabled);
						
				List<Category> categories = categoryRepository.findAll();
				List<Tag> tags = tagRepository.findAll();
				List<User> users = userRepository.findAll();
				model.addAttribute("blogs", blogRepository.findBlogByCategoryIdAndUserUsernameAndEnabled(title, category, username, enabled));
				model.addAttribute("blogs1", list1);
				model.addAttribute("categories",  categories);
				model.addAttribute("tags",  tags);
				model.addAttribute("users",  users);
				model.addAttribute("userSelected", username);
				model.addAttribute("titleSelected", title);
				model.addAttribute("categorySelected", category);
				model.addAttribute("enabledSelected", enabled);
				

				return "blog-list";
			}
		  
		  
		  
		  
		  
		  
		  
		  @GetMapping(value = "/blog-form")
		  public String getProductForm(Principal principal,Model model) {
		  	List<Tag> tags=tagRepository.findAll();
		  List<Category>categories=categoryRepository.findAll();
		  User user=userRepository.getById(principal.getName());
		  model.addAttribute("user", user);
		  	model.addAttribute("blog", new Blog());
		  	model.addAttribute("tags", tags);
		  	model.addAttribute("categories", categories);
		  	return "blog-form";
		  }
		  @PostMapping(value = "/blog-save")
		  public String getBlogSave(@Validated @Valid @ModelAttribute Blog blog,BindingResult result,Principal principal,Model model){
			  
				List<Tag> tags1=tagRepository.findAll();
				  List<Category>categories=categoryRepository.findAll();
				  model.addAttribute("tags", tags1);
				  	model.addAttribute("categories", categories);
			  
			  if(result.hasErrors()) {
				  return "blog-form";
			  }
			  
		  	Category category=categoryRepository.getById(blog.getCategory().getId());
		  	List<Tag>tags=new ArrayList<Tag>();
		  	for(Tag tag:blog.getTags()) {
		  		Tag tempTag=tagRepository.getById(tag.getId());
		  		tags.add(tempTag);
		  	}
		  	User user=userRepository.getById(principal.getName());
		  	blog.setCategory(category);
		  blog.setTags(tags);
		 blog.setUser(user);
		
		 
		 blog.setDateCreated(new Date());
			 
		
		 
		 blogRepository.save(blog);
		  	
		  	return "redirect:blogs";

}
		  @GetMapping(value = "/blog-update")
		  public String getProductUpdate( @RequestParam int id,Principal principal,Model model) {
			 Blog blog=blogRepository.getById(id);
			  blog.setViews(blog.getViews()+1);
		  	
		  	model.addAttribute("categories", categoryRepository.findAll());
		  	model.addAttribute("tags", tagRepository.findAll());
		  	model.addAttribute("user", userRepository.findAll());
		  	model.addAttribute("blog", blog);
		  	return "blog-form";
		  }  	
		  @GetMapping(value = "/blog-enabled")
		  public String getBlogEnabled(@RequestParam int id) {
			  Blog blog=blogRepository.getById(id);
			blog.setEnabled(!blog.getEnabled());
			blogRepository.save(blog);
			  return "redirect:blogs";
		  }
		  @GetMapping(value = "/blog-important")
		  public String getBlogImportant(@RequestParam int id) {
			  Blog blog=blogRepository.getById(id);
			blog.setImportant(!blog.getImportant());
			blogRepository.save(blog);
			  return "redirect:blogs";
		  }
		  @GetMapping(value = "/blog-delete")
			public String getBlogDelete(@RequestParam int id,Model model) {
				
				blogRepository.deleteById(id);
			   
				return"redirect:blogs";
			}
		  //-----------------------Message---------------------------------------------------------
		  
		  @GetMapping(value = "/messages")
		  public String getMessageList( Model model) {
		  	
		  	List<Message> list=messageRepository.findAll();
		  	model.addAttribute("messages", list);
		  	
		  	return "message-list";
		  }
		  @GetMapping(value = "/message-seen")
		  public String getMessageSeenPage(@RequestParam int id) {
		  	Message message=messageRepository.getById(id);
		  	message.setSeen(true);;
		  	messageRepository.save(message);
		  	return "redirect:messages";
		  }
		  @GetMapping(value = "/message-delete")
		  public String getMessageDelete(@RequestParam int id) {
			  Message message=messageRepository.getById(id);
			  messageRepository.delete(message);
			  return "redirect:messages";
		  }


}