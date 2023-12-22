package ie.wandergym.controller;

import ie.wandergym.domain.request.BusinessRequest;
import ie.wandergym.domain.response.BusinessListResponse;
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

    @ApiOperation(value = "Recover a fitness partner by name")
    @GetMapping(path = "/name/{name}")
    public BusinessListResponse getByName(@PathVariable String name){
        return new BusinessListResponse(service.findByName(name));
    }

    @ApiOperation(value = "Recover all available fitness partners")
    @GetMapping(path = "/all")
    public BusinessListResponse getAllBusinesses(){
        return new BusinessListResponse(service.findAllBusinesses());
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

    @ApiOperation(value = "Deactivate a business partner account in the database")
    @PatchMapping(path = "/id/{id}/deactivate")
    public void deactivate(@PathVariable Long id){
        service.deactivate(id);
    }
}
