package pwsz.toik.chatter.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * Klasa przechowujaca dane na temat konta uzytkownika.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    /**
     * Identyfikator uzytkownika.
     */
    @Id
    @SequenceGenerator(
            name = "app_user_seq",
            sequenceName = "app_user_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_seq"
    )
    private Long id;
    /**
     * Nazwa uzytkownika.
     */
    private String username;
    /**
     * Adres e-mail uzytkownika.
     */
    private String email;
    /**
     * Haslo uzytkownika.
     */
    private String password;
    /**
     * Rola uzytkownika.
     */
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    /**
     * Czy konto zostalo zablokowane.
     */
    private Boolean locked = false;
    /**
     * Czy konto zostalo aktywowane.
     */
    private Boolean enabled = false;

    /**
     * Konstruktor klasy AppUser
     *
     * @param username oznacza nazwe uzytkownika
     * @param email oznacza adres e-mail uzytkownika
     * @param password oznacza haslo uzytkownika
     * @param appUserRole oznacza role, jaka jest przydzielana uzytkownikowi ( przyjmuje wartosci: USER / ADMIN )
     */
    public AppUser(String username, String email, String password, AppUserRole appUserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    /**
     * Getter zwracajacy liste zautoryzowanych uzytkownikow.
     *
     * @return zwraca kolekcje
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    /**
     * Getter zwracajacy haslo.
     *
     * @return haslo
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Getter zwracajacy nazwe uzytkownika.
     *
     * @return nazwa
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Zwraca informacje na temat przedawnienia konta.
     *
     * @return true / false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // for now
    }

    /**
     * Zwraca informacje na temat zablokowania konta.
     *
     * @return true / false
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * Zwraca informacje na temat przedawnienia danych logowania.
     *
     * @return true / false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Zwrocona wartosc oznacza, czy konto zostalo aktywowane, czy tez nie.
     *
     * @return true / false
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
