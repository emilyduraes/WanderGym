package ie.wandergym.repository;

import ie.wandergym.domain.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository  extends JpaRepository<Business, Long> {

    // custom query to retrieve all business as defined by user search
    @Query("SELECT t FROM Business t WHERE lower(t.name) LIKE lower(concat('%', concat(:name, '%')))")
    Optional<List<Business>> findByName(@Param("name") String name);

}
