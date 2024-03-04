package Bingo.BingoChecker;


import Bingo.BingoCard;
import Bingo.BingoGame;

public class BingoRowChecker extends BingoChecker {
    private int row;

    public BingoRowChecker(BingoCard bingoCard, int row) {
        super(bingoCard);
        this.row = row-1;
    }

        /*
        BingoRowChecker - extends BingoChecker
            fields:
                    - int row initialized at constructor
            run method:
                    - loops through all card's elements at the specified row
                    - waits for the game to release result
                    - can be interrupted and when so, declare what element it was waiting for
         */

    @Override
    public void run() {

        for(int col = 0; col < row; col++) {
            int num = card.numbers[col][row];

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
        System.out.println("Card " + card.getID() + " - completes row " + (row+1));
    }
}
