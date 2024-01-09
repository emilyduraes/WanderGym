package ie.wandergym.service;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.request.BusinessRequest;

import java.util.List;

public interface BusinessService {
    void create(BusinessRequest request);
    BusinessDto find(Long id);
    BusinessDto findByEmail(String email);
    List<BusinessDto> findByName(String name);
    List<BusinessDto> findAllBusinesses();
    void delete(Long id);
    void update(Long id, BusinessRequest request);
    void deactivate(Long id);
}
