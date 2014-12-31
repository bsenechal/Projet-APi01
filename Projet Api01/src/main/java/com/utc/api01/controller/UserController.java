package com.utc.api01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utc.api01.model.User;
import com.utc.api01.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUsers());
		return "user";
	}
	
	@RequestMapping(value= "/admin/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User u){
		
		this.userService.addUser(u);
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping("/admin/remove/{login}")
    public String removeUser(@PathVariable("login") String login){
		
        this.userService.removeUser(login);
        return "redirect:/admin/users";
    }
 
    @RequestMapping("/admin/edit/{login}")
    public String editUser(@PathVariable("login") String login, Model model){
		model.addAttribute("user", this.userService.fingByUsername(login));
        return "editUser";
    }
	
    @RequestMapping(value= "/admin/saveEdit", method = RequestMethod.POST)
    public String editAndSaveUser(@ModelAttribute("user") User u){
    	this.userService.updateUser(u);
        return "redirect:/admin/users";
    }
}
