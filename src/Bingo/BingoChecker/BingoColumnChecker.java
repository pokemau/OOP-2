package Bingo.BingoChecker;

import Bingo.BingoCard;
import Bingo.BingoGame;

public class BingoColumnChecker extends BingoChecker{
    private int col;

    public BingoColumnChecker(BingoCard bingoCard, int col) {
        super(bingoCard);
        this.col = col-1;
    }
/*
        BingoColumnChecker - extends BingoChecker
            fields:
                - int col initialized at constructor
            run method:
                - loops through all card's elements at the specified col
                - waits for the game to release result
                - can be interrupted and when so, declare what element it was waiting for
         */

    @Override
    public void run() {

        int row = 0;

        for(int col = 0; col < 5; col++) {
            int num = card.numbers[row][col];

            while(!BingoGame.results[num]) {

                try {

                    synchronized (BingoGame.results) {
                        BingoGame.results.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("Card " + card.getID() + " - completes col " + (col+1));

    }
}
