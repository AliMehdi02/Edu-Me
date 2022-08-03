package com.cs2001.group34.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="guides")
public class Guide {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="guide_id")
	private Integer id;
	
	@Column(name="guide_name")
	private String title;
	
	@Column(name="guide_desc")
	private String description;
	
	@Column(name="guide_content")
	private String content;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="topic_id")
	private Integer topicId;
	
	@Column(name="exp_lvl")
	private Integer expLvl;
	
	@Column(name="number_of_likes")
	private Integer numLikes;
	
	@Column(name="source_id")
	private Integer sourceId;
	
	@Column(name="date_created")
	private java.sql.Date dateCreated;
	
	@Column(name="last_updated")
	private java.sql.Date lastUpdated;
	
	@Column(name="number_of_dislikes")
	private Integer numDislikes;

	@OneToMany
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Guide() {
		super();
	}
	
	public Guide(String content, String description, Integer expLvl, String title, Integer topicId) {
		super();
		this.content = content;
		this.description = description;
		this.expLvl = expLvl;
		this.title = title;
		this.topicId = topicId;
	}
	public Guide(String content, String description, Integer expLvl, String title, Integer topicId, Integer userId) {
		super();
		this.content = content;
		this.description = description;
		this.expLvl = expLvl;
		this.title = title;
		this.topicId = topicId;
		this.userId= userId;
	}

	public Guide(Integer id, String title, String description, String content, Integer userId, Integer topicId,
			Integer expLvl, Integer numLikes, Integer sourceId, Date dateCreated, Date lastUpdated) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.userId = userId;
		this.topicId = topicId;
		this.expLvl = expLvl;
		this.numLikes = numLikes;
		this.sourceId = sourceId;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getExpLvl() {
		return expLvl;
	}

	public void setExpLvl(Integer expLvl) {
		this.expLvl = expLvl;
	}

	public Integer getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(Integer numLikes) {
		this.numLikes = numLikes;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public java.sql.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.sql.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public java.sql.Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(java.sql.Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getNumDislikes() {
		return numDislikes;
	}

	public void setNumDislikes(Integer numDislikes) {
		this.numDislikes = numDislikes;
	}
	
	
}