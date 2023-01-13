package models;

import enums.Suit;
import enums.Value;

/**
 * This is the Card class. It consists of 2 fields, the suit of the card and the value of the card. Chose to implement the constructor accepting a string argument,
 * since we are only going to get cards as inputs from the text file, and it saves some hustle down the road.
 */
public class Card implements Comparable<Card>{

    private final Value value;
    private final Suit suit;


    public Card(String cardStr) {
        this.value = Value.getValueFromChar(cardStr.charAt(0));
        this.suit = Suit.getSuitFromChar(cardStr.charAt(1));

    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        return  Integer.compare(this.value.ordinal(),o.value.ordinal());
    }
}
