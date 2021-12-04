package pwsz.toik.chatter.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OutputMessage {

    private String from;
    private String message;
    private String topic;
    private Date time = new Date();


    public OutputMessage(String from, String message, String topic) {
        this.from = from;
        this.message = message;
        this.topic = topic;
    }


}
