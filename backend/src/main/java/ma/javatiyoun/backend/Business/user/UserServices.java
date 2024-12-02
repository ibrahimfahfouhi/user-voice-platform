package ma.javatiyoun.backend.Business.user;

import lombok.extern.slf4j.Slf4j;
import ma.javatiyoun.backend.Business.notification.INotificationServices;
import ma.javatiyoun.backend.Business.validation.IValidationServices;
import ma.javatiyoun.backend.Domain.entities.User;
import ma.javatiyoun.backend.Domain.entities.Validation;
import ma.javatiyoun.backend.Domain.enums.Role;
import ma.javatiyoun.backend.Domain.exceptions.EmailValidationException;
import ma.javatiyoun.backend.Domain.exceptions.OtpNotValidException;
import ma.javatiyoun.backend.Repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServices implements IUserServices{
    private final IUserRepository _userRepository;
    private final BCryptPasswordEncoder _passwordEncoder;
    private final IValidationServices _validationServices;

    public UserServices(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder, IValidationServices validationServices, INotificationServices notificationServices) {
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _validationServices = validationServices;
    }

    @Override
    public void inscription(User user) throws EmailValidationException{
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new EmailValidationException("Email not valid");
        }
        Optional<User> userInBDD = _userRepository.findByEmail(user.getEmail());
        if (userInBDD.isPresent()) {
            log.info("user already exist");
        }
        String passwordEncrypted = _passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setRole(Role.USER);
        _userRepository.save(user);
        _validationServices.saveValidation(user);
    }

    @Override
    public List<User> listUsers() {
        return _userRepository.findAll();
    }

    @Override
    public User searchUser(String email) {
        Optional<User> userInBDD = _userRepository.findByEmail(email);
        if (userInBDD.isPresent()) {
            return userInBDD.get();
        }
        return null;
    }

    @Override
    public void updateUser(Long id, User user) {
        Optional<User> userInBDD = _userRepository.findById(id);
        if (userInBDD.isPresent()) {
            userInBDD.get().setUserName(user.getUsername());
            userInBDD.get().setEmail(user.getEmail());
            userInBDD.get().setRole(user.getRole());
            _userRepository.save(userInBDD.get());
        }
    }

    @Override
    public void activation(Map<String, String> activation) throws OtpNotValidException {
        Validation validation = _validationServices.findValidationByOtp(activation.get("otp"));
        if (Instant.now().isAfter(validation.getExpirationDate())) {
            throw new OtpNotValidException("your code has been expired");
        }
        validation.getUser().setActive(true);
        _userRepository.save(validation.getUser());
    }
}
