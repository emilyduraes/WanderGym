package ie.wandergym.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class SessionDto {
    private Long id;
    private Long businessId;
    private Long userId;
    private Long dailyAttendance;
    private Long monthlyAttendance;
}
