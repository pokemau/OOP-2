package Generics;

public class ArrayFullException extends Exception{
    public ArrayFullException(Object val) {
        super("The array is full and " + val + " cannot be inserted.");
    }
}
