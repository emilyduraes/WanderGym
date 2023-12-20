package ie.wandergym.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(Long id){
        super(String.format("Entity with Id %d not found", id));
    }
}
