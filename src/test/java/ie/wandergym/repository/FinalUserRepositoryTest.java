package ie.wandergym.repository;

import ie.wandergym.domain.entity.FinalUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Optional;

public class FinalUserRepositoryTest extends AbstractRepositoryTest<FinalUserRepository, FinalUser> {

    @Test
    void testSave(){
        int initialCount = repository.findAll().size();

        FinalUser user = buildEntity();
        repository.save(user);

        int finalCount = repository.findAll().size();
        Assertions.assertNotEquals(initialCount, finalCount);
    }

    @Test
    void testSaveMissingFields(){
        FinalUser user = buildEntity();
        user.setFullName(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () ->{
            repository.save(user);
        });
    }

    @Test
    void testFindById(){
        FinalUser user = buildEntity();
        repository.save(user);

        Optional<FinalUser> result = repository.findById(user.getId());
        Assertions.assertEquals(user.getFullName(), result.get().getFullName());
    }

    @Test
    void testFindByIdNoResult(){
        Optional<FinalUser> result = repository.findById(Long.MAX_VALUE);
        Assertions.assertFalse(result.isPresent());
    }

    @Override
    protected FinalUser buildEntity() {
        FinalUser user = new FinalUser();
        user.setFullName("full name");
        user.setAddress("1, Address, City, Country");
        user.setEmail("email@domain.com");
        user.setDob(LocalDate.parse("1989-05-21"));
        user.setMobileNumber(9999999L);
        return user;
    }
}
