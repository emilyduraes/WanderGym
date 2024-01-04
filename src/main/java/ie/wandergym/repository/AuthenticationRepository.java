package ie.wandergym.repository;

import ie.wandergym.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Auth, Long> {

    Auth findByUsername(String username);
}
