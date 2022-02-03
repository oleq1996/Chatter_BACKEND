package pwsz.toik.chatter.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Klasa odpowiadajaca za usluge mailowa.
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final static String FAILED_TO_SEND_EMAIL = "Failed to send email";
    private final static String TOPIC = "Confirm your email";
    private final static String FROM = "chatterappbot@gmail.com";

    private final JavaMailSender mailSender;

    /**
     * Metoda uzywana do wysylania wiadomosci.
     *
     * @param to adresat
     * @param email nadawca
     */
    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject(TOPIC);
            helper.setFrom(FROM);
            mailSender.send(mimeMessage);

        } catch(MessagingException e){
            LOGGER.error(FAILED_TO_SEND_EMAIL);
            throw new IllegalStateException(FAILED_TO_SEND_EMAIL);
        }
    }
}
