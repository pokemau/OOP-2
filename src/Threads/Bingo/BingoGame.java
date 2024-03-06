package Threads.Bingo;

import Threads.Bingo.BingoPattern.BingoPatternPlus;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
BingoGame - Runnable
fields:
- boolean array of 76 elements (result)
	- index 0 reserved for FREE
	- all initialized to false, except for 0 (FREE)
- boolean variable to identify bingo
- list of BingoCard

run method:
- asks for the number of players/cards
- creates the list of cards
- creates a list of threads containing BingoPattern
- starts all threads at once
- loops while it is not bingo
	- generate a random number
	- outputs the number chosen
	- prints out all numbers chosen numerically
	- notifies those waiting for result
	- sleeps for 300 milliseconds
- if bingo
	- prints out all numbers again
	- interrupts all threads since game is over
*/
public class BingoGame implements Runnable {
    public static final boolean[] res = new boolean[76];
    public static boolean bingo;
    ArrayList<BingoCard> cards;

    private int CardID = 1;

    public BingoGame() {
        cards = new ArrayList<>();
        bingo = false;
        Init();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many players: ");
        int count = sc.nextInt();
        for(int i = 0; i < count; i++) {
            cards.add(new BingoCard(CardID));
            CardID++;
        }

        ArrayList<Thread> patternThreads = new ArrayList<>();
        // plus pattern
        for(BingoCard card : cards)
            patternThreads.add(new Thread(new BingoPatternPlus(card)));
        for(Thread t : patternThreads)
            t.start();

        while(!bingo) {

            int randomNum = generateRandomNum();

            System.out.println("NUM: " + randomNum);
            printChosenNums();

            synchronized (res) {
                res.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized (res) {
            res.notifyAll();
        }

        System.out.println("BINGO!");
        printChosenNums();
    }

    private int generateRandomNum() {
        Random random = new Random();
        int randomNum;

        while(true) {
            randomNum = random.nextInt(1, 76);
            if(!res[randomNum]) {
                res[randomNum] = true;
                break;
            }
        }
        return randomNum;
    }

    private void printChosenNums() {
        for(int i = 1; i < 76; i++) {
            if(res[i])
                System.out.print(i + " ");
        }
        System.out.println();
    }

    private void Init() {
        res[0] = true;

        for (int i = 1; i < 76; i++)
            res[i] = false;
    }
}
