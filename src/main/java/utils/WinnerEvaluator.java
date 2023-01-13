package utils;

import enums.Winner;
import models.Card;
import models.HandDeepEval;

import java.util.List;
import java.util.Optional;

public class WinnerEvaluator {

    /**
     * Method that finds the winner.
     * @param p1
     * @param p2
     * @return
     */
    public static Winner getWinner(HandDeepEval p1, HandDeepEval p2) {
        // Comparing who has the best ranking hand
        Winner winnerFirst = compareHighest(p1, p2);
        if (winnerFirst != Winner.DRAW){
            return winnerFirst;
        }
        // The hand rankings where equal, so now we check the highest from the winning ranking cards
        Winner  winnerSecond = compareHighestWinningRanking(p1, p2);
        if (winnerSecond != Winner.DRAW) {
            return winnerSecond;
        }
        // Hand rankings equal, winning cards ranking equal, now checking the remaining cards
        return compareRemainingRanking(p1, p2);

    }

    /**
     * Method that compares the highest ranking hand
     * @param p1
     * @param p2
     * @return
     */
    private static Winner compareHighest(HandDeepEval p1, HandDeepEval p2) {
        int evalComparison = p1.getHandRankingEnum().compareTo(p2.getHandRankingEnum());
        if (evalComparison > 0) {
            return Winner.PLAYER_ONE;
        } else if (evalComparison < 0) {
            return Winner.PLAYER_TWO;
        } else {
            return Winner.DRAW;
        }
    }

    /**
     * Method that compares the highest rank Card amongst the winning cards of two equal-ranking Hands
     * @param p1
     * @param p2
     * @return
     */
    private static Winner compareHighestWinningRanking(HandDeepEval p1, HandDeepEval p2) {
        List<Card> p1Cards = p1.getRankAndRemainingCards().getRankCards();
        List<Card> p2Cards = p2.getRankAndRemainingCards().getRankCards();

        // the sizes should be the same
        for (int i = 0; i < p1Cards.size(); i++) {
            int evalComparison = p1Cards.get(i).compareTo(p2Cards.get(i));
            if (evalComparison > 0) {
                return Winner.PLAYER_ONE;
            }
            else if (evalComparison < 0) {
                return Winner.PLAYER_TWO;
            }
        }
        return Winner.DRAW;
    }

    /**
     * Method that compares the highest rank card amongst the remaining cards
     * of equal-ranking hands with equal rank of winning cards
     * @param p1
     * @param p2
     * @return
     */
    private static Winner compareRemainingRanking(HandDeepEval p1, HandDeepEval p2){
        List<Card> p1Cards = p1.getRankAndRemainingCards().getRemainingCards();
        List<Card> p2Cards = p2.getRankAndRemainingCards().getRemainingCards();

        for (int i = 0; i < p1Cards.size(); i++) {
            int evalComparison = p1Cards.get(i).compareTo(p2Cards.get(i));
            if (evalComparison > 0) {
                return Winner.PLAYER_ONE;
            }
            else if (evalComparison < 0) {
                return Winner.PLAYER_TWO;
            }
        }
        return Winner.IT_BROKE;
    }
}
