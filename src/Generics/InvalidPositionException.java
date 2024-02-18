package Generics;

public class InvalidPositionException extends IllegalArgumentException {
    public InvalidPositionException(int maxPos) {
        super("Position must be between 1 and " + maxPos);
    }
}
