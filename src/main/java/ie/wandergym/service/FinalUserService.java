package ie.wandergym.service;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.request.FinalUserRequest;

public interface FinalUserService {
    void createFinalUser(FinalUserRequest request);
    FinalUserDto findFinalUser(Long id);
    void deleteFinalUser(Long id);
}
