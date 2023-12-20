package ie.wandergym.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class FinalUserDto {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String address;
    private Long mobileNumber;
}
