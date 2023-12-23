package ie.wandergym.repository;

import ie.wandergym.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long>  {

    // custom query to retrieve gym's daily attendance
    @Query("SELECT COUNT(business_id) as attendance FROM Session s " +
            "WHERE day(start_time) = day(now()) and business_id = :businessId")
    Long countByBusinessId_daily(@Param("businessId") Long businessId);

    // custom query to retrieve gym's daily attendance
    @Query("SELECT COUNT(business_id) as attendance FROM Session s " +
            "WHERE MONTH(start_time) = MONTH(now()) and business_id = :businessId")
    Long countByBusinessId_monthly(@Param("businessId") Long businessId);
}