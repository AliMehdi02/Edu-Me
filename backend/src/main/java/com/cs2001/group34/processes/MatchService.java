package com.cs2001.group34.processes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.PrefRepo;
import com.cs2001.group34.repository.UserRepository;


@Service
public class MatchService  //return a list of key value objects, one being the user id and the other object being the percentage of the match
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrefRepo preferences;
	
	
	//This method goes through all the users and adds to a hashmap if it is a good match along with the match percentage
	public LinkedHashMap<Integer, Integer> findMatches(User user)
	{
		HashMap<Integer, Integer> matches = new HashMap<Integer, Integer>(); //to store the matches
		List<String> badPrefs = preferences.returnBadAt(user.getId()); //the list of topics a user wants to learn
		Integer[] userIds = userRepository.findAllUserIds();
		
		for(int i = 0; i < userIds.length; i++)
		{
			Integer matchPercentage = getPercentage(badPrefs, preferences.returnGoodAt(userIds[i]));
			if(user.getId() != userIds[i] && matchPercentage >= 50) //checks if it is not the user that is querying and if it is a good match
			{
				matches.put(userIds[i], matchPercentage); //put the user at i and call the method which returns the percentage of match
			}
		}
		
		ArrayList<Integer> usersId = new ArrayList<>(matches.keySet());
		//User tempUser = new User();
		Integer tempUser; 
		boolean fixed = false;
		
		while (!fixed) //a flag to check if theres any swaps made
		{
			fixed = true;
			for (int i = 0; i < matches.size() - 1; i++) 
			{ //checks through each element to check if theres any element bigger than the one next to it
				//matches remains unsorted but it sorts the users arraylist instead
				if (matches.get(usersId.get(i)) < matches.get(usersId.get(i + 1))) 
				{
					tempUser = usersId.get(i); // the smaller value is saved to a temp variable
					usersId.set(i, usersId.get(i + 1)); //sets the position with the number next to it (the bigger value)
					usersId.set((i + 1), tempUser); // the temp value is then written to the index of the next element
					fixed = false; // the flag is now false which means there was a swap made
				}
			}
		}
		
		LinkedHashMap<Integer, Integer> sortedMatches = new LinkedHashMap<Integer, Integer>();
		
		for(int i = 0; i<matches.size(); i++)
		{
			sortedMatches.put(usersId.get(i), matches.get(usersId.get(i)));
		}

		return sortedMatches;
	}	
	
	
	 public Integer getPercentage(List listOfPrefs, List compareListTo) // list of users bad At prefs, another user's good at list 
	 { // adds a fraction of the list size (1/size) to the returned percentage if an item from the users list is contained in the other users list
		double matches = 0.0;
		
		int totalTopics = listOfPrefs.size();
		for(int i = 0; i < totalTopics; i++) 
		{
			if(compareListTo.contains(listOfPrefs.get(i)))
			{	
				matches++;
			} 
		}
		double percentage = (matches/totalTopics) * 100;
		return (int) percentage; // a fitness function that checks how good the match is
	 }
	 

	@SuppressWarnings("null")
	public ArrayList<Object> getMatches(User user)
	 {
		 ArrayList<Object> match = new ArrayList<>();
		 LinkedHashMap<Integer, Integer> matchPercentages = findMatches(user);
		 ArrayList<Integer> ids = new ArrayList<>(matchPercentages.keySet());
		 for(int i = 0; i < matchPercentages.size(); i++)
		 {
			 User u = userRepository.findUserById(ids.get(i));
			 
				 //User confirmedUser = new User(); 
			 	LinkedHashMap<String, Object> matchObj = new LinkedHashMap<String, Object>(); 
			 	 matchObj.put("id", ids.get(i));
				 matchObj.put("percentage", matchPercentages.get(ids.get(i)));
				 matchObj.put("username", u.getUserName());
				 matchObj.put("image",u.getImage());
				 match.add(matchObj);
		 }
		 
		 return match;
	 }
	 
}
