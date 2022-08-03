package com.cs2001.group34.model;


import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User implements UserDetails {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="firstName")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="pin")
	private String pin;
	@Column(name="verified")
	private boolean verified;	
//	private ArrayList <Integer> friends;
	
	@Column(name="username",unique = true)
	private String username;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "preferences_id")
	private List<Preferences> preferences;


	private ArrayList <Integer> friends; //maybe do a one to one relationship = user to user
	
	@Column(name="image")
	private String image;
	

	public User() {
		super();
	}

	public User(String email, String password, String username) {

		super();
		this.email = email;
		this.password = password;
		this.username = username;


	}

	public boolean isVerified() {
		return verified;
	}

	public List<Preferences> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preferences> preferences) {
		this.preferences = preferences;

	}


	//setters
	public void setId(Integer id) {
	    this.id=id;
	  }

	  public void setFirstName(String firstName) {
		    this.firstName=firstName;
	  }
	  public void setLastName(String lastName) {
		  this.lastName=lastName;
	  }
	 
	  public void setEmail(String email) {
		    this.email=email;
	  }
	  public void setPassword(String password) {
		  this.password=password;
	  }
	  public void setPin(String pin) {
		  this.pin=pin;
	  }
	  
	  public void setVerified(boolean verified) {
		  this.verified=verified;
	  }
//	  public void setFriends(ArrayList<Integer> friends) {
//		  this.friends = friends;
//	  }
	public void setUsername(String username) {
		this.username = username;
	}
	
	//getters
	  public Integer getId() {
		    return id;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public String getLastName() {
		  return lastName;
	  }
	  public String getEmail() {
	    return email;
	  }
	  public String getPassword() {
		  return password;
	  }
	  public String getPin() {
		  return pin;
	  }
	  public boolean getVerified() {
		  return verified;
	  }
	  public ArrayList<Integer> getFriends(){
		  return friends;
	  }
	  public String getUserName() {
		  return username;
	  }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
