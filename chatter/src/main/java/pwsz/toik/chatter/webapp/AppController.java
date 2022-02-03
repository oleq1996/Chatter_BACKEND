package pwsz.toik.chatter.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Kontroler aplikacji.
 */
@Controller
public class AppController {

    /**
     * Przekierowanie na strone glowna komunikatora.
     *
     * @param model model
     * @return strona glowna komunikatora
     */
    @GetMapping(path = {"/","/index"})
    public String index(Model model){
        return "index";
    }

    /**
     * Przekierowanie do formularza logowania.
     *
     * @param model model
     * @param error komunikat niepowodzenia przy logowaniu
     * @param logout komunikat wylogowania
     * @return formularz logowania
     */
    @GetMapping(path = "/login")
    public String login(Model model, String error, String logout){

        if (error != null)
            model.addAttribute("error", "Incorrect login info");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /**
     * Przekierowanie do formularza rejestracyjnego.
     *
     * @param model model
     * @return formularz rejestracji
     */
    @GetMapping(path = "/registration")
    public String registration(Model model){
        return "registration";
    }
}
