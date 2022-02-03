package pwsz.toik.chatter.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa wiadomosci w pokojach rozmow.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String from;
    private String text;
}
