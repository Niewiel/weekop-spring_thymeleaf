package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;


import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class LoginController  {


    private final UserService userService;
    private final AuthorityService authorityService;

    @Autowired
    public LoginController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }


    // Login form
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping(path = "/add")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam String userName, @RequestParam String email, @RequestParam String password) {
        if (bindingResult.hasErrors()) {
            return "/register";
        } else {
            Authority authority;
            authority = authorityService.getByAuthority("ROLE_USER");
            System.err.println(authority == null);
            if (authority == null) {
                authorityService.add(new Authority("ROLE_USER"));
                authority = authorityService.getByAuthority("ROLE_USER");
            }
            User newUser = new User(userName, email, password);
            newUser.setAuthorities(Arrays.asList(authority));
            userService.addUser(newUser);
            System.out.println(newUser.getAuthorities());
            return "redirect:/login";
        }
    }


}
