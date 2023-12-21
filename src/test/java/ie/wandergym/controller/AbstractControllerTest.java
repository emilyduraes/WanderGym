package ie.wandergym.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AbstractControllerTest {
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
                                                            .registerModule(new JavaTimeModule())
                                                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    protected String url;

    @Autowired
    protected MockMvc mvc;
}
