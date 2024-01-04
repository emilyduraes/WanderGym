package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.AuthDto;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class AuthResponse {

    private AuthDto authentication;

    public AuthResponse(AuthDto authentication){
        this.authentication = authentication;
    }
}
