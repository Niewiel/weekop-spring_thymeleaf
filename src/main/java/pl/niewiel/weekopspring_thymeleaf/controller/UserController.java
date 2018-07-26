package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping(path = "/add")
    public String add(@RequestParam String username,@RequestParam String email,@RequestParam String password){
        userService.addUser(new User(username,email,password));
        return "redirect:/user/index";
    }
}
