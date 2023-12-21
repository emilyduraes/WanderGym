package ie.wandergym.repository;

import ie.wandergym.domain.entity.Business;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

public class BusinessRepositoryTest  extends AbstractRepositoryTest<BusinessRepository, Business> {

    @Test
    void testSave(){
        int initialCount = repository.findAll().size();

        Business business = buildEntity();
        repository.save(business);

        int finalCount = repository.findAll().size();
        Assertions.assertNotEquals(initialCount, finalCount);
    }

    @Test
    void testSaveMissingFields(){
        Business business = buildEntity();
        business.setName(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () ->{
            repository.save(business);
        });
    }

    @Test
    void testFindById(){
        Business business = buildEntity();
        repository.save(business);

        Optional<Business> result = repository.findById(business.getId());
        Assertions.assertEquals(business.getName(), result.get().getName());
    }

    @Test
    void testFindByIdNoResult(){
        Optional<Business> result = repository.findById(Long.MAX_VALUE);
        Assertions.assertFalse(result.isPresent());
    }

    @Override
    protected Business buildEntity() {
        Business business = new Business();
        business.setName("Gym name");
        business.setAddress("1, Address, City, Country");
        business.setEmail("gym_email@domain.com");
        business.setPhoneNumber(9999999L);
        return business;
    }
}
