package com.cs2001.group34.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exp_lvl")
public class ExperienceLevel {
	
	@Id
	@Column(name="experience_id")
	private Integer id;
	
	@Column(name="experience_level")
	private String expLvl;
	
	public ExperienceLevel() {
		super();
	}

	public ExperienceLevel(Integer id, String expLvl) {
		super();
		this.id = id;
		this.expLvl = expLvl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpLvl() {
		return expLvl;
	}

	public void setExpLvl(String expLvl) {
		this.expLvl = expLvl;
	}
}
