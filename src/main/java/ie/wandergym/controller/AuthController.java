package ie.wandergym.controller;

import ie.wandergym.domain.request.AuthRequest;
import ie.wandergym.domain.request.LoginRequest;
import ie.wandergym.domain.response.LoginResponse;
import ie.wandergym.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "Application login route")
    @PostMapping(path = "/login", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginResponse login(@RequestBody @Valid LoginRequest request) throws Exception {
        return new LoginResponse(authService.login(request));
    }

    @ApiOperation(value = "Application logout route")
    @GetMapping(value = "/logout")
    @ResponseBody
    public LoginResponse logout() {
        return new LoginResponse(authService.logout());
    }

    @ApiOperation(value = "Insert a user authentication into the database")
    @PostMapping(path = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid AuthRequest request) {
        authService.register(request);
    }
}
