package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

@Controller
public class AddController {

    private final UserService userService;

    private final AuthorityService authorityService;

    @Autowired
    public AddController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }




}
