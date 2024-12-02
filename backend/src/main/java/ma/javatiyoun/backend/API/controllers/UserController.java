package ma.javatiyoun.backend.API.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.javatiyoun.backend.Business.user.IUserServices;
import ma.javatiyoun.backend.Domain.entities.User;
import ma.javatiyoun.backend.Domain.exceptions.EmailValidationException;
import ma.javatiyoun.backend.Domain.exceptions.OtpNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserServices _userServices;

    public UserController(IUserServices userServices) {
        _userServices = userServices;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/inscription", consumes = APPLICATION_JSON_VALUE)
    public void inscription(@RequestBody User user) throws EmailValidationException {
        log.info("Creating User {}", user);
        _userServices.inscription(user);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/activation")
    public void inscription(@RequestBody Map<String, String> activation) throws OtpNotValidException {
        _userServices.activation(activation);
    }
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        return _userServices.listUsers();
    }
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public User searchUser(@RequestParam(required = false) String email) {
        return _userServices.searchUser(email);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Creating User {}", user);
        _userServices.updateUser(id, user);
    }
}
