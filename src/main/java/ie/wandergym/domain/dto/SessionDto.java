package ie.wandergym.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class SessionDto {
    @JsonInclude(JsonInclude.Include.NON_NULL) //includes field in json response only if not null
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long businessId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long dailyAttendance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long monthlyAttendance;
}
