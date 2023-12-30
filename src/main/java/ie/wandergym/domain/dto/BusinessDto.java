package ie.wandergym.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class BusinessDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Long phoneNumber;
    private String description;
    private String type;
    private boolean active;
}
