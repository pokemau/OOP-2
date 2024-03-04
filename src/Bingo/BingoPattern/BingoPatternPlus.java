package Bingo.BingoPattern;

import Bingo.BingoCard;
import Bingo.BingoChecker.BingoColumnChecker;
import Bingo.BingoChecker.BingoRowChecker;

public class BingoPatternPlus extends BingoPattern {

    public BingoPatternPlus(BingoCard bingoCard) {
        super(bingoCard);

        bingoCheckers.add(new BingoColumnChecker(bingoCard, 3));
        bingoCheckers.add(new BingoRowChecker(bingoCard, 3));
    }
}
