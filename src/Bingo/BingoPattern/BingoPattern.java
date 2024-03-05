package Bingo.BingoPattern;

import Bingo.BingoCard;
import Bingo.BingoChecker.BingoChecker;
import Bingo.BingoGame;

import java.util.ArrayList;
import java.util.List;

/*
BingoPattern - abstract Runnable
- only to be extended from actual pattern checkers

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
public abstract class BingoPattern implements Runnable {
    ArrayList<BingoChecker> checkers;
    private BingoCard card;

    public BingoPattern(BingoCard card) {
        this.card = card;
        checkers = new ArrayList<>();
    }

    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>();

        for(BingoChecker checker : checkers)
            threads.add(new Thread(checker));
        for(Thread t : threads)
            t.start();

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(!BingoGame.bingo){
            System.out.println("Card " + card.getID() + " completes pattern!");
            card.printNums();
            BingoGame.bingo = true;
        }
    }
}
