package controlador;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author miguel
 */
public class VerificationMailSender {

    private MailSender mailSender;

    /**
     * 
     * @param mailSender 
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    /**
     * 
     * @param from
     * @param to
     * @param subject
     * @param msg 
     */
    public void sendMail(String from, String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
