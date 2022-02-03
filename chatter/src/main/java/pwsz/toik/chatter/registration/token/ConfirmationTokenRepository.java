package pwsz.toik.chatter.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfejs wyszukiwania poprzez token aktywacyjny.
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {


    /**
     * Metoda wyszukujaca token.
     *
     * @param token token aktywacyjny
     * @return token
     */
    Optional<ConfirmationToken> findByToken(String token);
}
