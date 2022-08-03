package com.cs2001.group34.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cs2001.group34.model.Preferences;
import com.cs2001.group34.model.User;
import com.cs2001.group34.processes.MatchService;
import com.cs2001.group34.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController //gets scanned for annotations such as get mapping and post mapping 
public class ShowMatchesController 
{
	
	@Autowired
	MatchService match;
	
	@Autowired
	UserRepository userRepo;
	
	//get and post mapping	

	@GetMapping(value="getMatch/{id}")
	private ArrayList<Object> findMatch(@PathVariable int id)
	{
		User user = userRepo.getById(id);
		return match.getMatches(user);
	}
	
	//public HashMap<User, Integer> findMatches(User user)

	

}
