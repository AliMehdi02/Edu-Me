package com.cs2001.group34.repository;

import com.cs2001.group34.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface loginRepo extends JpaRepository<Login, Integer>{
	Optional<Login> findByUsernameAndPassword(String username, String password);

}