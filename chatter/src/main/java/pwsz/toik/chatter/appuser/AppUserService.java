package pwsz.toik.chatter.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pwsz.toik.chatter.registration.token.ConfirmationToken;
import pwsz.toik.chatter.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Klasa sluzaca do obslugi konta uzytkownika.
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    private final static String EMAIL_ALREADY_TAKEN = "Email %s is already taken";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * Loguje uzytkownika przy uzyciu jego adresu e-mail.
     *
     * @param email adres e-mail uzytkownika
     * @return konto uzytkownika
     * @throws UsernameNotFoundException blad zwracany w sytuacji, gdy nie znaleziono uzytkownika o podanym adresie e-mail
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    /**
     * Metoda sluzaca do rejestracji uzytkownika.
     *
     * @param appUser przyjmuje obiekt AppUser, posiadajacy dane uzytkownika
     * @return token uzytkownika wykorzystywany w celu aktywacji konta
     */
    public String signUpUser(AppUser appUser){

        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if(userExists){
            throw new IllegalStateException(String.format(EMAIL_ALREADY_TAKEN,appUser.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    /**
     * Metoda sluzaca do aktywacji konta.
     *
     * @param email przyjmuje adres e-mail uzytkownika
     */
    public void enableAppUser(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(()->
                new IllegalStateException(String.format(USER_NOT_FOUND,email)));

        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }
}
