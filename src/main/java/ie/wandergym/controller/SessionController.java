package ie.wandergym.controller;

import ie.wandergym.domain.request.SessionRequest;
import ie.wandergym.domain.response.SessionResponse;
import ie.wandergym.service.SessionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/session")
@PreAuthorize("hasAnyRole('BUSINESS', 'SYSADMIN', 'USER')")
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

    @ApiOperation(value = "Get gym monthly and daily users attendance")
    @GetMapping(path = "/{businessId}/attendance")
    @ResponseStatus(HttpStatus.OK)
    public SessionResponse getAttendance(@PathVariable Long businessId) {
        return new SessionResponse(service.getAttendance(businessId));
    }

}
