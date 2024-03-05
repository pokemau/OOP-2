package Bingo.BingoPattern;

import Bingo.BingoCard;
import Bingo.BingoChecker.BingoColumnChecker;
import Bingo.BingoChecker.BingoRowChecker;

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
