package com.cs2001.group34.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cs2001.group34.model.GuideTag;

import java.util.HashSet;
import java.util.List;

public interface GuideTagRepository extends CrudRepository<GuideTag, Integer> {

    List<GuideTag> findByGuideId(int id);
    
    @Query(value="SELECT tag_name FROM guide_tags gt WHERE guide_id = ?1", nativeQuery=true)
    HashSet<String> findByGuideIdHS(int id);
    
    @Query(value="SELECT * FROM guide_tags gt WHERE guide_id = ?1 AND tag_name = ?2", nativeQuery=true)
    GuideTag findByGuideIdAndTagName(int id, String name);

}
