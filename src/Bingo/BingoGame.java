package Bingo;

import Bingo.BingoPattern.BingoPatternHash;
import Bingo.BingoPattern.BingoPatternPlus;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable {

    /*
    fields:
        - boolean array of 76 integers (result)
            - index 0 reserved for FREE
            - all initialized to false, except for 0 (FREE)
                - boolean variable to identify bingo
        - list of BingoCard
     */


    public static boolean[] results;
    public static boolean bingo;
    private ArrayList<BingoCard> bingoCards;
    private static int bingoCardCount = 1;

    public BingoGame() {
        bingo = false;

        bingoCards = new ArrayList<>();

        results = new boolean[76];
        results[0] = true;
        for(int i = 1 ; i < 75; i++)
            results[i] = false;
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        getCardCount(sc);
        createThreads(sc);

//        for(BingoCard card : bingoCards) {
//            System.out.println("Card: " + card.GetBingoCardID());
//            card.printNums();
//        }

    /*
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
        Random rand = new Random();
        int randomNum;
        while(!bingo) {

            while(true) {
                randomNum = rand.nextInt(1, 76);

                if(!results[randomNum]) {
                    break;
                }
            }
            System.out.println("Chosen Num: " + randomNum);
            results[randomNum] = true;
            printResults();

            synchronized (results) {
                results.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void printResults() {
        for(int i = 1; i < 76; i++) {
            if(results[i] == true) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    private void getCardCount(Scanner sc) {
        System.out.print("How many players/cards? ");
        int playerCount = sc.nextInt();

        for(int i = 0; i < playerCount; i++) {
            bingoCards.add(new BingoCard(bingoCardCount));
            bingoCardCount++;
        }
    }

    private void createThreads(Scanner sc) {
        ArrayList<Thread> bingoPatternThreads = new ArrayList<>();

        int pattern;
        while(true) {
            System.out.print("What pattern? 1[+]   2[#]   : ");
            pattern = sc.nextInt();

            if(pattern > 0 && pattern < 3) { break; }
        }

        if(pattern == 1) {

            for(BingoCard bingoCard : bingoCards) {
                bingoPatternThreads.add(new Thread(new BingoPatternPlus(bingoCard)));
            }
        }
        else {
            for(BingoCard bingoCard : bingoCards) {
                bingoPatternThreads.add(new Thread(new BingoPatternHash(bingoCard)));
            }
        }

        for(Thread t : bingoPatternThreads)
            t.start();
    }
}
