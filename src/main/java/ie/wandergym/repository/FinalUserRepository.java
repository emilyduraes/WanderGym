package ie.wandergym.repository;

import ie.wandergym.domain.entity.FinalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinalUserRepository extends JpaRepository<FinalUser, Long> {
    // custom query to retrieve user by email
    Optional<FinalUser> findByEmail(@Param("email") String email);
}
