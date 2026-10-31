package CRUD_PROJECT.SpringBootDemo.exception;

public class DuplicateResouceFoundException extends RuntimeException{

    public DuplicateResouceFoundException(String message){

        super(message);

    }
}
