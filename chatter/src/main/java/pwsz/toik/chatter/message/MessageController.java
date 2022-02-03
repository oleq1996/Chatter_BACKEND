package pwsz.toik.chatter.message;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Kontroler uzywany do wysylania wiadomosci w pokojach rozmow.
 */
@Controller
public class MessageController {

    /**
     * Metoda uzywana do wysylania wiadomosci w pokojach rozmow.
     *
     * @param topic temat wiadomosci
     * @param message tresc wiadomosci
     * @return wyslana nowa wiadomosc
     * @throws Exception blad wysylania wiadomosci
     */
    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/messages")
    public OutputMessage send(
            @DestinationVariable("topic") String topic, Message message) throws Exception {
        return new OutputMessage(message.getFrom(), message.getText(), topic);
    }
}
