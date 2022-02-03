package pwsz.toik.chatter.registration;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler rejestracji nowych kont.
 */
@RestController
@RequestMapping(path="/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    /**
     * Metoda odpowiadajaca za rejestracje konta.
     *
     * @param request request
     * @return usluga rejestracji
     */
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    /**
     * Metoda aktywujaca konto.
     *
     * @param token token aktywacyjny
     * @return token potwierdzajacy
     */
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }



}

