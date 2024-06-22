package et.by.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/registration")
    public String sayHello() {
        return "registration";
    }
}
