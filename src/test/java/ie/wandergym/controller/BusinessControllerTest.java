package ie.wandergym.controller;

import ie.wandergym.domain.dto.BusinessDto;
import ie.wandergym.domain.request.BusinessRequest;
import ie.wandergym.service.BusinessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BusinessControllerTest  extends AbstractControllerTest{

    public static final String NAME = "business name";
    public static final long PHONE_NUMBER = 9999999L;
    public static final String EMAIL = "gym_email@domain.com";
    public static final String BAD_EMAIL = "email";
    public static final String ADDRESS = "1, Address, City, Country";

    @MockBean
    private BusinessServiceImpl service;


    @Test
    void testPostIsBadRequest() throws Exception {
        url = "/business/signin";

        BusinessRequest request = getBusinessRequest();
        request.setEmail(BAD_EMAIL);
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(post(url)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testPost() throws Exception {
        url = "/business/signin";

        BusinessRequest request = getBusinessRequest();
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(post(url)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testGetById() throws Exception {
        url = "/business/id/";
        long id = 1L;

        BusinessDto businessDto = getBusinessDto();
        when(service.find(anyLong())).thenReturn(businessDto);

        mvc.perform(get(url + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.business.name", is(businessDto.getName())))
                .andExpect(jsonPath("$.business.email", is(businessDto.getEmail())))
                .andExpect(jsonPath("$.business.phoneNumber").isNumber())
                .andExpect(jsonPath("$.business.active", is(businessDto.isActive())))
                .andDo(print());
    }

    @Test
    void testGetByIdIsNotFound() throws Exception {
        url = "/business/id/";

        BusinessDto businessDto = getBusinessDto();
        when(service.find(anyLong())).thenReturn(businessDto);

        mvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testGetByName() throws Exception {
        url = "/business/name/";
        String name = "Gym";

        when(service.findByName(name)).thenReturn(Collections.singletonList(getBusinessDto()));

        mvc.perform(get(url + name))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetAll() throws Exception {
        url = "/business/all";

        when(service.findAllBusinesses()).thenReturn(Collections.singletonList(getBusinessDto()));

        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdate() throws Exception {
        url = "/business/id/";
        long id = 1L;

        BusinessRequest request = getBusinessRequest();
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(put(url + id)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateIsNotFound() throws Exception {
        url = "/business/id/";

        mvc.perform(put(url)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testUpdateIsBadRequest() throws Exception {
        url = "/business/id/";
        long id = 1L;

        BusinessRequest request = getBusinessRequest();
        request.setEmail(BAD_EMAIL);
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(put(url + id)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception{
        url = "/business/id/";
        long id = 1L;

        mvc.perform(delete(url + id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteIsNotAllowed() throws Exception{
        url = "/business/id/";
        long id = 1L;

        mvc.perform(post(url + id))
                .andExpect(status().isMethodNotAllowed())
                .andDo(print());
    }

    @Test
    void testDeactivate() throws Exception{
        url = "/business/id/";
        long id = 1L;

        mvc.perform(patch(url + id + "/deactivate"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private BusinessRequest getBusinessRequest() {
        BusinessRequest request = new BusinessRequest();
        request.setName(NAME);
        request.setAddress(ADDRESS);
        request.setEmail(EMAIL);
        request.setPhoneNumber(PHONE_NUMBER);
        return request;
    }

    private BusinessDto getBusinessDto() {
        BusinessDto dto = new BusinessDto();
        dto.setName(NAME);
        dto.setAddress(ADDRESS);
        dto.setEmail(EMAIL);
        dto.setPhoneNumber(PHONE_NUMBER);
        return dto;
    }
}