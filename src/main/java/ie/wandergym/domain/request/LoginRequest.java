package ie.wandergym.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "LoginRequest", description = "Transport class for API authentication")
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class LoginRequest {
    @ApiModelProperty(name = "username")
    @NotEmpty(message = "The username (e-mail) cannot be null or empty")
    private String username;

    @ApiModelProperty(name = "password")
    @NotEmpty(message = "The password cannot be null or empty")
    private String password;
}
