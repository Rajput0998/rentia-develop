package com.rentia.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Address;
import com.rentia.services.AddressService;




@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
    private AddressService addressService;


	@RequestMapping("/home")
	public String home(Model model) {
		List<Address> cities = addressService.fetchAddressDetails(null, null); 
        model.addAttribute("cities", cities);
        return "index";
	}

	/*
	 * @RequestMapping("/signup") public String signup(Model model) {
	 * model.addAttribute("title", "Register - Smart Contact Manager");
	 * model.addAttribute("user", new User()); return "signup"; }
	 */

	/*
	 * //handler for custom login
	 * 
	 * @GetMapping("/signin") public String customLogin(Model model) {
	 * model.addAttribute("title","Login Page"); return "login"; }
	 */
}
