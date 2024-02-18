package Generics;

public class MyGenList<T> {
    private T[] arr;

    private int capacity;
    private int len;


    public MyGenList() {
        len = 0;
        capacity = 5;

        arr = (T[]) new Object[capacity];
    }

    public void add(T val) throws ArrayFullException {
        try {
            arr[len] = val;
            len++;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayFullException(val);
        }
    }

    public void addAt(int pos, T val) throws ArrayFullException {
        if (len == capacity) {
            throw new ArrayFullException(val);
        } else if (pos > len + 1 || pos < 1) {
            throw new InvalidPositionException(len + 1);
        } else {

            for(int i = len; i >= pos; i--) {
                arr[i] = arr[i-1];
            }
            arr[pos-1] = val;
            len++;
        }
    }

    public boolean remove(Object val) {

        for(int i = 0; i < len; i++) {
            if(arr[i].equals(val)) {

                for(int j = i; j < len-1; j++) {
                    arr[j] = arr[j+1];
                }
                len--;
                return true;
            }

        }
        return false;
    }

    public T removeAt(int pos) {
        if(pos < 1 || pos > len) {
            throw new InvalidPositionException(len);
        } else {

            T res = arr[pos-1];

            for(int i = pos-1; i < len-1; i++) {
                arr[i] = arr[i+1];
            }

            len--;
            return res;
        }
    }

    public boolean contains(Object val) {
        for(int i = 0; i < len; i++) {
            if(arr[i].equals(val)) {
                return true;
            }
        }
        return false;
    }


    public T get(int pos) throws InvalidPositionException {
        if(pos < 1 || pos > len) {
            throw new InvalidPositionException(len);
        } else {
            return arr[pos-1];
        }
    }

    public T set(int pos, T val) throws InvalidPositionException {
        if(pos < 1 || pos > len) {
            throw new InvalidPositionException(len);
        } else {

            T res = arr[pos-1];
            arr[pos-1] = val;
            return res;
        }
    }

    public int size() { return len; }
    public boolean isEmpty() { return size() == 0; }


    public void print() {
        for(int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
