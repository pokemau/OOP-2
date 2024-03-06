package Threads.Bingo.BingoPattern;

import Threads.Bingo.BingoCard;
import Threads.Bingo.BingoChecker.BingoColumnChecker;
import Threads.Bingo.BingoChecker.BingoRowChecker;

/*
BingoPatternPlus - extends BingoPattern
    - only has constructor
    - adds BingoCheckers at middle row and middle column
*/
public class BingoPatternPlus extends BingoPattern{

    public BingoPatternPlus(BingoCard card) {
        super(card);
        checkers.add(new BingoRowChecker(card, 3));
        checkers.add(new BingoColumnChecker(card, 3));
    }

}
