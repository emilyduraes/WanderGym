package ie.wandergym.service;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.request.FinalUserRequest;

public interface FinalUserService {
    void create(FinalUserRequest request);
    FinalUserDto find(Long id);
    FinalUserDto findByEmail(String email);
    void delete(Long id);
    void update(Long id, FinalUserRequest request);
}
