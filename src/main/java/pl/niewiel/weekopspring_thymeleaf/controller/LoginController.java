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
import java.util.Collections;

@Controller
public class LoginController implements UserDetailsService, Serializable {


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

    @PostMapping(path = "/register")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
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
            User newUser = new User(username, email, password);
            user.setAuthorities(Collections.singletonList(authority));
            userService.addUser(newUser);
            System.out.println(user.getAuthorities());
            return "redirect:/login";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("") || username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s is invalid!", username));
        }
        User login = userService.getByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return null;
    }
}
