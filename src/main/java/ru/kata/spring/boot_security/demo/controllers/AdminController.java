package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private static final String REDIRECT = "redirect:/admin/allUsers";
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/allUsers")
    public String getPanel(ModelMap modelMap, Principal principal) {
        List<User> users = userService.getUsers();
        User authUser = userService.findByUsername(principal.getName());
        modelMap.addAttribute("user", authUser);
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("newUser", new User());
        modelMap.addAttribute("users_roles", roleService.getRoles());

        return "admin/allUsers";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return REDIRECT;
    }


}
