package com.utc.api01.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Role;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class UserController {
    private static final String JSP_USER = "user";
    private static final String REDIRECT_EDITUSER = "editUser";
    private static final String REDIRECT_ADMIN = "admin";
    private static final String REDIRECT_USERS = "users";
    private static final String MSG_ADD_SUCCESS = "L'utilisateur a correctement été ajouté.";
    private static final String MSG_EDIT_SUCCESS = "L'utilisateur a correctement été modifié.";
    private static final String MSG_SUPPR_SUCCESS = "L'utilisateur a correctement été supprimé.";
    private GeneriqueService<User> userService;
    private GeneriqueService<Role> roleService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(GeneriqueService<User> us) {
        this.userService = us;
    }

    @Autowired(required = true)
    @Qualifier(value = "roleService")
    public void setRoleService(GeneriqueService<Role> r) {
        this.roleService = r;
    }

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("listUsers", this.userService.list());
        return JSP_USER;
    }

    @RequestMapping(value = "/admin/user/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRole", roleService.list());
        return REDIRECT_EDITUSER;
    }

    @RequestMapping("/admin/user/remove/{idUser}")
    public ModelAndView removeUser(@PathVariable("idUser") int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        model.setViewName(JSP_USER);
        this.userService.remove(id);
        return model;
    }

    @RequestMapping("/admin/user/edit/{idUser}")
    public String editUser(@PathVariable("idUser") int idUser, Model model) {
        User u = this.userService.getById(idUser);
        model.addAttribute("user", u);
        model.addAttribute("listRole", roleService.list());
        return REDIRECT_EDITUSER;
    }
    
    @RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("user") User u, BindingResult result) {
        ModelAndView model = new ModelAndView();
       
        if (result.hasErrors()) {
            if (u.getIdUser() != 0){
                model.addObject("user", u);
            }
            model.addObject("listRole", roleService.list());
            model.setViewName(REDIRECT_EDITUSER);
        } else {
            model.setViewName(REDIRECT_ADMIN);
            u.setRole(this.roleService.getById(u.getRoleUser()));
            
            if (u.getIdUser() != 0) {
                model.addObject("msg", MSG_EDIT_SUCCESS);
                this.userService.update(u);
            } else {
                u.setCreationDate(new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
                model.addObject("msg", MSG_ADD_SUCCESS);
                this.userService.add(u);
            }
        }
        return model;
    }
}
