package ma.javatiyoun.backend.Business.user;

import ma.javatiyoun.backend.Domain.entities.User;
import ma.javatiyoun.backend.Domain.exceptions.EmailValidationException;
import ma.javatiyoun.backend.Domain.exceptions.OtpNotValidException;

import java.util.List;
import java.util.Map;

public interface IUserServices {
    void inscription(User user) throws EmailValidationException;

    List<User> listUsers();

    User searchUser(String email);

    void updateUser(Long id, User user);

    void activation(Map<String, String> activation) throws OtpNotValidException;
}
