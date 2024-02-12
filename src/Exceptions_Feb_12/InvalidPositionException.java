package Exceptions_Feb_12;

public class InvalidPositionException extends RuntimeException{

    public InvalidPositionException(String errMsg) {
        super(errMsg);
    }
}
