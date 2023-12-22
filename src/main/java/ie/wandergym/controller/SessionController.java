package ie.wandergym.controller;

import ie.wandergym.domain.request.SessionRequest;
import ie.wandergym.service.SessionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("/session")
public class SessionController {

    private final SessionService service;

    // class constructor
    public SessionController(SessionService service){
        this.service = service;
    }

    @ApiOperation(value = "Start a new gym session")
    @PostMapping(path = "/start", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void start(@RequestBody @Valid SessionRequest request) {
        service.start(request);
    }
}
