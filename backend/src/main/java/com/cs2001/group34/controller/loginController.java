package com.cs2001.group34.controller;


import com.cs2001.group34.dto.LoginDto;
import com.cs2001.group34.dto.LoginRequestDto;
import com.cs2001.group34.model.Login;
import com.cs2001.group34.model.User;
import com.cs2001.group34.processes.loginService;
import com.cs2001.group34.repository.UserRepository;
import com.cs2001.group34.security.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
public class loginController {
	
	@Autowired
    private loginService ls;
    @Autowired
    private JWT jwt;
    @Autowired
    private AuthenticationManager a;
    @Autowired
    private UserRepository ur;

    @GetMapping("/login") 
    public ModelAndView login() {
    	ModelAndView mv = new ModelAndView("login");
        mv.addObject("user", new Login());
        return mv;
    }

    @PostMapping("/loginn")
    public LoginDto login(@RequestBody LoginRequestDto user) throws Exception {
        try{
            a.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
        }catch (Exception e){
            throw new Exception("Invalid Username or Password");
        }
        LoginDto loginDto = ls.login(user.getUsername(), user.getPassword());
        loginDto.setToken(jwt.CT(user.getUsername()));
        return loginDto;
//    	LoginDto login = Service.login(user.getUsername(), user.getPassword());
//        return login;
}
@PostMapping("/log")
public User getUser(@RequestBody LoginRequestDto user){
        return ur.findByUsername(user.getUsername());

}

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
    	
	  
        return "redirect:/login";
    }

}