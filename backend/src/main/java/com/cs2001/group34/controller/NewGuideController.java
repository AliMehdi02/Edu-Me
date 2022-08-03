package com.cs2001.group34.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.cs2001.group34.dto.AddCommentDto;
import com.cs2001.group34.dto.GuideComment;
import com.cs2001.group34.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs2001.group34.model.Guide;
import com.cs2001.group34.model.GuideTag;
import com.cs2001.group34.processes.GuideService;

@CrossOrigin
@RestController

public class NewGuideController {

	@Autowired
	private GuideService guideService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/submit-new-guide")
	private String createNewGuide(@RequestBody List<Object> obj) {
		LinkedHashMap lhm = (LinkedHashMap) obj.get(0);
		Guide guide = new Guide((String) lhm.get("content"),
				(String) lhm.get("description"),
				(Integer) lhm.get("expLvl"),
				(String) lhm.get("title"),
				(Integer) lhm.get("topicId"),
				(Integer) lhm.get("userId"));
		return guideService.createNewGuide(guide, (ArrayList<String>) obj.get(1));
	}

	@GetMapping(value = "/view-guides")
	private List<Guide> viewAllGuides() {
		return guideService.viewAllGuides();
	}

	@GetMapping(value = "/view-guides/{guideId}")
	private Guide viewGuide(@PathVariable String guideId) {
		return guideService.getGuide(guideId);
	}
	
	@GetMapping(value = "/view-guides/view-guide-with-tags/{guideId}")
	private List<Object> viewGuideWithTags(@PathVariable String guideId) {
		Guide guide = guideService.getGuide(guideId);
		List<String> tags = guideService.getGuideTags(Integer.parseInt(guideId));
		List<Object> obj = new ArrayList<>();
		obj.add(guide);
		obj.add(tags);
		return obj;
	}
	
	@GetMapping(value = "/user-posted-guides/{userId}")
	private List<Guide> viewUsersGuides(@PathVariable int userId) {
		return guideService.viewUserGuides(userId);
	}
	
	@PutMapping(value = "/update-guide/{userId}/{guideId}")
	private String editGuide(@PathVariable int userId, @PathVariable int guideId, @RequestBody List<Object> changes) {
		return guideService.editGuide(userId, guideId, changes);
	}
	
	@DeleteMapping(value = "/delete-guide/{userId}/{guideId}")
	private String deleteGuide(@PathVariable int userId, @PathVariable int guideId) {
		return guideService.deleteGuide(userId, guideId);
	}

	@GetMapping(value = "/view-guides-comments/{guideId}")
	private List<GuideComment> guideComments(@PathVariable Integer guideId) {
		return guideService.allComments(guideId);
	}

	@PostMapping("/addComment")
	private Comment addComment(@RequestBody AddCommentDto addCommentDto) {
		return guideService.addComment(addCommentDto);
	}
	//retrieves all guides that have topics that the user wants to learn
	@GetMapping( value = "/view-guides-topics/{userId}")
    private List <Guide> viewGuidesByTopic(@PathVariable int userId){
        return guideService.getByTopicId(userId);
    }
	//updates number of likes in database
	@GetMapping( value = "/addLike/{guide_id}")
	private int UpdateLikes(@PathVariable int guide_id ) {
	    Guide guide=new Guide();
	    guide=guideService.addLike(guide_id);
		return guide.getNumLikes();
	 
	}

	//update number of dislikes 
	@GetMapping( value = "/addDislike/{guide_id}")
	private int UpdateDislikes(@PathVariable int guide_id ) {
	    Guide guide=new Guide();
	    guide=guideService.addDislike(guide_id);
		return guide.getNumDislikes();

	}
	
	
	@GetMapping( value = "/findlikes")
	private List<Guide> findlikes() {
		return guideService.findalllikes();
	}
}
