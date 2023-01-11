package models;

import enums.HandRankingEnum;
import utils.RankAndRemainingCards;

/**
 * This is a class used to store, the handRankingEnum of the hand (e.g. if it was a STRAIGHT, or a FLUSH) and
 * the cards that produced that rank, aswell as the remaining cards
 */
public class HandDeepEval {

    private final HandRankingEnum handRankingEnum;

    private final RankAndRemainingCards rankAndRemainingCards;

    public HandDeepEval(HandRankingEnum handRankingEnum, RankAndRemainingCards rankAndRemainingCards) {
        this.handRankingEnum = handRankingEnum;
        this.rankAndRemainingCards = rankAndRemainingCards;
    }

    public HandRankingEnum getHandRankingEnum() {
        return handRankingEnum;
    }

    public RankAndRemainingCards getRankAndRemainingCards() {
        return rankAndRemainingCards;
    }
}
