package ie.wandergym.service;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.request.BusinessRequest;

public interface BusinessService {
    void create(BusinessRequest request);
    BusinessDto find(Long id);
    void delete(Long id);
    void update(Long id, BusinessRequest request);
}
