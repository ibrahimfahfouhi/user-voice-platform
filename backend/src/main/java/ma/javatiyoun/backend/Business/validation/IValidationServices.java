package ma.javatiyoun.backend.Business.validation;

import ma.javatiyoun.backend.Domain.entities.User;
import ma.javatiyoun.backend.Domain.entities.Validation;
import ma.javatiyoun.backend.Domain.exceptions.OtpNotValidException;

public interface IValidationServices {
    void saveValidation(User user);
    Validation findValidationByOtp(String otp) throws OtpNotValidException;
}
