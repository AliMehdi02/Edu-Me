package com.cs2001.group34.dto;

public class PreferenceDto {
	private int id;
	private String topic;
	private String expLvl;
	private Boolean goodAt;
	private Integer userId;

	public PreferenceDto(int id, String topic, String expLvl, Boolean goodAt, Integer userId) {
		this.id = id;
		this.topic = topic;
		this.expLvl = expLvl;
		this.goodAt = goodAt;
		this.userId = userId;
	}

	public PreferenceDto()
	{

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
