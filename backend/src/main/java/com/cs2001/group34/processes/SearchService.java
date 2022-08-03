package com.cs2001.group34.processes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2001.group34.model.Guide;
import com.cs2001.group34.repository.ExperienceRepository;
import com.cs2001.group34.repository.GuideRepository;
import com.cs2001.group34.repository.TopicRepository;
import com.cs2001.group34.repository.UserRepository;

@Service
public class SearchService {

	@Autowired
	private GuideRepository guideRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired
	private ExperienceRepository expRepo;

	
	// from: https://stackoverflow.com/questions/46508634/sorting-an-arraylist-
	//according-to-number-of-occurences-of-similar-elements
	public static List<Guide> frequencyBasedSort(List<Guide> guides) {
		@SuppressWarnings("unchecked")
		List<Guide>[] bucket = new List[guides.size() + 1];
		
		Map<Guide, Integer> freqMap = new HashMap<>();
		List<Guide> res = new ArrayList<>();
		
		for (Guide g: guides) {
			freqMap.put(g, freqMap.getOrDefault(g, 0) + 1);
		}
		
		for (Guide key: freqMap.keySet()) {
			int frequency = freqMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}
		
		for (int i=bucket.length - 1; i>=0; i--) {
			if (bucket[i] != null) {
				res.addAll(bucket[i]);
			}
		}
		return res;
	}


	public List<String[]> searchFilter(String lookupText, ArrayList<Integer> topics, ArrayList<Integer> experience, String authorName, ArrayList<String> tags) {
		ArrayList<Integer> authorIds = new ArrayList<Integer>();
		if (authorName.length() != 0) Collections.addAll(authorIds, userRepo.findUserId(authorName));
		else {
			authorIds.add(0);
			authorIds.add(1);
			Collections.addAll(authorIds, userRepo.findAllUserIds());
		}
		
		if (topics.size() == 0) Collections.addAll(topics, topicRepo.findAllTopicIds());
		if (experience.size() == 0) Collections.addAll(experience, expRepo.findAllExperienceIds());
		
		String[] lookupWords = lookupText.toLowerCase().split(" ");
		List<Guide> guides = new ArrayList<>();
		
		// look for words
		if (lookupText.length() == 0) {
			if (tags.size() > 0) {
				guideRepo.findGuideByFilterOnlyWithTags(topics, experience, authorIds, tags).forEach(guides::add);
			}
			else {
				guideRepo.findGuideByFilterOnly(topics, experience, authorIds).forEach(guides::add);
			}
		}
		else {
			if (tags.size() > 0) {
				for (String word: lookupWords) {
					guideRepo.findGuideByFilterCriteriaWithTags(word, topics, experience, authorIds, tags).forEach(guides::add);
				}
			}
			else {
				for (String word: lookupWords) {
					guideRepo.findGuideByFilterCriteria(word, topics, experience, authorIds).forEach(guides::add);
				}
			}
		}
		
		return makePresentable(guides);
	}
	
	
	private List<String[]> makePresentable(List<Guide> guides) {
		// sort by frequency and get rid of duplicates while maintaining order
		guides = frequencyBasedSort(guides);
		
		List<String[]> strGuides = new ArrayList<>();
		for (Guide guide: guides) {
			String[] strGuide = new String[7];
			strGuide[0] = "" + guide.getId();
			strGuide[1] = guide.getTitle();
			
			try {
				strGuide[2] = userRepo.findById(guide.getUserId()).get().getUserName();
			} catch (Exception e) {
				strGuide[2] = "user not found";
			}
			
			strGuide[3] = String.valueOf(guide.getLastUpdated());
			strGuide[4] = guide.getDescription();
			strGuide[5] = topicRepo.findById(guide.getTopicId()).get().getName();
			strGuide[6] = expRepo.findById(guide.getExpLvl()).get().getExpLvl();
			strGuides.add(strGuide);
		}
		return strGuides;
	}
	
}





