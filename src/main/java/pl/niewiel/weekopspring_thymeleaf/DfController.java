package pl.niewiel.weekopspring_thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DfController {



    @RequestMapping("/")
    public String index() {
        return "index";
    }




}
