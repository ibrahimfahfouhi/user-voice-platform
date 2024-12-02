package ma.javatiyoun.backend.Repository;

import ma.javatiyoun.backend.Domain.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
}
