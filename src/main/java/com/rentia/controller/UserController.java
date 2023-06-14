package com.rentia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.engine.jdbc.StreamUtils;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentia.models.UserAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.helper.Message;
import com.rentia.models.User;
import com.rentia.services.EmailService;
import com.rentia.services.FileService;
import com.rentia.services.UserService;
import com.rentia.utilities.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/u1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	Random random = new Random(1000);

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user, HttpSession session) {

		try {
			System.out.println("mukul1" + user.getAdress().size());
			User l_user = userService.registerUser(user);
			String s = "Hi  " + l_user.getAdress().size()
					+ "  your Registration Process successfully completed. Now Please Login to Continue";
			//session.setAttribute("user", user);
			return new ResponseEntity<User>(l_user, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<User>(new User(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
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
	public ResponseEntity<User> verifyOtp(@RequestParam("otp") int otp, HttpSession session) throws JsonMappingException, JsonProcessingException
	{
		int myOtp=(int)session.getAttribute("myotp");
		
		System.out.println("User OTP "+otp);
		System.out.println("Our OTP "+myOtp);
		
		User user= (User) session.getAttribute("user");
		System.out.println("Our email "+user.getEmail());
		if(otp == myOtp)
		{
			User l_user = userService.registerUser(user);
			return new ResponseEntity<User>(l_user, HttpStatus.OK);	
		}else
		{
			return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {

		try {
			User l_user = userService.updateUser(user, userId);
			String s = "Hi  " + l_user.getAdress().size()
					+ "  your Registration Process successfully completed. Now Please Login to Continue";
			return new ResponseEntity<User>(l_user, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<User>(new User(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/getUser/{userName}", produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable("userName") String userName) {
		User user = userService.getUserbyUserName(userName);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/post/image/upload/{userId}")
	public ResponseEntity<User> uploadUserImage(@RequestParam("selfImage") MultipartFile profileImageFile,
			@RequestParam("documentImage") List<MultipartFile> docImageFile, @PathVariable Long userId)
			throws IOException {

		User user = userService.getUserbyUserId(userId);

		if (null != user.getSelfImage()) {
			this.fileService.deleteImage(path, user.getSelfImage());
		}

		String profileImageName = this.fileService.uploadImage(path, profileImageFile);

		List<String> docImageName = new ArrayList<>();
		for (MultipartFile file : docImageFile) {
			String fileName = this.fileService.uploadImage(path, file);
			docImageName.add(fileName);
		}

		user.setSelfImage(profileImageName);
		user.setAadharImage(docImageName);

		User updateUser = userService.updateUser(user, userId);
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);

	}

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

	// change password..handler
	@PostMapping("/changepassword/{userName}")
	public ResponseEntity<String> changePassword(@RequestBody String resetPasswordBody, @PathVariable String userName
			, HttpSession session) throws JsonMappingException, JsonProcessingException {
	    JsonNode jsonObj = JsonUtil.stringToJson(resetPasswordBody);
    	String newPassword = jsonObj.get("newPassword").asText();
    	String oldPassword = jsonObj.get("oldPassword").asText();
		System.out.println("OLD PASSWORD " + oldPassword);
		System.out.println("NEW PASSWORD " + newPassword);
		Boolean ok = userService.changePassword(userName, newPassword, oldPassword);

		if (ok) {
			session.setAttribute("message", new Message("Your password is successfully changed..", "success"));
			return new ResponseEntity<String>("Your password is successfully changed..", HttpStatus.OK);

		} else {
			// error...
			session.setAttribute("message", new Message("Please Enter correct old password !!", "danger"));
			return new ResponseEntity<String>("Please Enter correct old password !!", HttpStatus.BAD_REQUEST);
		}
	}

}
