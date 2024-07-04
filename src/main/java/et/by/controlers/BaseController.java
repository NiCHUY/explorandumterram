package et.by.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
    @GetMapping("/registration")
    public String registrationController() {
        return "registration";
    }

    @GetMapping("/login")
    public String loginController() {
        return "login";
    }

    @GetMapping("/lobby")
    public String lobbyController(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("username", username);
        return "lobby";
    }
}
