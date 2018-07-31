package pl.niewiel.weekopspring_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.niewiel.weekopspring_thymeleaf.service.DiscoveryService;

@Controller
public class DfController {

    private final DiscoveryService discoveryService;

    @Autowired
    public DfController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("discoveryList", discoveryService.getAll());
        return "index";
    }


}
