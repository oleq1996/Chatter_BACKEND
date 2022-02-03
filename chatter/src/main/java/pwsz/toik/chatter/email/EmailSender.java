package pwsz.toik.chatter.email;

/**
 * Interfejs uzywany do wysylania wiadomosci e-mail w celu aktywacji konta.
 */
public interface EmailSender {
    /**
     * Wysylanie wiadomosci.
     *
     * @param to adresat
     * @param email nadawca
     */
    void send(String to, String email);
}
