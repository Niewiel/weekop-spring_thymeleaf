package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

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

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping(path = "/add")
    public String add(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
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
