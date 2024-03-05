package Bingo.BingoChecker;

import Bingo.BingoCard;
import Bingo.BingoGame;

/*
BingoRowChecker - extends BingoChecker

fields:
    - int row initialized at constructor
run method:
    - loops through all card's elements at the specified row
    - waits for the game to release result
    - can be interrupted and when so, declare what element it was waiting for

 */
public class BingoRowChecker extends BingoChecker {

    private int row;

    public BingoRowChecker(BingoCard card, int row) {
        super(card);
        this.row = row-1;
    }

    @Override
    public void run() {

        /*
        run method:
            - loops through all card's elements at the specified row
            - waits for the game to release result
            - can be interrupted and when so, declare what element it was waiting for
         */

        for(int col = 0; col < 5; col++) {
            int currNum = card.getNums()[row][col];

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

            if (BingoGame.bingo) { break; }
        }
    }
}
