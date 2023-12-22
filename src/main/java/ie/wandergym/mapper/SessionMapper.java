package ie.wandergym.mapper;

import ie.wandergym.domain.dto.SessionDto;
import ie.wandergym.domain.entity.Session;
import ie.wandergym.domain.request.SessionRequest;
import org.mapstruct.Mapper;

// interface to configure MapStruct dependency for class Business
// responsible to map the class and parse into other classes automatically via MapStruct plugin
@Mapper()
public interface SessionMapper {
    Session dtoToEntity(SessionDto sessionDto);
    SessionDto entityToDto(Session session);
    Session requestToEntity(SessionRequest request);
}
