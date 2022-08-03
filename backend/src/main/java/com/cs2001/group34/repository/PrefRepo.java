package com.cs2001.group34.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cs2001.group34.model.Preferences;

@Repository
public interface PrefRepo extends JpaRepository<Preferences, Integer>
{
	
	@Query(value="SELECT DISTINCT u.topic FROM user_pref u WHERE u.user_id LIKE ?1 AND u.good_at = 1 ;", nativeQuery=true)
	List<String> returnGoodAt(int id);
	
	@Query(value="SELECT DISTINCT u.topic FROM user_pref u WHERE u.user_id LIKE ?1 AND u.good_at = 0 ;", nativeQuery=true)
	List<String> returnBadAt(int id);
	
//	@Query(value="SELECT * FROM user_pref u WHERE u.user_id LIKE %?1% AND u.good_at = 0 ;", nativeQuery=true)
//	List<Preferences> returnBadAtStr(String username);
	
}


