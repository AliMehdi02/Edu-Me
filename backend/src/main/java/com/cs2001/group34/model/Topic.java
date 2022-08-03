package com.cs2001.group34.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="topics")
public class Topic {
	
	@Id
	@Column(name="topic_id")
	private Integer id;
	
	@Column(name="topic_name")
	private String name;
	
	public Topic() {
		super();
	}

	public Topic(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
