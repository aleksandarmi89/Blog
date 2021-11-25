package com.cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sliders")
public class Slider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String url;
	@Column
	private String image;
	@Column
	private boolean enabled;
	@Column
	private String button;
	@Column
	private int priority;

	
	 public Slider() {}


	public Slider(int id, String title, String url, String image, boolean enabled, String button, int priority) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.enabled = enabled;
		this.button = button;
		this.priority = priority;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getButton() {
		return button;
	}


	public void setButton(String button) {
		this.button = button;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}
	 
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		return title+"-"+id;
	}
}
