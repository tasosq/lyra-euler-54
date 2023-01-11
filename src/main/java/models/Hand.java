package models;

/**
 * This is the Hand class, it consists of an array of {@link Card}
 */
public class Hand {

    private final Card[] cards;

    public Hand(Card[] cards) {
        this.cards = cards;
    }

    public Hand(String[] cardsStr) {
        cards = new Card[5];
        for (int i = 0; i < 5; i++){
            cards[i] = new Card(cardsStr[i]);
        }
    }



}
