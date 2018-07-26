package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthorityService authorityService;

    @Autowired
    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }


    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping(path = "/add")
    public String add(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        Authority authority;
        authority=authorityService.getByAuthority("USER");
        if (authority==null){
            authorityService.add(new Authority("USER"));
        }
        User user=new User(username,email,password);
        user.setAuthorities(Collections.singletonList(authority));
        userService.addUser(user);
        return "redirect:/user/index";
    }
}
