package com.cs2001.group34.processes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cs2001.group34.model.User;
import com.cs2001.group34.repository.UserRepository;
import com.sendgrid.*;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
		    return optionalUser.get();
		} else {
		    throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
		}
    }
	
    public void registerUser(String firstname, String lastname, String username, String email, String password) throws NoSuchAlgorithmException {
    	User n = new User();
    	n.setFirstName(firstname);
    	//System.out.println(n.getFirstName());
    	n.setLastName(lastname);
    	n.setUsername(username);
    	n.setEmail(email);
    	n.setPassword(toSHA256(password));
		String pin = pinGenerator();
		n.setPin(pin);
    	try {
			sendEmail(n);
			System.out.println("sending the email....");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	final User createdUser = userRepository.save(n);

    }
	
    public void verifyUser(String pin, String email) {
    	//get the user using the email from the frontend
    	Optional<User> user = userRepository.findByEmail(email);
    	//get the user pin from the information
    	User userToVerify = user.get();
    	String userPin = userToVerify.getPin(); 	
    	if (pin.equals(userPin)) {
    		userToVerify.setVerified(true);
    		System.out.println(userToVerify.toString());
    		userRepository.save(userToVerify);	
    	} 
    	
    }
    
    public boolean changePassword(int id, String password, String confirmPassword) throws NoSuchAlgorithmException {
    	
    	Optional<User> u = userRepository.findById(id);
    	User userToUpdate = u.get();
    	if(password.equals(confirmPassword)) {
    		userToUpdate.setPassword(toSHA256(password));
        	userRepository.save(userToUpdate);
        	return true;
    	} else {
    		return false;
    	}
    	
    }
    
	//encryption for psw so it is not stored in plaintext
	private String toSHA256(String password) throws NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
	    return hash.toString();
	  }
	
	//generates the pin for the user at login that then gets sent to the email
	private String pinGenerator() {
		 String pin="";
		  for(int i =0; i<6; ++i) {
			  //the pin is made up of 5 random digits
			  pin +=new Random().nextInt(9 - 1 + 1) + 1;
		  }
//		  System.out.println(pin);
		  
		  return pin;
	}
	
	private void sendEmail(User u) throws IOException {
	

		//using my uni email for now
		Email from = new Email("2018147@brunel.ac.uk");
	    String subject = "your verification pin";
	    //user who just registered
	    Email to = new Email(u.getEmail());
	    Content content = new Content("text/plain", "here is your pin: "+ u.getPin());
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
	private String generateURL(String email) {
		String url ="http://localhost:3000/reset/password/";
		Optional<User> u =userRepository.findByEmail(email);
		User userToUpdate = u.get();
		url +=userToUpdate.getId().toString();
		return url;
	}
	
	public void sendResetPasswordURL(String email) throws IOException {
		String url =generateURL(email);
		Email from = new Email("2018147@brunel.ac.uk");
	    String subject = "reset your password";
	    //user who just registered
	    Email to = new Email(email);
	    Content content = new Content("text/plain", "click on this link to reset your password\n "
	    		+ "if this request did not come from you, please ignore this email "+ url);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
	
	
	//reference: https://www.youtube.com/watch?v=oTJ89wcz5Ec 
	
//	public saveUserProfile(MultipartFile file, int id)
//	{
//		User u = userRepository.findById(id);
//		String fileName =  StringUtils.cleanPath(file.getOriginalFilename());
//		
//		n.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//		
//		userRepository.save(u); 
//	}
	

}
