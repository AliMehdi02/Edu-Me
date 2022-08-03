package com.cs2001.group34.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cs2001.group34.model.User;

//jpa repository already provides a '@repository' annotation internally
public interface MatchRepository extends CrudRepository<User, Integer>
{
	//get users preferences
	
	//List<User> findMatch(int id); //input the user that wants to be matched
	
}