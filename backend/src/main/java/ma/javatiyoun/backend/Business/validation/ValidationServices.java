package ma.javatiyoun.backend.Business.validation;

import ma.javatiyoun.backend.Business.notification.INotificationServices;
import ma.javatiyoun.backend.Domain.entities.User;
import ma.javatiyoun.backend.Domain.entities.Validation;
import ma.javatiyoun.backend.Domain.exceptions.OtpNotValidException;
import ma.javatiyoun.backend.Repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class ValidationServices implements IValidationServices{
    private final ValidationRepository _validationRepository;
    private final INotificationServices _notificationServices;

    public ValidationServices(ValidationRepository validationRepository, INotificationServices notificationServices) {
        _validationRepository = validationRepository;
        _notificationServices = notificationServices;
    }
    @Override
    public void saveValidation(User user) {
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creationDate = Instant.now();
        Instant expirationDate = creationDate.plus(10, ChronoUnit.MINUTES);
        validation.setCreationDate(creationDate);
        validation.setExpirationDate(expirationDate);
        Random random = new Random();
        int integerRandom = random.nextInt(999999);
        String otp = String.format("%06d", integerRandom);
        validation.setOtp(otp);
        _validationRepository.save(validation);
        _notificationServices.sendEmail(validation);
    }

    @Override
    public Validation findValidationByOtp(String otp) throws OtpNotValidException{
        return _validationRepository.findByOtp(otp).orElseThrow(()-> new OtpNotValidException("Otp not valid"));
    }
}
