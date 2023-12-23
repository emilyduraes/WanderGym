package ie.wandergym.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class FinalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // generates a number automatically for the field ID
    private Long id;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dob;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "mobile_number")
    private Long mobileNumber;

    @Column(nullable = false)
    private boolean active;

    // class constructor
    public FinalUser() {
        this.active = true;
    }
}
