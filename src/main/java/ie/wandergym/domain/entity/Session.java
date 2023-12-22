package ie.wandergym.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // generates a random number automatically for the field ID
    private Long id;

    @Column(nullable = false, name = "business_id")
    private Long businessId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

}
