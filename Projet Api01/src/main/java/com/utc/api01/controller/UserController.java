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
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Role;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class UserController {

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

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("listUsers", this.userService.list());
        return "user";
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRole", roleService.list());
        model.addAttribute("url", "user/add");

        return "editUser";
    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User u) {
        ModelAndView model = new ModelAndView();

        model = validateUser(u, model, true);

        return model;
    }

    @RequestMapping("/admin/remove/{idUser}")
    public String removeUser(@PathVariable("idUser") int id) {

        this.userService.remove(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/edit/{idUser}")
    public String editUser(@PathVariable("idUser") int idUser, Model model) {
        User u = this.userService.getById(idUser);
        model.addAttribute("user", u);
        model.addAttribute("listRole", roleService.list());
        model.addAttribute("url", "saveEdit");
        return "editUser";
    }

    @RequestMapping(value = "/admin/edit/saveEdit", method = RequestMethod.POST)
    public String editAndSaveUser(@ModelAttribute("user") User u) {
        ModelAndView model = new ModelAndView();

        model = validateUser(u, model, false);

        return "redirect:/admin/users";
    }

    private ModelAndView validateUser(User u, ModelAndView model, boolean add) {
        model.setViewName("editUser");

        if (u.getEmail() == null || u.getFirstname() == null
                || u.getLastname() == null || u.getTelephone() == null
                || u.getPassword() == null) {
            model.addObject("error", "Vous devez remplir tous les champs");
        } else if (u.getPassword() != u.getConfirmation()) {
            model.addObject("error", "Les mots de passe ne correspondent pas");
        } else {
            u.setRole(this.roleService.getById(u.getRoleUser()));
            if (add) {
                u.setCreationDate(new SimpleDateFormat("YYYY-MM-DD")
                        .format(new Date()));
                this.userService.add(u);
                model.addObject("msg", "L'utilisateur " + u.getFirstname()
                        + " " + u.getLastname() + " a correctement été ajouté");
            } else {
                this.userService.update(u);
                model.addObject("msg", "L'utilisateur " + u.getFirstname()
                        + " " + u.getLastname() + " a correctement été modifié");
            }
            model.setViewName("admin");
        }
        return model;
    }
}
