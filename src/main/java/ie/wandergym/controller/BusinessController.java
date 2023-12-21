package ie.wandergym.controller;

import ie.wandergym.domain.request.BusinessRequest;
import ie.wandergym.domain.response.BusinessResponse;
import ie.wandergym.service.BusinessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("/business")
public class BusinessController {
    private final BusinessService service;

    // class constructor
    public BusinessController(BusinessService service){
        this.service = service;
    }

    @ApiOperation(value = "Insert a new business into the database")
    @PostMapping(path = "/signin", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid BusinessRequest request) {
        service.create(request);
    }

    @ApiOperation(value = "Find a business partner by ID from the database")
    @GetMapping(path = "/id/{id}")
    public BusinessResponse get(@PathVariable Long id){
        return new BusinessResponse(service.find(id));
    }

    @ApiOperation(value = "Delete a business partner from the database by ID")
    @DeleteMapping(path = "/id/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @ApiOperation(value = "Update a business partner from the database by ID")
    @PutMapping(path = "/id/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid BusinessRequest request){
        service.update(id, request);
    }
}
