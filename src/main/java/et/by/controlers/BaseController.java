package et.by.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/starting")
    public String startingController() {
        return "welcome_page";
    }

    @GetMapping("/registration")
    public String registrationController() {
        return "registration";
    }

    @GetMapping("/login")
    public String loginController() {
        return "login";
    }

    @GetMapping("/lobby")
    public String lobbyController() {
        return "lobby";
    }
}
