package com.cs2001.group34.security;

import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String un) throws UsernameNotFoundException {
        User u = ur.findByUsername(un);
        return new org.springframework.security.core.userdetails.User(u.getUserName(),u.getPassword(),new ArrayList<>());

    }
}
