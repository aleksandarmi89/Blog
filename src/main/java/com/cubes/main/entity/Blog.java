package com.cubes.main.entity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.print.attribute.standard.DateTimeAtCreation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "blogs")
public class Blog {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private	int id;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name="user_username")
	private	User user;
	@Column
	@NotNull
	@Size(min=20,max=255,message = "Minimalan broj karaktera naslova je 20,a maksimalan broj karaktera je 255.")
	private String title;
	@Column
	private	String image;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	@OneToMany (cascade= { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "blog")
	private List<Comment> comments;
	@ManyToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name = "blogs_tags",joinColumns =@JoinColumn(name="blog_id"),inverseJoinColumns = @JoinColumn(name="tag_id"))
	private	List<Tag>tags;
	@Column
	private int views;
	@Column(name = "date_created",nullable=false,updatable=false)
	private Date dateCreated;
	@Column
	@NotNull
	@Size(min=50,max=500,message = "Minimalan broj karaktera opisa je 50,a maksimalan broj karaktera je 500.")
	private  String description;

	@Column
	String post;
	@Column
    private	boolean enabled;
	@Column
	private	boolean important;
	
	public Blog() {}

	public Blog(int id, User user, String title, String image, Category category, List<Comment> comments,
			List<Tag> tags, int views, String post, boolean enabled,
			boolean important) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.image = image;
		this.category = category;
		this.comments = comments;
		this.tags = tags;
		this.views = views;
		
		this.post = post;
		this.enabled = enabled;
		this.important = important;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}





	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}
	
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getCeoTitle() {
		return title.replace(" ", "-").toLowerCase();
	}
   public String gettimeAgo() {
	   Date date=new Date();
	   
	   
	   long timeAgo=date.getTime()/1000-dateCreated.getTime()/1000;
	   long seconds=timeAgo;
	   int minutes=Math.round(timeAgo/60);
	   int hours=Math.round(timeAgo/3600);
	   int days=Math.round(timeAgo/86400);
	   int months=Math.round(timeAgo/2600640);
	   int years=Math.round(timeAgo/31207680);
	   
	   if(seconds<=60) {
		   return "just now";
		   
	   }
	   else if(minutes<=60) {if(minutes==1) {
		   return "one minute ago";}
	   else {return minutes+" minutes ago";}	   
	   }
	   else if(hours<=24) {if(hours==1) {
		   return "one hour ago";}
	   else {return hours+" hours ago";}	   
	   }
	   else if(days<=30) {if(days==1) {
		   return "one day ago";}
	   else {return days+" days ago";}	   
	   }
	   else if(months<=12) {if(months==1) {
		   return "one month ago";}
	   else {return months+" months ago";}	   
	   }	   else {if(years==1) {
		   return "one year ago";}
	   else {return years+" years ago";}}
	   
	   
	   
   }
   
   public long  getnumComents() {
	   int brojac =0;
	   for(Comment c : comments) {
		   
		   if(c.getEnabled()) {
			   brojac=brojac+1;
		   }
	   }
	   
	   return brojac;
   }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title+"-"+id;
	}

}
