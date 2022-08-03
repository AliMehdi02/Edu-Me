package com.cs2001.group34.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cs2001.group34.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value="SELECT id FROM users u WHERE u.username LIKE %?1% ;", nativeQuery=true)
	Integer[] findUserId(String username);

	User findByUsername(String userName);

	Optional<User> findByUsernameAndPassword(String userName, String password);

	@Query(value="SELECT id FROM users;", nativeQuery=true)
	Integer[] findAllUserIds();
	
	Optional<User> findByEmail(String email);
	
	@Query(value="DELETE FROM users u where u.id LIKE %?1% ;", nativeQuery=true)
	void removeUser(@Param("id") Integer id);
	
	//update user table with the pin generated for verification
	//@Modifying(clearAutomatically = true)
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Transactional
	@Query(value="UPDATE users u SET u.verified = :verified WHERE u.id = :id", nativeQuery=true)
	void verified(@Param("verified") boolean verified, @Param("id") Integer id);
	
	User findUserById(int id);
	
//	//get the pin for user x from the db
//	@Query(value="SELECT pin FROM users u WHERE u.id LIKE %?1%", nativeQuery=true)
//	String getPin(@Param("id") Integer id);
}
