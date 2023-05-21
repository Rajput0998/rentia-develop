package com.rentia.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentia.models.UserAddress;
import com.rentia.models.User;
import com.rentia.services.FileService;
import com.rentia.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/u1/user")
public class UserController 
{
    	
	@Autowired
    private UserService userService;	
	
	@Autowired
    private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		
		try{
			System.out.println("mukul1" + user.getAdress().size());
			User l_user = userService.registerUser(user);
			String s="Hi  " + l_user.getAdress().size() + "  your Registration Process successfully completed. Now Please Login to Continue";
			return new ResponseEntity<User>(l_user, HttpStatus.OK);
         
		}
		catch(Exception ex){
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
	
	// post image upload

		@PostMapping("/post/image/upload/{userId}")
		public ResponseEntity<User> uploadUserImage(@RequestParam("selfImage") MultipartFile profileImageFile, @RequestParam("documentImage") List<MultipartFile> docImageFile,
				@PathVariable Long userId) throws IOException {

			User user = userService.getUserbyUserId(userId);
			
			String profileImageName = this.fileService.uploadImage(path, profileImageFile);
			
			List<String> docImageName = new ArrayList<>();
			for (MultipartFile file : docImageFile) {
	            String fileName =  this.fileService.uploadImage(path, file);
	            docImageName.add(fileName);
	        }
			
			user.setSelfImage(profileImageName);
			user.setAadharImage(docImageName);
			 
			
			User updateUser = userService.updateUser(user, userId);
			return new ResponseEntity<User>(updateUser, HttpStatus.OK);

		}
		
		//method to serve files
	    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;

	    }
}


	


