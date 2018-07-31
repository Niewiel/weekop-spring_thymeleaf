package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.service.AuthorityService;
import pl.niewiel.weekopspring_thymeleaf.service.DiscoveryService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import java.net.URL;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/discovery")
public class DiscoveryController {

    private final UserService userService;
    private final DiscoveryService discoveryService;
    private final AuthorityService authorityService;

    @Autowired
    public DiscoveryController(UserService userService, AuthorityService authorityService, DiscoveryService discoveryService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.discoveryService = discoveryService;
    }

    @RequestMapping("/new")
    public String newDiscovery() {
        return "/discovery/new";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String inputName, String inputOpis,String inputUrl, Principal principal) {
        Discovery discovery;
        if (principal != null) {
            discovery=new Discovery(inputName, inputOpis, inputUrl, userService.getByUsername(principal.getName()));
            discovery.setDate(new Timestamp(new Date().getTime()));
            discoveryService.add(discovery);
            return "redirect:/";
        } else return "error";
    }
}
