package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.BusinessDto;
import lombok.Getter;
import lombok.Setter;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class BusinessResponse {
    private BusinessDto business;

    public BusinessResponse(BusinessDto business){
        this.business = business;
    }
}
