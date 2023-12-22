package ie.wandergym.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(Long id){
        super(String.format("Entity with Id %d not found", id));
    }

    public DataNotFoundException(Long id, String entity){
        super(String.format("Entity " + entity + " with Id %d not found", id));
    }

    public DataNotFoundException(String name){
        super(String.format("Entity with name %s not found", name));
    }
}
