package Exceptions_Feb_12;

public class MyCatList {

    private Cat[] arr;
    int len;
    int capacity;


    public MyCatList() {
        len = 0;
        capacity = 5;
        arr = new Cat[5];
    }

    public void add(Cat c) throws ArrayFullException {
        try {
            arr[len] = c;
            len++;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayFullException("The array is full and " +
                    len + " cannot be inserted.");
        }
    }

    public void addAt(int pos, Cat c) throws ArrayFullException, InvalidPositionException {
        if(len == capacity) {
            throw new ArrayFullException("The array is full and " +
                    c.getName() + " cannot be inserted.");

        } else if(pos < 1 || pos > len+1) {
            throw new InvalidPositionException("Position must be between 1 and " + (len+1));
        } else {
            // [ 1, 2, 3, 0, 0]
            //         i
            for(int i = len-1; i >= pos-1; i--) {
                arr[i+1] = arr[i];
            }
            arr[pos-1] = c;
            len++;
        }
    }

    public boolean remove(String c) {

        for(int i = 0; i < len; i++) {
            if(arr[i].getName().equals(c)) {

                for(int j = i; j < len-1; j++) {
                    arr[j] = arr[j+1];
                }
                len--;
                return true;
            }

        }
        return false;
    }

    public Cat removeAt(int pos) throws InvalidPositionException {

        if(pos < 1 || pos > len) {
            throw new InvalidPositionException("Position must be between 1 and " + (len));
        } else {

            Cat res = arr[pos-1];

            for(int i = pos-1; i < len-1; i++) {
                arr[i] = arr[i+1];
            }

            len--;
            return res;
        }
    }

    public boolean contains(Cat c) {
        for(int i = 0; i < len; i++) {
            if(arr[i].getName().equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    public Cat get(int pos) throws InvalidPositionException {
        if(pos < 1 || pos > len) {
            throw new InvalidPositionException("Position must be between 1 and " + (len));
        } else {

            return arr[pos-1];
        }
    }

    public Cat set(int pos, Cat c) throws InvalidPositionException {
        if(pos < 1 || pos > len) {
            throw new InvalidPositionException("Position must be between 1 and " + (len));
        } else {

            Cat res = arr[pos-1];
            arr[pos-1] = c;
            return res;
        }
    }

    public int size() { return len; }
    public boolean isEmpty() { return size() == 0; }

    public void print() {
        for(int i = 0; i < len; i++) {
            System.out.print(arr[i].getName() + " ");
        }

        System.out.println();
    }
}
