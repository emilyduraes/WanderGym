package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.SessionDto;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class SessionResponse {
    private SessionDto session;

    public SessionResponse(SessionDto session){
        this.session = session;
    }
}
