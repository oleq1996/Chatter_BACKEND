package pwsz.toik.chatter.registration.token;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Klasa obslugujaca tokeny aktywacyjne.
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService  {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final static String TOKEN_NOT_FOUND = "Token not found";

    /**
     * Metoda zapisujaca token aktywaycyjny.
     *
     * @param confirmationToken token aktywacyjny
     */
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }


    /**
     * Getter dla tokenu aktywacyjnego.
     *
     * @param token token aktywacyjny
     * @return token
     */
    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(()->
                        new IllegalStateException(TOKEN_NOT_FOUND));
    }

    /**
     * Setter dla daty uzycia tokenu.
     *
     * @param token token aktywacyjny
     */
    public void setConfirmedAt(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElseThrow(()->
                new IllegalStateException(TOKEN_NOT_FOUND));

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

    }
}
