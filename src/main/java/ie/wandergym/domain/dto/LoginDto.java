package ie.wandergym.domain.dto;

import ie.wandergym.domain.enums.MsgLoginResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class LoginDto<T> {
    private HttpStatus responseCode;
    private MsgLoginResponse responseMessage;
    private T responseObject;

    public LoginDto(HttpStatus responseCode, MsgLoginResponse responseMessage, T responseObject) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    public LoginDto(HttpStatus responseCode, MsgLoginResponse responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
