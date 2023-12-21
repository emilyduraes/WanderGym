package ie.wandergym.controller;

import ie.wandergym.domain.dto.FinalUserDto;
import ie.wandergym.domain.request.FinalUserRequest;
import ie.wandergym.service.FinalUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FinalUserControllerTest extends AbstractControllerTest{

    public static final String FULL_NAME = "full name";
    public static final long MOBILE_PHONE = 9999999L;
    public static final String EMAIL = "email@domain.com";
    public static final String BAD_EMAIL = "email";
    public static final String ADDRESS = "1, Address, City, Country";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.parse("1989-05-21");

    @MockBean
    private FinalUserServiceImpl finalUserService;


    @Test
    void testPostIsBadRequest() throws Exception {
        url = "/user/signin";

        FinalUserRequest request = getFinalUserRequest();
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
        url = "/user/signin";

        FinalUserRequest request = getFinalUserRequest();
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(post(url)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testGetById() throws Exception {
        url = "/user/id/";
        long id = 1L;

        FinalUserDto finalUserDto = getFinalUserDto();
        when(finalUserService.findFinalUser(anyLong())).thenReturn(finalUserDto);

        mvc.perform(get(url + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalUser.fullName", is(finalUserDto.getFullName())))
                .andExpect(jsonPath("$.finalUser.dob").isNotEmpty())
                .andExpect(jsonPath("$.finalUser.email", is(finalUserDto.getEmail())))
                .andExpect(jsonPath("$.finalUser.mobileNumber").isNumber())
                .andExpect(jsonPath("$.finalUser.active", is(finalUserDto.isActive())))
                .andDo(print());
    }

    @Test
    void testGetByIdIsNotFound() throws Exception {
        url = "/user/id/";

        FinalUserDto finalUserDto = getFinalUserDto();
        when(finalUserService.findFinalUser(anyLong())).thenReturn(finalUserDto);

        mvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testUpdate() throws Exception {
        url = "/user/id/";
        long id = 1L;

        FinalUserRequest request = getFinalUserRequest();
        String jsonValue = OBJECT_MAPPER.writeValueAsString(request);

        mvc.perform(put(url + id)
                        .content(jsonValue)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateIsNotFound() throws Exception {
        url = "/user/id/";

        mvc.perform(put(url)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testUpdateIsBadRequest() throws Exception {
        url = "/user/id/";
        long id = 1L;

        FinalUserRequest request = getFinalUserRequest();
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
        url = "/user/id/";
        long id = 1L;

        mvc.perform(delete(url + id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteIsNotAllowed() throws Exception{
        url = "/user/id/";
        long id = 1L;

        mvc.perform(post(url + id))
                .andExpect(status().isMethodNotAllowed())
                .andDo(print());
    }

    private FinalUserRequest getFinalUserRequest() {
        FinalUserRequest request = new FinalUserRequest();
        request.setFullName(FULL_NAME);
        request.setAddress(ADDRESS);
        request.setEmail(EMAIL);
        request.setDob(DATE_OF_BIRTH);
        request.setMobileNumber(MOBILE_PHONE);
        return request;
    }

    private FinalUserDto getFinalUserDto() {
        FinalUserDto dto = new FinalUserDto();
        dto.setFullName(FULL_NAME);
        dto.setAddress(ADDRESS);
        dto.setEmail(EMAIL);
        dto.setDob(DATE_OF_BIRTH);
        dto.setMobileNumber(MOBILE_PHONE);
        return dto;
    }
}
