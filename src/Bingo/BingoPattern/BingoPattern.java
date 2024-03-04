package Bingo.BingoPattern;

import Bingo.BingoCard;
import Bingo.BingoChecker.BingoChecker;
import Bingo.BingoGame;

import java.util.ArrayList;

public abstract class BingoPattern implements Runnable {

    /*
    BingoPattern - abstract Runnable
        fields:
            - list of BingoCheckers
            - the BingoCard to check against, initialized at constructor

        run method:
            - creates threads for BingoCheckers
            - starts them all at once
            - waits for all the threads to finish and when done,
                - declare bingo
                - output "Card [id] completes pattern" while printing all elements in card form
                - stops all other threads
            - can be interrupted and when so, output "Card [id] loses"
     */

    ArrayList<BingoChecker> bingoCheckers;
    BingoCard bingoCard;

    public BingoPattern(BingoCard bingoCard) {
        this.bingoCard = bingoCard;
        bingoCheckers = new ArrayList<>();
    }

    @Override
    public void run() {

        ArrayList<Thread> threads = new ArrayList<>();

        for(BingoChecker bingoChecker : bingoCheckers) {
            threads.add(new Thread(bingoChecker));
        }

        for(Thread t : threads)
            t.start();

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        BingoGame.bingo = true;
        if(BingoGame.bingo) {
            bingoCard.printNums();

        }
    }


}
