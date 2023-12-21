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
    public void create(FinalUserRequest request) {
        FinalUser user = MAPPER.requestToEntity(request);
        repository.save(user);
    }

    @Override
    public FinalUserDto find(Long id) {
        FinalUser user = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return MAPPER.entityToDto(user);
    }

    @Override
    public void delete(Long id) {
        FinalUser user = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        repository.delete(user);
    }

    @Override
    public void update(Long id, FinalUserRequest request) {
        FinalUser user = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setMobileNumber(request.getMobileNumber());
        repository.save(user);
    }
}
