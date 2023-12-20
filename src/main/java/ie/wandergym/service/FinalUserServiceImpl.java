package ie.wandergym.service;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.entity.FinalUser;
import ie.wandergym.domain.request.FinalUserRequest;
import ie.wandergym.exception.DataNotFoundException;
import ie.wandergym.mapper.FinalUserMapper;
import ie.wandergym.repository.FinalUserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class FinalUserServiceImpl implements FinalUserService {

    private static final FinalUserMapper MAPPER = Mappers.getMapper(FinalUserMapper.class);
    FinalUserRepository repository;

    // class constructor
    public FinalUserServiceImpl(FinalUserRepository repository){
        this.repository = repository;
    }

    @Override
    public void createFinalUser(FinalUserRequest request) {
        FinalUser finalUser = MAPPER.requestToEntity(request);
        repository.save(finalUser);
    }

    @Override
    public FinalUserDto findFinalUser(Long id) {
        FinalUser finalUser = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return MAPPER.entityToDto(finalUser);
    }
}
