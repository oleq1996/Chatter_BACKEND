package pwsz.toik.chatter.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pwsz.toik.chatter.appuser.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Klasa odpowiadajaca za token aktywacyjny.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_seq",
            sequenceName = "confirmation_token_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_seq"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
            name = "app_user_id")
    private AppUser appUser;

    /**
     * Konstruktor tokeny aktywacyjnego.
     *
     * @param token token aktywacyjny
     * @param createdAt czas utworzenia
     * @param expiresAt czas przedawnienia
     * @param appUser przypisane konto uzytkownika
     */
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
