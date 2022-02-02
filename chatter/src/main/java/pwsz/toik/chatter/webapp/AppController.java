package pwsz.toik.chatter.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping(path = {"/","/index"})
    public String index(Model model){
        return "index";
    }
    @GetMapping(path = "/login")
    public String login(Model model, String error, String logout){

        if (error != null)
            model.addAttribute("error", "Incorrect login info");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    @GetMapping(path = "/registration")
    public String registration(Model model){
        return "registration";
    }
}
