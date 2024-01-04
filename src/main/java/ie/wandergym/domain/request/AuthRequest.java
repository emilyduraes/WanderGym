package ie.wandergym.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(value = "AuthRequest", description = "Transport class for Auth entity")
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class AuthRequest {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "username")
    @NotEmpty(message = "The username (e-mail) cannot be null or empty")
    @Email(message = "This is not a valid email")
    private String username;

    @ApiModelProperty(name = "password")
    @NotEmpty(message = "The password cannot be null or empty")
    @Size(min = 6, max = 90, message = "A password should have minimum 6 characters")
    private String password;

    @ApiModelProperty(name = "role", example = "ROLE_USER, ROLE_BUSINESS or ROLE_SYSADMIN")
    @NotEmpty(message = "The authorities cannot be null or empty")
    private String role;
}
