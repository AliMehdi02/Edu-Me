package com.cs2001.group34.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cs2001.group34.model.Guide;

public interface GuideRepository extends CrudRepository<Guide, Integer> {
	
	@Query(value="SELECT * FROM guides g WHERE g.guide_name LIKE %?1% "
			+ "OR g.guide_desc LIKE %?1% "
			+ "OR g.guide_content LIKE %?1%", nativeQuery=true)
	List<Guide> findGuidesBySearchWord(String word);
	
	@Query(value="SELECT * FROM guides g WHERE (g.guide_name LIKE %?1% "
			+ "OR g.guide_desc LIKE %?1% "
			+ "OR g.guide_content LIKE %?1%) AND ((g.topic_id IN (?2)) AND (g.exp_lvl IN (?3)) AND (user_id IN (?4)));", nativeQuery=true)
	List<Guide> findGuideByFilterCriteria(String word, Collection<Integer> topics, 
			Collection<Integer> experience, Collection<Integer> authorId);
	
	@Query(value="SELECT * FROM guides g WHERE (g.topic_id IN (?1)) AND (g.exp_lvl IN (?2)) AND (user_id IN (?3));", nativeQuery=true)
	List<Guide> findGuideByFilterOnly(Collection<Integer> topics, 
			Collection<Integer> experience, Collection<Integer> authorId);
	
	@Query(value="SELECT * FROM guides g, guide_tags gt "
			+ "WHERE (g.guide_id = gt.guide_id AND gt.tag_name IN (?4)) "
			+ "AND ((g.topic_id IN (?1)) AND (g.exp_lvl IN (?2)) AND (g.user_id IN (?3)));", nativeQuery=true)
	List<Guide> findGuideByFilterOnlyWithTags(Collection<Integer> topics, 
			Collection<Integer> experience, Collection<Integer> authorId, Collection<String> tags);
	
	@Query(value="SELECT * FROM guides g, guide_tags gt "
			+ "WHERE (g.guide_id = gt.guide_id AND gt.tag_name IN (?5)) "
			+ "AND ((g.guide_name LIKE %?1% OR g.guide_desc LIKE %?1% OR g.guide_content LIKE %?1%) "
			+ "AND ((g.topic_id IN (?2)) AND (g.exp_lvl IN (?3)) AND (user_id IN (?4))));", nativeQuery=true)
	List<Guide> findGuideByFilterCriteriaWithTags(String word, Collection<Integer> topics, 
			Collection<Integer> experience, Collection<Integer> authorId, Collection<String> tags);
	//Returns guides for a specific topic id
	@Query(value ="SELECT * FROM guides g WHERE g.topic_id =?1", nativeQuery=true)
	List<Guide> findGuideByTopicId(int number);
	
    @Modifying()
    @Query(" UPDATE Guide SET numLikes = numLikes + 1 WHERE id = :guide_id  " )
	void updateLike(Integer guide_id);
	
    @Modifying()
	@Query(" UPDATE Guide SET numDislikes = numDislikes + 1 WHERE id = :guide_id  " )
	void updateDislike(Integer guide_id);

    @Query(value = "SELECT * FROM guides g WHERE user_id = ?1", nativeQuery = true)
	List<Guide> findGuidesByUserId(int userId);
    
    
    @Query(value = "SELECT * FROM guides g order by g.number_of_likes desc;" , nativeQuery=true)
    List<Guide> findlikes();
	
}
