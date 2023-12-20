package ie.wandergym.controller;

import ie.wandergym.domain.request.FinalUserRequest;
import ie.wandergym.domain.response.FinalUserResponse;
import ie.wandergym.service.FinalUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("/user")
public class FinalUserController {
    private final FinalUserService finalUserService;

    // class constructor
    public FinalUserController(FinalUserService finalUserService){
        this.finalUserService = finalUserService;
    }

    @ApiOperation(value = "Insert a new user into the database")
    @PostMapping(path = "/signin", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFinalUser(@RequestBody @Valid FinalUserRequest request) {
        finalUserService.createFinalUser(request);
    }

    @ApiOperation(value = "Find a WanderGym user by ID from the database")
    @GetMapping(path = "/id/{id}")
    public FinalUserResponse getFinalUser(@PathVariable Long id){
        return new FinalUserResponse(finalUserService.findFinalUser(id));
    }

    @ApiOperation(value = "Delete a WanderGym user from the database by ID")
    @DeleteMapping(path = "/id/{id}")
    public void deleteFinalUser(@PathVariable Long id){
        finalUserService.deleteFinalUser(id);
    }
}
