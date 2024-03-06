package Threads.Bingo.BingoChecker;

import Threads.Bingo.BingoCard;
import Threads.Bingo.BingoGame;

/*
BingoColumnChecker - extends BingoChecker
    fields:
        - int col initialized at constructor
    run method:
        - loops through all card's elements at the specified col
        - waits for the game to release result
        - can be interrupted and when so, declare what element it was waiting for
 */

public class BingoColumnChecker extends BingoChecker {

    private int col;

    public BingoColumnChecker(BingoCard card, int col) {
        super(card);
        this.col = col-1;
    }

    @Override
    public void run() {

        for(int i = 0; i < 5; i++) {
            int currNum = card.getNums()[i][col];

            if (currNum == 0) { continue; }

            while(!BingoGame.res[currNum] && !BingoGame.bingo) {

                try {
                    synchronized (BingoGame.res) {
                        BingoGame.res.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(BingoGame.bingo) { break; }
        }



    }
}
