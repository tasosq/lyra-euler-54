package models;

import enums.Winner;
import utils.WinnerEvaluator;

import java.util.Arrays;

public class PlayerHands {

    private final Hand p1Hand;
    private final Hand p2Hand;

    public PlayerHands(String[] cardStrings) {
        this.p1Hand = new Hand(Arrays.copyOfRange(cardStrings, 0, 5));
        this.p2Hand = new Hand(Arrays.copyOfRange(cardStrings, 5, 10));
    }

    public boolean playerOneWon() {
        return WinnerEvaluator.getWinner(p1Hand.getHandDeepEval(), p2Hand.getHandDeepEval()).equals(Winner.PLAYER_ONE);
    }
}
