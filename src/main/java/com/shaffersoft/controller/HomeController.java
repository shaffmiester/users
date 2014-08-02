package com.shaffersoft.controller;

import com.shaffersoft.User;
import com.shaffersoft.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(){
        userService = new UserService();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model m) {
        m.addAttribute("descriptions", userService.getAllowableDescription());
        return "newUser";
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public String edit(Model m, @PathVariable String id) {
        m.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String loadUsers(Model m) {
        List<User> users = userService.getAllUsers();
        m.addAttribute("name", "Users Go Here");
        m.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public String addUser(Model m, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("descriptions") List<String> descriptions) {
        userService.addUser(name, email, descriptions);
        m.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public String loadUser(Model m, @PathVariable String id) {
        m.addAttribute("id", id);
        return "user";
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.POST)
    public String editUser(Model m, @PathVariable String id) {
        m.addAttribute("id", id);
        System.out.println("YEAAHH... EDITING USER");
        return "users";
    }
}
