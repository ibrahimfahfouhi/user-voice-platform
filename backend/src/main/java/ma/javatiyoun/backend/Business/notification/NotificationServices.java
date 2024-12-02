package ma.javatiyoun.backend.Business.notification;

import ma.javatiyoun.backend.Domain.entities.Validation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices implements INotificationServices{
    private final JavaMailSender _javaMailSender;

    public NotificationServices(JavaMailSender javaMailSender) {
        _javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Validation validation) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@javatiyoun.com");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("Your activation code");
        String notificationText = String.format(
                "Hello %s, <br /> your activation code is: %s. <br /> See you soon.",
                validation.getUser().getUsername(),
                validation.getOtp()
        );
        mailMessage.setText(notificationText);
        _javaMailSender.send(mailMessage);
    }
}
