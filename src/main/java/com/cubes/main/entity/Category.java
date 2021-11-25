package com.cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	
	public Category(String id) {
		 this.id=Integer.valueOf(id);
	}
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column
   private int id;
 @Column
   private String name;
 @Column
 private int priority;
 @OneToMany (cascade= { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "category")
	
 List<Blog>blogs;
 
 public Category() {}

public Category(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

 
 public int getPriority() {
	return priority;
}

public void setPriority(int priority) {
	this.priority = priority;
}

public List<Blog> getBlogs() {
	return blogs;
}

public void setBlogs(List<Blog> blogs) {
	this.blogs = blogs;
}
public String getCeoTitle() {
	return name.replace(" ", "-").toLowerCase();
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+"-"+id;
	}
	
}
