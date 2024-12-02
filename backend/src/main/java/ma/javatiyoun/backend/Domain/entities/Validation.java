package ma.javatiyoun.backend.Domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "validations")
public class Validation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant creationDate;
    private Instant activationDate;
    private Instant expirationDate;
    @Column(name = "One_Time_Password")
    private String otp;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
