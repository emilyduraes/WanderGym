package ie.wandergym.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ie.wandergym.domain.entity.Business;
import ie.wandergym.domain.entity.FinalUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter // generates getters automatically for the class
@Setter // generates setters automatically for the class
public class AuthDto {
    @JsonInclude(JsonInclude.Include.NON_NULL) //includes field in json response only if not null
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Business business;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FinalUser user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<? extends GrantedAuthority> authorities;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String basicAuthorization;
}
