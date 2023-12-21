package ie.wandergym.service;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.entity.FinalUser;
import ie.wandergym.domain.request.FinalUserRequest;
import ie.wandergym.exception.DataNotFoundException;
import ie.wandergym.repository.FinalUserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Optional.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FinalUserServiceTest {

    @InjectMocks
    private FinalUserServiceImpl fixture;

    @Mock
    private FinalUserRepository repository;


    @Test
    void testFindById(){
        String name = "Astolfo";
        FinalUser user = getFinalUser(name);
        when(repository.findById(1L)).thenReturn(of(user));
        FinalUserDto response = fixture.findFinalUser(1L);
        Assertions.assertEquals(name, response.getFullName());
    }

    @Test
    void testFindByIdNotFound(){
        when(repository.findById(1L)).thenReturn(empty());
        Assert.assertThrows(DataNotFoundException.class, () -> {
            fixture.findFinalUser(1L);
        });
    }

    @Test
    void testDelete(){
        ArgumentCaptor<FinalUser> finalUserCaptor = ArgumentCaptor.forClass(FinalUser.class);

        when(repository.findById(anyLong())).thenReturn(of(getFinalUser("Mark")));

        fixture.deleteFinalUser(1L);

        verify(repository).delete(finalUserCaptor.capture());
    }

    @Test
    void testDeleteNotFound(){
        when(repository.findById(1L)).thenReturn(empty());
        Assert.assertThrows(DataNotFoundException.class, () -> {
            fixture.deleteFinalUser(1L);
        });
    }

    @Test
    void testUpdateNotFound(){
        FinalUserRequest finalUserRequest = getFinalUserRequest();
        when(repository.findById(1L)).thenReturn(empty());
        Assert.assertThrows(DataNotFoundException.class, () -> {
            fixture.updateFinalUser(1L, finalUserRequest);
        });
    }

    private FinalUserRequest getFinalUserRequest() {
        FinalUserRequest request = new FinalUserRequest();
        request.setFullName("Clodoaldo");
        return request;
    }

    private FinalUser getFinalUser(String name) {
        FinalUser user = new FinalUser();
        user.setFullName(name);
        user.setId(2L);
        return user;
    }
}
