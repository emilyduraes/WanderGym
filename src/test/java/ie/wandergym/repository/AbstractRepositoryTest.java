package ie.wandergym.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@SpringBootTest
public abstract class AbstractRepositoryTest<REPOSITORY extends JpaRepository<ENTITY, Long>, ENTITY> {

    @Autowired
    REPOSITORY repository;

    protected abstract ENTITY buildEntity();

}
