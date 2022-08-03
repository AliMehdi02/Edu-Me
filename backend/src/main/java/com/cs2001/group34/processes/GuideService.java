package com.cs2001.group34.processes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.cs2001.group34.dto.AddCommentDto;
import com.cs2001.group34.dto.GuideComment;
import com.cs2001.group34.model.Comment;
import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.CommentRepository;
import com.cs2001.group34.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2001.group34.model.Guide;
import com.cs2001.group34.model.GuideTag;
import com.cs2001.group34.repository.GuideRepository;
import com.cs2001.group34.repository.GuideTagRepository;
import com.cs2001.group34.repository.TopicRepository;
@Transactional
@Service
public class GuideService {

	@Autowired
	private GuideRepository guideRepo;
	@Autowired
	private GuideTagRepository guideTagRepo;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    
	public String createNewGuide(Guide guide, ArrayList<String> tags) {
		// TODO: FIND ID OF LOGGED IN USER
		// int userId = 2;
		// guide.setUserId(userId);
		// user id sent from frontend

		guide.setSourceId(-1);
		guide.setDateCreated(getCurrentDate());
		guide.setLastUpdated(getCurrentDate());
		guide.setNumLikes(0);

		guideRepo.save(guide);
		guide.setSourceId(guide.getId());
		guideRepo.save(guide);

		// add tags for the created guideId
		for (String tagText : tags) {
			GuideTag tag = new GuideTag(tagText, guide.getId());
			guideTagRepo.save(tag);
		}

		return "{\"result\":\"guide saved.\"}";
	}

	private java.sql.Date getCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public List<Guide> viewAllGuides() {
		List<Guide> guides = new ArrayList<>();
		guideRepo.findAll().forEach(guides::add);
		return guides;
	}

	public Guide getGuide(String guideId) {
		int id = Integer.parseInt(guideId);
		return guideRepo.findById(id).orElse(null);
	}

	public List<GuideComment> allComments(Integer guideId) {
		return commentRepository.allCommentsForGuide(guideId);
	}
	
	public Comment addComment(AddCommentDto addCommentDto) {
		Comment comment = new Comment();
		User user = userRepository.findById(addCommentDto.getUserId())
				.orElseThrow(() -> new IllegalStateException("User Not Exists"));
		comment.setUser(user);
		Guide guide = guideRepo.findById(addCommentDto.getGuideId())
				.orElseThrow(() -> new IllegalStateException("Guide Not Exists"));
		comment.setGuide(guide);
		comment.setComment(addCommentDto.getComment());

		Comment savedComment = commentRepository.save(comment);
		guide.getComments().add(savedComment);
		guideRepo.save(guide);

		return savedComment;
	}
	
	//Use userId to get topicIds of the topics that the user wants to learn and generate guides based on these topics
	public List<Guide> getByTopicId( int userId){
        List<Guide> guides=new ArrayList<>();
        
      Integer [] numbers= topicRepository.findTopicIdsByUserId(userId);
        for(int i=0;i<numbers.length;i++) {
        	if(guideRepo.findGuideByTopicId(numbers[i])!=null){
        		guides.addAll(guideRepo.findGuideByTopicId(numbers[i]));
        	}
        	 }
         return guides;
	}
	

	//Update the number of likes for a certain guide using the guideId
      public Guide addLike (Integer guide_id) {
      guideRepo.updateLike(guide_id);
      String id=Integer.toString(guide_id);
       return getGuide(id);
    }

      public Guide addDislike (Integer guide_id) {
	      guideRepo.updateDislike(guide_id);
	      String id=Integer.toString(guide_id);
	       return getGuide(id);
	
	}

	public List<Guide> viewUserGuides(int userId) {
		return guideRepo.findGuidesByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	public String editGuide(int userId, int guideId, List<Object> changes) {
		Guide guide = guideRepo.findById(guideId).get();
		if (userId != guide.getUserId()) return null;
		
		// Modifying Guide
		LinkedHashMap<String, Object> extractedGuide = (LinkedHashMap<String, Object>) changes.get(0);
		Guide guideToSave = new Guide(
				(Integer) extractedGuide.get("id"),
				(String) extractedGuide.get("title"),
				(String) extractedGuide.get("description"),
				(String) extractedGuide.get("content"),
				(Integer) extractedGuide.get("userId"),
				(Integer) extractedGuide.get("topicId"),
				(Integer) extractedGuide.get("expLvl"),
				(Integer) extractedGuide.get("numLikes"),
				(Integer) extractedGuide.get("sourceId"),
				(Date) Date.valueOf(extractedGuide.get("dateCreated").toString()),
				new Date(System.currentTimeMillis())
		);
		guideRepo.save(guideToSave);
		
		// Modifying Tags
		List<String> tagsToSave = (ArrayList<String>) changes.get(1);
		HashSet<String> existingTags = guideTagRepo.findByGuideIdHS(guideId);
		for (String tag: tagsToSave) {
			if (!existingTags.contains(tag)) {
				System.out.println("added " + tag);
				guideTagRepo.save(new GuideTag(tag, guideId));
			}
		}
		for (String gt: existingTags) {
			if (!tagsToSave.contains(gt)) {
				System.out.println("removed " + gt);
				guideTagRepo.delete(guideTagRepo.findByGuideIdAndTagName(guideId, gt));
			}
		}
		
		return "{\"result\":\"guide modified.\"}";
	}

	
	public List<String> getGuideTags(int guideId) {
		List<String> tagsText = new ArrayList<String>();
		List<GuideTag> tags = guideTagRepo.findByGuideId(guideId);
		for (GuideTag tag: tags) tagsText.add(tag.getName());
		return tagsText;
	}

	public String deleteGuide(int userId, int guideId) {
		if (userId != guideRepo.findById(guideId).get().getUserId()) return "{\"result\":\"deletion failed.\"}";
		List<GuideTag> tags = guideTagRepo.findByGuideId(guideId);
		for (GuideTag tag: tags) {
			guideTagRepo.deleteById(tag.getId());
		}
		guideRepo.deleteById(guideId);
		return "{\"result\":\"guide deleted.\"}";
	}
	
	
	
	 public List<Guide> findalllikes() {
	  		List<Guide> findalllikes = new ArrayList<>();
	  		guideRepo.findlikes().forEach(findalllikes::add);
	  		findalllikes.addAll(guideRepo.findlikes());
	  		return findalllikes;

	      }

    
}
	
	

	
	

