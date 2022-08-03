package com.cs2001.group34.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cs2001.group34.model.ExperienceLevel;

public interface ExperienceRepository extends CrudRepository<ExperienceLevel, Integer> {
	
	@Query(value="SELECT experience_id FROM exp_lvl el;", nativeQuery=true)
	Integer[] findAllExperienceIds();
	
}
