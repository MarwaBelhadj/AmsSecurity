package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sip.ams.entities.User;
import com.sip.ams.repositories.UserRepository;
@Controller
@RequestMapping("/accounts/")
public class AccountController {


	
		private final UserRepository userRepository;

		@Autowired
		public AccountController(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		@GetMapping("list")
	    public String listUsers(Model model) {
	    	
	    	List<User> users = (List<User>) userRepository.findAll();
	    	long nbr =  userRepository.count();
	    	if(users.size()==0)
	    		users = null;
	        model.addAttribute("users", users);
	        model.addAttribute("nbr", nbr);
	        return "user/listUsers";
	    }
		
		@GetMapping("enable/{id}")
		//@ResponseBody
	    public String enableUserAcount(@PathVariable ("id") int id) {
	    	
			
			 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		     user.setActive(1);
		     userRepository.save(user);
	    	return "redirect:../list";
	    }
		
		@GetMapping("disable/{id}")
		//@ResponseBody
		public String disableUserAcount(@PathVariable ("id") int id) {
	    	
			 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		     user.setActive(0);
		     userRepository.save(user);
	    	return "redirect:../list";
	    }
	    
}
