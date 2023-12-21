package ie.wandergym.mapper;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.entity.FinalUser;
import ie.wandergym.domain.request.FinalUserRequest;
import org.mapstruct.Mapper;

// interface to configure MapStruct dependency for class Final User
// responsible to map the class and parse into other classes automatically via MapStruct plugin
@Mapper()
public interface FinalUserMapper {
    FinalUser dtoToEntity(FinalUserDto finalUserDto);
    FinalUserDto entityToDto(FinalUser finalUser);
    FinalUser requestToEntity(FinalUserRequest request);
}
