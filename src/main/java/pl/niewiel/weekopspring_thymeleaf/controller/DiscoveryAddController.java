package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.DiscoveryService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import java.security.Principal;

@Controller
public class DiscoveryAddController {

    private final UserService userService;
    private final DiscoveryService discoveryService;
    private final AuthorityService authorityService;

    @Autowired
    public DiscoveryAddController(UserService userService, AuthorityService authorityService, DiscoveryService discoveryService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.discoveryService = discoveryService;
    }

    @RequestMapping("/new")
    public String newDiscovery(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        return "/new";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@PathVariable String name, @PathVariable String description, @PathVariable String url, Principal principal) {
        if (principal != null) {
            discoveryService.add(new Discovery(name, description, url, userService.getByUsername(principal.getName())));
            return "redirect:/index";
        } else return "error";
    }
}
