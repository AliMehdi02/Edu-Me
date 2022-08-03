package com.cs2001.group34.processes;

import com.cs2001.group34.dto.LoginDto;
import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class loginService{
	@Autowired
	private UserRepository ur;
	public LoginDto login(String u, String p)
	{
		Optional<User> user = ur.findByUsernameAndPassword(u, p);
		if (user.isEmpty())
		{
			return new LoginDto(null,false);
		}
		return new LoginDto(user.get().getId(),true);
	}
}