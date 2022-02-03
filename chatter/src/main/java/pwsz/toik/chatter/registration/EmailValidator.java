package pwsz.toik.chatter.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * Klasa walidujaca adres e-mail.
 */
@Service
public class EmailValidator implements Predicate<String> {

    /**
     * Tester adresu e-mail.
     *
     * @param s e-mail uzytkownika
     * @return true / false
     */
    @Override
    public boolean test(String s) {
        //TODO REGEX FOR EMAIL
        return true;
    }
}
