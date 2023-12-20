package ie.wandergym.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@ApiModel(value = "FinalUserRequest", description = "Transport class for the Final User")
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class FinalUserRequest {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "fullName")
    @NotEmpty(message = "The fullName cannot be null or empty")
    private String fullName;

    @ApiModelProperty(name = "Date of Birth (dob)")
    @Past(message = "This is not a valid date of birth")
    private LocalDate dob;

    @ApiModelProperty(name = "email")
    @Email(message = "This is not a valid email")
    private String email;

    @ApiModelProperty(name = "address")
    @NotEmpty(message = "The address cannot be null or empty")
    private String address;

    @ApiModelProperty(name = "mobileNumber")
    @Positive(message = "Invalid mobile number number")
    private Long mobileNumber;
}
