package pwsz.toik.chatter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Klasa kodowania hasla uzytkownika.
 */
@Configuration
public class PasswordEncoder {

    /**
     * Metoda tworzaca enkoder.
     *
     * @return enkoder BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
