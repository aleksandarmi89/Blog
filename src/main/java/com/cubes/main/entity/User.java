package com.cubes.main.entity;

import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;






@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String phone;
	@Column
	private boolean enable;
	@Column
	private String email;
	@Column
	private String image;
	@Transient
	private MultipartFile file;
	

	@ManyToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinTable(name = "authorities",joinColumns =@JoinColumn(name="username"),inverseJoinColumns = @JoinColumn(name="authority"))
	private Collection<Role> roles;
	
  public User(){}

public User(String username, String password, String name, String surname, String phone, boolean enable, String email,
		String image) {
	super();
	this.username = username;
	this.password = password;
	this.name = name;
	this.surname = surname;
	this.phone = phone;
	this.enable = enable;
	this.email = email;
	this.image = image;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public boolean getEnable() {
	return enable;
}

public void setEnable(boolean enable) {
	this.enable = enable;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}




public Collection<Role> getRoles() {
	return roles;
}

public void setRoles(Collection<Role> roles) {
	this.roles = roles;
}




public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return username+"-"+name;
}

public void generateBCPassword() {
	if(!getPassword().contains("{bcrypt}")) {
	 String password=new BCryptPasswordEncoder().encode(getPassword());
	 setPassword("{bcrypt}"+password);}
}
public String getEncodedPassword() {
	
	return password.replace("{bcrypt}", "");
}

 public String getCeoTitle() {
	 return name.replace(" ", "-").toLowerCase()+"-"+surname.replace(" ", "-").toLowerCase();
 }

}
