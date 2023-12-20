package ie.wandergym.repository;

import ie.wandergym.domain.entity.FinalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalUserRepository extends JpaRepository<FinalUser, Long> {
}
