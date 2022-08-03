package com.cs2001.group34.model;

import javax.persistence.*;

@Entity
@Table(name="login")
public class Login {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;


	
	public Login(int id, String username, String password) {

		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}