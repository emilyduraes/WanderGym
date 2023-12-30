package ie.wandergym.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // generates a number automatically for the field ID
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "phone_number")
    private Long phoneNumber;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    // class constructor
    public Business(){
        this.active = true;
    }

}
