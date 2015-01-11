package com.utc.api01.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class UserController {
	
	private GeneriqueService<User> userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(GeneriqueService<User> us){
		this.userService = us;
	}
	
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.list());
		return "user";
	}
	
	@RequestMapping(value= "/admin/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User u){
		u.setCreationDate(new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		this.userService.add(u);
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping("/admin/remove/{idUser}")
    public String removeUser(@PathVariable("idUser") int id){
		
        this.userService.remove(id);
        return "redirect:/admin/users";
    }
 
    @RequestMapping("/admin/edit/{idUser}")
    public String editUser(@PathVariable("idUser") int idUser, Model model){
		model.addAttribute("user", this.userService.getById(idUser));
        return "editUser";
    }
	
    @RequestMapping(value= "/admin/saveEdit", method = RequestMethod.POST)
    public String editAndSaveUser(@ModelAttribute("user") User u){
    	this.userService.update(u);
        return "redirect:/admin/users";
    }
}
