package ie.wandergym.service;

import ie.wandergym.domain.request.SessionRequest;

public interface SessionService {
    void start(SessionRequest request);
}
