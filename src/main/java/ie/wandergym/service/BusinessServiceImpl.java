package ie.wandergym.service;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.entity.Business;
import ie.wandergym.domain.request.BusinessRequest;
import ie.wandergym.exception.DataNotFoundException;
import ie.wandergym.mapper.BusinessMapper;
import ie.wandergym.repository.BusinessRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    private static final BusinessMapper MAPPER = Mappers.getMapper(BusinessMapper.class);
    BusinessRepository repository;

    // class constructor
    public BusinessServiceImpl(BusinessRepository repository){
        this.repository = repository;
    }

    @Override
    public void create(BusinessRequest request) {
        Business business = MAPPER.requestToEntity(request);
        repository.save(business);
    }

    @Override
    public BusinessDto find(Long id) {
        Business business = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return MAPPER.entityToDto(business);
    }

    @Override
    public void delete(Long id) {
        Business business = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        repository.delete(business);
    }

    @Override
    public void update(Long id, BusinessRequest request) {
        Business business = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        business.setAddress(request.getAddress());
        business.setEmail(request.getEmail());
        business.setName(request.getName());
        business.setPhoneNumber(request.getPhoneNumber());
        repository.save(business);
    }
}
