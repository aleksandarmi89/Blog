package com.cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column
	private String name;
	@Column
	private String email;
	
	@Column(name = "message")
	private String m;
	@Column
	private boolean seen;
	
	public Message () {}

	public Message(int id, String name, String message, boolean seen) {
		super();
		this.id = id;
		this.name = name;
		this.m = message;
		this.seen = seen;
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

	public String getM() {
		return m;
	}

	public void setM(String message) {
		this.m = message;
	}

	public boolean getSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
@Override
public String toString() {
	// TODO Auto-generated method stub
	return id+"-"+name;
}
}
