package Exceptions_Feb_12;

public class MyList {
    private int[] arr;
    private int _size;
    private int capacity;


    public MyList() {
        _size = 0;
        capacity = 5;
        arr = new int[5];
    }

    public void add(int num) throws ArrayFullException {
        try {
            arr[_size] = num;
            _size++;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayFullException("The array is full and " +
                    num + " cannot be inserted.");
        }
    }

    public void addAt(int pos, int num) throws ArrayFullException {

        if(_size == capacity) {
            throw new ArrayFullException("The array is full and " +
                    num + " cannot be inserted.");
        } else if(pos < 1 || pos > _size+1) {
            throw new InvalidPositionException("Position must be between 1 and " + (_size+1));
        } else {
            // [ 1, 3, 4, 4, 5]
            //      i  i
            for(int i = _size-1; i >= pos-1; i--) {
                arr[i+1] = arr[i];
            }
            arr[pos-1] = num;
            _size++;
        }
    }

    public boolean remove(int num) {

        for(int i = 0; i < _size; i++) {
            if(arr[i] == num) {

                for(int j = i; j < _size-1; j++) {
                    arr[j] = arr[j+1];
                }
                _size--;
                return true;
            }

        }
        return false;
    }

    public int removeAt(int pos) throws InvalidPositionException {

        if(pos < 1 || pos > _size) {
            throw new InvalidPositionException("Position must be between 1 and " + (_size));
        } else {

            int res = arr[pos-1];

            for(int i = pos-1; i < _size-1; i++) {
                arr[i] = arr[i+1];
            }

            _size--;
            return res;
        }
    }

    public boolean contains(int num) {
        for(int i = 0; i < _size; i++) {
            if(arr[i] == num) {
                return true;
            }
        }
        return false;
    }


    public int get(int pos) throws InvalidPositionException {
        if(pos < 1 || pos > _size) {
            throw new InvalidPositionException("Position must be between 1 and " + (_size));
        } else {

            return arr[pos-1];
        }
    }

    public int set(int pos, int num) throws InvalidPositionException {
        if(pos < 1 || pos > _size) {
            throw new InvalidPositionException("Position must be between 1 and " + (_size));
        } else {

            int res = arr[pos-1];
            arr[pos-1] = num;
            return res;
        }
    }

    public int size() { return _size; }
    public boolean isEmpty() { return size() == 0; }


    public void print() {
        for(int i = 0; i < +_size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
