package Bingo.BingoPattern;

import Bingo.BingoCard;
import Bingo.BingoChecker.BingoColumnChecker;
import Bingo.BingoChecker.BingoRowChecker;

public class BingoPatternHash extends BingoPattern{

    public BingoPatternHash(BingoCard bingoCard) {
        super(bingoCard);

        bingoCheckers.add(new BingoRowChecker(bingoCard, 2));
        bingoCheckers.add(new BingoRowChecker(bingoCard, 4));
        bingoCheckers.add(new BingoColumnChecker(bingoCard, 2));
        bingoCheckers.add(new BingoColumnChecker(bingoCard, 4));
    }
}
