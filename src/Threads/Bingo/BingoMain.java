package Threads.Bingo;

public class BingoMain {

    public static void main(String[] args) {

        Thread t = new Thread(new BingoGame());

        t.start();
    }
}
