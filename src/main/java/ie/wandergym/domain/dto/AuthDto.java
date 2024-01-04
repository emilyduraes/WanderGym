package ie.wandergym.domain.dto;

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
    private Long id;
    private String username;
    private String password;
    private String role;
    private Business business;
    private FinalUser user;
    @JsonProperty("basic_authorization")
    private Collection<? extends GrantedAuthority> authorities;
    private String basicAuthorization;
}
