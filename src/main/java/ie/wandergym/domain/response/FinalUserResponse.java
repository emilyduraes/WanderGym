package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.FinalUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class FinalUserResponse {
    private FinalUserDto finalUser;

    // class constructor
    public  FinalUserResponse(FinalUserDto finalUser){
        this.finalUser = finalUser;
    }

}
