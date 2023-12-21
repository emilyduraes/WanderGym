package ie.wandergym.service;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.entity.Business;
import ie.wandergym.domain.request.BusinessRequest;
import ie.wandergym.exception.DataNotFoundException;
import ie.wandergym.repository.BusinessRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BusinessServiceTest {

        @InjectMocks
        private BusinessServiceImpl fixture;

        @Mock
        private BusinessRepository repository;


        @Test
        void testFindById(){
            String name = "Gym Name";
            Business business = getBusiness(name);
            when(repository.findById(1L)).thenReturn(of(business));
            BusinessDto response = fixture.find(1L);
            Assertions.assertEquals(name, response.getName());
        }

        @Test
        void testFindByIdNotFound(){
            when(repository.findById(1L)).thenReturn(empty());
            Assert.assertThrows(DataNotFoundException.class, () -> {
                fixture.find(1L);
            });
        }

        @Test
        void testDelete(){
            ArgumentCaptor<Business> businessCaptor = ArgumentCaptor.forClass(Business.class);

            when(repository.findById(anyLong())).thenReturn(of(getBusiness("Gym Name")));

            fixture.delete(1L);

            verify(repository).delete(businessCaptor.capture());
        }

        @Test
        void testDeleteNotFound(){
            when(repository.findById(1L)).thenReturn(empty());
            Assert.assertThrows(DataNotFoundException.class, () -> {
                fixture.delete(1L);
            });
        }

        @Test
        void testUpdateNotFound(){
            BusinessRequest request = getBusinessRequest();
            when(repository.findById(1L)).thenReturn(empty());
            Assert.assertThrows(DataNotFoundException.class, () -> {
                fixture.update(1L, request);
            });
        }

        private BusinessRequest getBusinessRequest() {
            BusinessRequest request = new BusinessRequest();
            request.setName("Gym Awesome Name");
            return request;
        }

        private Business getBusiness(String name) {
            Business business = new Business();
            business.setName(name);
            business.setId(2L);
            return business;
        }
}