package ma.javatiyoun.backend.Domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.javatiyoun.backend.Domain.enums.FeedbackType;
@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Feedbacks")
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private FeedbackType type;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;
}
