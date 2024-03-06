package Threads.Bingo.BingoChecker;

import Threads.Bingo.BingoCard;

public abstract class BingoChecker implements Runnable {
    BingoCard card;

    public BingoChecker(BingoCard card) {
        this.card = card;
    }
}
