package com.rentia.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.models.User;
import com.rentia.services.EmailService;
import com.rentia.services.UserService;
import com.rentia.utilities.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/u1/forgotPassword")
public class ForgotController {
	Random random = new Random(1000);

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOTP(@RequestParam("email") String email,HttpSession session)
	{
		System.out.println("EMAIL "+email);
		
		//generating otp of 4 digit
		
		int otp = random.nextInt(999999);
		
		System.out.println("OTP "+otp);	
		
		//write code for send otp to email...
		
		
		String subject="Reset Password : Rentia";
		String message=""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h1>"
				+ "OTP is "
				+ "<b>"+otp
				+ "</n>"
				+ "</h1> "
				+ "</div>";
		String to=email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			session.setAttribute("message", "Check your email id !!");
			
			return new ResponseEntity<String>("Check your email id !!", HttpStatus.OK);
			
		}else
		{	
			
			session.setAttribute("message", "Check your email id !!");
		
			return new ResponseEntity<String>("Check your email id !!", HttpStatus.OK);
		}
		
		
	}

	// verify otp
	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestParam("otp") int otp, @RequestBody String resetPasswordBody, HttpSession session) throws JsonMappingException, JsonProcessingException
	{
		int myOtp=(int)session.getAttribute("myotp");
		
		System.out.println("User OTP "+otp);
		System.out.println("Our OTP "+myOtp);
		
		String email=(String)session.getAttribute("email");
		System.out.println("Our email "+email);
		Boolean l_ok = userService.verfiyOtp(otp, myOtp, email);
		if(l_ok)
		{
			User currentUser = userService.getUserByEmail(email);
		    JsonNode jsonObj = JsonUtil.stringToJson(resetPasswordBody);
        	String newPassword = jsonObj.get("newPassword").asText();
			l_ok = userService.changePassword(currentUser.getUsername(), newPassword, null);
			if (l_ok) {
				session.setAttribute("message", "Password is change Successfully !!");
				return new ResponseEntity<String>("Password is change Successfully !!", HttpStatus.OK);
			} else {
				session.setAttribute("message", "Password is not changed. Please try again!!");
				return new ResponseEntity<String>("Password is not changed. Please try again!!", HttpStatus.BAD_REQUEST);
			}
			
		}else
		{
			session.setAttribute("message", "There is some error to reset the Password. Please correct the otp or email !!");
			return new ResponseEntity<String>("There is some error to reset the Password. Please correct the otp or email !!", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * //change password
	 * 
	 * @PostMapping("/change-password") public String
	 * changePassword(@RequestParam("newpassword") String newpassword,HttpSession
	 * session) { String email=(String)session.getAttribute("email"); User user =
	 * this.userRepsitory.getUserByUserName(email);
	 * user.setPassword(this.bcrypt.encode(newpassword));
	 * this.userRepsitory.save(user); return
	 * "redirect:/signin?change=password changed successfully..";
	 * 
	 * }
	 */
	
	
}
