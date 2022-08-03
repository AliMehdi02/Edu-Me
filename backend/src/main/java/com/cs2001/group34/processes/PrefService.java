package com.cs2001.group34.processes;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cs2001.group34.dto.PreferenceDto;
import com.cs2001.group34.model.Preferences;
import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.PrefRepo;
import com.cs2001.group34.repository.UserRepository;

@Service
public class PrefService {

	@Autowired
	private PrefRepo Repo;

	@Autowired
	private UserRepository userRepository;


	public void AddPref(PreferenceDto pref) throws Exception {
		Preferences preferences = new Preferences(pref.getId(), pref.getTopic(), pref.getExpLvl(), pref.getGoodAt());
		Optional<User> user = userRepository.findById(pref.getUserId());
		if (user.isEmpty()) {
			throw new Exception("User not Found");
		}
		preferences.setUser(user.get());
		Repo.save(preferences);
	}



	
//	@Autowired
//	private UserRepository userRepository; 
	
	public void AddPref(Preferences pref) {
		Repo.save(pref);
	}
	
	//reference : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example //not using this anymore
	
	public List<String> returnGoodAt(int id)
	{
		return Repo.returnGoodAt(id);
	}
	
	public List<String> returnBadAt(int id)
	{
		return Repo.returnBadAt(id);
	}

}
