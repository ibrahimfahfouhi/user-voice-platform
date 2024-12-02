package ma.javatiyoun.backend.Repository;

import ma.javatiyoun.backend.Domain.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
    Optional<Validation> findByOtp(String otp);
}
