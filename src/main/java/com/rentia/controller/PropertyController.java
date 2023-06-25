package com.rentia.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentia.models.Property;
import com.rentia.models.Rating;
import com.rentia.models.TntProperty;
import com.rentia.models.User;
import com.rentia.services.FileService;
import com.rentia.services.PropertyService;
import com.rentia.services.RatingService;
import com.rentia.services.UserService;

@Controller
public class PropertyController {
	
	@Autowired
    private PropertyService propertyService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image.property}")
	private String path;

	
	@PostMapping("/api/a1/property/add/postman")
	public ResponseEntity<Property> addPropertybyPostMan(@RequestBody Property property ) throws Exception {
		
		try{
			Property l_property = propertyService.addProperty(property);
			return new ResponseEntity<Property>(l_property, HttpStatus.OK);
         
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
	}
	
	
	 
	@PostMapping("/api/a1/property/add")
	public String registerProperty(@ModelAttribute Property property) throws Exception {
		// Perform any necessary processing of the uploaded images
        System.out.println("MPS" + property);
		// Save the property to the database
		Property savedProperty = propertyService.addProperty(property);

		// Redirect to a success page or display a success message

		return "redirect:/property/" + savedProperty.getId();
	}
	
	@RequestMapping("/api/a1/property/getProperty/{propertyID}")
	public String getProperty(@PathVariable Long propertyID, Model model) {
		Property property = propertyService.getPropertybyId(propertyID);
		System.out.println("property MPS : " + property);
		List<Property> propertyList = propertyService.getPropertybyCity(property.getAddress().getAdrid());
		model.addAttribute("property", property);
		model.addAttribute("propertyList", propertyList);
		System.out.println(model);
        return "space-detail";
    }
	
	/*
	 * @GetMapping("/getProperty/{propertyName}") public ResponseEntity<Property>
	 * getProperty(@PathVariable("propertyName") String propertyName) { Property
	 * l_property = propertyService.getPropertybyPropertyName(propertyName); if
	 * (l_property == null) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return new
	 * ResponseEntity<Property>(l_property, HttpStatus.OK); }
	 */
	
	@PutMapping("/api/a1/property/update/{pid}")
	public ResponseEntity<Property> updateProperty(@RequestBody String body, @PathVariable("pid") Long pid ) throws Exception {
		
		try{
			Property l_property = propertyService.updateProperty(body, pid);
			return new ResponseEntity<Property>(l_property, HttpStatus.OK);
         
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
	}
	
	@GetMapping("/api/a1/property/city/{addressID}")
	public ResponseEntity<List<Property>> getProperty(@PathVariable("addressID") Long addressID) {
		List<Property> l_property = propertyService.getPropertybyCity(addressID);
        if (l_property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<List<Property>>(l_property, HttpStatus.OK);
    }
	
	@PostMapping("/api/a1/property/post/image/upload/{propertyId}")
	public ResponseEntity<Property> uploadUserImage(@RequestParam(value = "displayImage", required = false) MultipartFile profileImageFile,
			@RequestParam(value = "propertyImages", required = false) List<MultipartFile> propertyImagesFile, @PathVariable Long propertyId)
			throws Exception {

		Property property = propertyService.getPropertybyId(propertyId);

		if (null != property.getDisplayImage()) {
			this.fileService.deleteImage(path, property.getDisplayImage());
		}

		String profileImageName = this.fileService.uploadImage(path, profileImageFile);

		List<String> docImageName = new ArrayList<>();
		
		for (MultipartFile file : propertyImagesFile) {
			String fileName = this.fileService.uploadImage(path, file);
			docImageName.add(fileName);
		}
		property.setDisplayImage(profileImageName);
		property.setPropertyImages(docImageName);
		System.out.println(property);
		Property updateProperty = propertyService.updateProperty(property, propertyId);
		return new ResponseEntity<Property>(updateProperty, HttpStatus.OK);

	}
	
	@GetMapping(value = "/api/a1/property/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}
	

}

