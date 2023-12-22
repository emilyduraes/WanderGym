package ie.wandergym.service;

import ie.wandergym.domain.entity.Session;
import ie.wandergym.domain.request.SessionRequest;
import ie.wandergym.exception.DataNotFoundException;
import ie.wandergym.mapper.SessionMapper;
import ie.wandergym.repository.BusinessRepository;
import ie.wandergym.repository.FinalUserRepository;
import ie.wandergym.repository.SessionRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private static final SessionMapper MAPPER = Mappers.getMapper(SessionMapper.class);
    SessionRepository repository;
    BusinessRepository businessRepository;
    FinalUserRepository userRepository;

    // class constructor
    public SessionServiceImpl(SessionRepository repository, BusinessRepository businessRepository,
                              FinalUserRepository userRepository){
        this.repository = repository;
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void start(SessionRequest request) {
        // throws exception if ids not found
        checkId(request.getUserId(), request.getBusinessId());

        // continues if ids exists in the db
        Session session = MAPPER.requestToEntity(request);
        session.setStartTime(LocalDateTime.now());
        repository.save(session);
    }

    // method to check if the user and business exists in the database
    private void checkId(Long userId, Long businessId){
        businessRepository.findById(businessId).orElseThrow(() -> new DataNotFoundException(businessId, "Business"));
        userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(userId, "Final User"));
    }
}
