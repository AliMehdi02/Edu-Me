package com.cs2001.group34.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cs2001.group34.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
	
	@Query(value="SELECT topic_id FROM topics t;", nativeQuery=true)
	Integer[] findAllTopicIds();
	
	//retrieve topic ids for topics a user wants to learn
	@Query(value=" SELECT DISTINCT topic_id FROM topics t " + " INNER JOIN user_pref p ON t.topic_name=p.topic  "
			+ " WHERE  p.user_id = ?1 "
			 + " AND  p.good_at = 0 ; ",nativeQuery=true)
	       Integer[]findTopicIdsByUserId( int user_id);

}
