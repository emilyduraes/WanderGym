package ie.wandergym.mapper;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.entity.Business;
import ie.wandergym.domain.request.BusinessRequest;
import org.mapstruct.Mapper;

// interface to configure MapStruct dependency for class Business
// responsible to map the class and parse into other classes automatically via MapStruct plugin
@Mapper()
public interface BusinessMapper {
    Business dtoToEntity(BusinessDto businessDto);
    BusinessDto entityToDto(Business business);
    Business requestToEntity(BusinessRequest request);
}
