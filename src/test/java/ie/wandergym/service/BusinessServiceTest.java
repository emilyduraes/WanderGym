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

import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
        void testFindByNameNotFound(){
            when(repository.findByName(anyString())).thenReturn(empty());

            Assert.assertThrows(DataNotFoundException.class, () -> {
                fixture.findByName("Some name");
            });
        }

        @Test
        void testFindByNameInactive(){
            Business business = getBusiness("Not so Awesome Gym");
            business.setActive(false);
            when(repository.findByName(anyString())).thenReturn(of(singletonList(business)));

            List<BusinessDto> result = fixture.findByName("Some gym");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        void testFindByName(){
            when(repository.findByName(anyString())).thenReturn(of(singletonList(getBusiness("Awesome Gym"))));

            List<BusinessDto> result = fixture.findByName("Awesome Gym");
            Assertions.assertFalse(result.isEmpty());
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