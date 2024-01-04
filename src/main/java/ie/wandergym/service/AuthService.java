package ie.wandergym.service;

import ie.wandergym.domain.dto.LoginDto;
import ie.wandergym.domain.request.AuthRequest;
import ie.wandergym.domain.request.LoginRequest;

public interface AuthService {
    void register(AuthRequest request);
    LoginDto login(LoginRequest request) throws Exception;
    LoginDto logout();
}
