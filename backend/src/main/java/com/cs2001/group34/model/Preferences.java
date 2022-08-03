package com.cs2001.group34.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User_Pref")
public class Preferences {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String topic;
	private String expLvl;
	private Boolean goodAt;

	@ManyToOne(fetch = FetchType.EAGER) //eager if u want to get the users too 
	@JoinColumn(name = "user_id")
//	@JsonIgnore 
	private User user;

	public Preferences(int id, String topic, String expLvl, Boolean goodAt) {
		this.id = id;
		this.topic = topic;
		this.expLvl = expLvl;
		this.goodAt = goodAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getExpLvl() {
		return expLvl;
	}


	public void setExpLvl(String expLvl) {
		this.expLvl = expLvl;
	}


	public Boolean getGoodAt() {
		return goodAt;
	}


	public void setGoodAt(Boolean goodAt) {
		this.goodAt = goodAt;
	}


	public Preferences () {
	}


}
