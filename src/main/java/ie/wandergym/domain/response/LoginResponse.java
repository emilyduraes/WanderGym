package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.LoginDto;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class LoginResponse {

    private LoginDto login;

    public LoginResponse(LoginDto login){
        this.login = login;
    }
}
