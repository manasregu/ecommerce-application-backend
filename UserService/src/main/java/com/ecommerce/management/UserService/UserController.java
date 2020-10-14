package com.ecommerce.management.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private RestTemplate restTemplate;
	 
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView model = new ModelAndView();
		model.setViewName("render-product");
		return model;
	}
	
	@RequestMapping("/logout-success")
	public ModelAndView logoutSuccess() {
		ModelAndView model = new ModelAndView();
		model.setViewName("logout");
		return model;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView registerUser(Customer user) {
		service.registerUser(user);
		ModelAndView model = new ModelAndView();
		model.setViewName("signup-success");
		model.addObject("username", user.getName());
		return model;
	}
	
	@RequestMapping(value = "/getMail", method = RequestMethod.GET)
	public String getMailId(@RequestParam int uid) {
		return service.getMailId(uid);
	}

}
