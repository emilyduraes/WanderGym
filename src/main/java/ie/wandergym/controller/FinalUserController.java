package ie.wandergym.controller;

import ie.wandergym.domain.request.FinalUserRequest;
import ie.wandergym.domain.response.FinalUserResponse;
import ie.wandergym.service.FinalUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/user")
public class FinalUserController {
    private final FinalUserService service;

    // class constructor
    public FinalUserController(FinalUserService service){
        this.service = service;
    }

    @ApiOperation(value = "Insert a new user into the database")
    @PostMapping(path = "/signin", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid FinalUserRequest request) {
        service.create(request);
    }

    @ApiOperation(value = "Find a WanderGym user by ID from the database")
    @GetMapping(path = "/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'SYSADMIN')")
    public FinalUserResponse get(@PathVariable Long id){
        return new FinalUserResponse(service.find(id));
    }

    @ApiOperation(value = "Find a WanderGym user by email from the database")
    @GetMapping(path = "/email/{email:.+}")
    @PreAuthorize("hasAnyRole('USER', 'SYSADMIN')")
    public FinalUserResponse getByEmail(@PathVariable String email){
        return new FinalUserResponse(service.findByEmail(email));
    }

    @ApiOperation(value = "Delete a WanderGym user from the database by ID")
    @DeleteMapping(path = "/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'SYSADMIN')")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @ApiOperation(value = "Update a WanderGym user from the database by ID")
    @PutMapping(path = "/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'SYSADMIN')")
    public void update(@PathVariable Long id, @RequestBody @Valid FinalUserRequest request){
        service.update(id, request);
    }
}
