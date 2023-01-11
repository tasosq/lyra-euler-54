package utils;

import models.Card;

import java.util.List;

/**
 * This is a utility class, used to store the list of cards that combined to produce the ranking of the hand, and the list of the rest of them
 */
public class RankAndRemainingCards {

    private final List<Card> rankCards;

    private final List<Card> remainingCards;

    public RankAndRemainingCards(List<Card> rankCards, List<Card> remainingCards) {
        this.rankCards = rankCards;
        this.remainingCards = remainingCards;
    }

    public List<Card> getRankCards() {
        return rankCards;
    }

    public List<Card> getRemainingCards() {
        return remainingCards;
    }
}
