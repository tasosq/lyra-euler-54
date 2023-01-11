package utils;

import enums.HandRankingEnum;
import enums.Suit;
import enums.Value;
import models.Card;
import models.HandDeepEval;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * This is the class that does the heavy lifting. Contains the methods that evaluate the hand.
 */
public class HandEvaluator {


    /**
     * This method evaluates if the hand is a flush , a straight, a straight flush, or a royal flush
     * @param cards
     * @return a HandDeepEval object, containing the hand's rank enum, and the ranking cards and remaining cards
     */
    public static Optional<HandDeepEval> getFlushOrStraightResult(Card[] cards) {
        boolean isStraight = true, isFlush = true;
        Card previousCard = cards[0];
        for (int i = 1; i < 5; i++) {
            Value currentValue = cards[i].getValue();
            Suit currentSuit = cards[i].getSuit();
            if (isStraight && currentValue.ordinal() - previousCard.getValue().ordinal() != 1)
                isStraight = false;
            if (isFlush && !currentSuit.equals(previousCard.getSuit()))
                isFlush = false;
            previousCard = cards[i];
        }

        HandDeepEval handDeepEval = null;
        new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList());
        if (isFlush && isStraight && Arrays.stream(cards).anyMatch(c->c.getValue().equals(Value.ACE))){
            handDeepEval = new HandDeepEval(HandRankingEnum.ROYAL_FLUSH, new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList()));
        }
        else if (isFlush && isStraight )
            handDeepEval = new HandDeepEval(HandRankingEnum.STRAIGHT_FLUSH,
                    new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList()));
        else if (isStraight)
            handDeepEval = new HandDeepEval(HandRankingEnum.STRAIGHT,
                    new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList()));
        else if (isFlush)
            handDeepEval = new HandDeepEval(HandRankingEnum.FLUSH,
                    new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList()));
        return Optional.ofNullable(handDeepEval);
    }
}
