package pwsz.toik.chatter.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Interfejs sluzacy do wyszukiwania uzytkownika po jego adresie e-mail.
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    /**
     * Metoda wyszukujaca adres e-mail.
     *
     * @param email adres e-mail
     * @return adres e-mail
     */
    Optional<AppUser> findByEmail(String email);
}

