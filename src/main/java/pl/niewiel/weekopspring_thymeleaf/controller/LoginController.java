package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class LoginController {

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
    public String register(){
        return "/register";
    }

    @PostMapping(path = "/register")
    public String add(@Valid User registerForm, BindingResult bindingResult, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        if (bindingResult.hasErrors()){
            return "/register";
        }
        Authority authority;
        authority = authorityService.getByAuthority("ROLE_USER");
        System.err.println(authority == null);
        if (authority == null) {
            authorityService.add(new Authority("ROLE_USER"));
            authority = authorityService.getByAuthority("ROLE_USER");
        }
        User user = new User(username, email, password);
        user.setAuthorities(Arrays.asList(authority));
        userService.addUser(user);
        System.out.println(user.getAuthorities());
        return "redirect:/login";
    }
}
