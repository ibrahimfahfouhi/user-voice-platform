package ma.javatiyoun.backend.Business.notification;

import ma.javatiyoun.backend.Domain.entities.Validation;

public interface INotificationServices {
    void sendEmail(Validation validation);
}
