package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.DiscoveryService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {

    private final DiscoveryService discoveryService;
    private final UserService userService;

    @Autowired
    public UserController(DiscoveryService discoveryService, UserService userService) {
        this.discoveryService = discoveryService;
        this.userService = userService;
    }

    @RequestMapping("/userPage")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("discoveryList", discoveryService.findDiscoveryByUser(userService.getByUsername(authentication.getName())));
        return "/user/userPage";
    }


}
