package ie.wandergym.mapper;

import ie.wandergym.domain.dto.AuthDto;
import ie.wandergym.domain.entity.Auth;
import ie.wandergym.domain.request.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthenticationMapper {
    @Mapping(target = "authorities", ignore = true)
    Auth dtoToEntity(AuthDto dto);
    AuthDto entityToDto(Auth user);
    @Mapping(target = "authorities", ignore = true)
    Auth requestToEntity(AuthRequest request);
}
