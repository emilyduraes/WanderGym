package ie.wandergym.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@ApiModel(value = "SessionRequest", description = "Transport class for the Session")
@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class SessionRequest {

    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "businessId")
    @Positive(message = "Invalid business id number number")
    private Long businessId;

    @ApiModelProperty(name = "userId")
    @Positive(message = "Invalid user id number number")
    private Long userId;

    @ApiModelProperty(name = "startTime")
    private LocalDateTime startTime;

}
