package ie.wandergym.domain.response;

import ie.wandergym.domain.dto.BusinessDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class BusinessListResponse {
    private List<BusinessDto> businesses;

    public BusinessListResponse(List<BusinessDto> businesses) {
        this.businesses = businesses;
    }

}
