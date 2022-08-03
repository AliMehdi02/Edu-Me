package com.cs2001.group34.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guide_tags")
public class GuideTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tag_id")
	private int id;
	
	@Column(name="tag_name")
	private String name;
	
	@Column(name="guide_id")
	private int guideId;
	
	public GuideTag() {
		super();
	}
	
	public GuideTag(Integer id, String name, int guideId) {
		super();
		this.id = id;
		this.name = name;
		this.guideId = guideId;
	}
	
	public GuideTag(String name, int guideId) {
		super();
		this.name = name;
		this.guideId = guideId;
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

	public int getGuideId() {
		return guideId;
	}

	public void setGuideId(int guideId) {
		this.guideId = guideId;
	}
}
