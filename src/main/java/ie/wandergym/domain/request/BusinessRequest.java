package ie.wandergym.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@ApiModel(value = "BusinessRequest", description = "Transport class for the Business")
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class BusinessRequest {

    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "name")
    @NotEmpty(message = "The name cannot be null or empty")
    private String name;

    @ApiModelProperty(name = "email")
    @Email(message = "This is not a valid email")
    private String email;

    @ApiModelProperty(name = "address")
    @NotEmpty(message = "The address cannot be null or empty")
    private String address;

    @ApiModelProperty(name = "phoneNumber")
    @Positive(message = "Invalid phone number number")
    private Long phoneNumber;
}
