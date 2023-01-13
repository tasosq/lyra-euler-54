package utils;

import enums.HandRankingEnum;
import enums.Suit;
import enums.Value;
import models.Card;
import models.HandDeepEval;
import models.RankAndRemainingCards;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

    /**
     * A utility method that evaluates the amount of pairs that exist in a hand, and which cards those are.
     * @param cards
     * @param map (the map that gets returned by the {@link  #numOfInstances(Card[])} method
     * @return
     */
    public static HandDeepEval pairs(Card[] cards, Map<Value, Integer> map) {
        RankAndRemainingCards rankAndRemainingCards = splitByNumOfInstances(cards, map, 2);
        if (rankAndRemainingCards.getRankCards().size()==4) {
            return new HandDeepEval(HandRankingEnum.TWO_PAIR, rankAndRemainingCards);
        } else {
            return new HandDeepEval(HandRankingEnum.PAIR, rankAndRemainingCards);
        }

    }

    /**
     * A utility method that takes the map created by the {@link #numOfInstances(Card[])} method, and splits the cards according to number of instances
     * of them in the Hand (while also adding those to the "ranking cards", and putting the rest of them in the remaining cards)
     * (e.g. the Hand: 2C 3S 8S 8D TD -> splitByNumOfInstances(cards, map, 2) the value 8 exists twice,
     * thus taking those 2 cards and putting them in the winAndRemainingCards=true list. The other 3 cards are going in the winAndReaminingCards = false list.
     * @param cards
     * @param map
     * @param numOfInstances
     * @return
     */
    public static RankAndRemainingCards splitByNumOfInstances(Card[] cards, Map<Value, Integer> map, int numOfInstances){
        Set<Value> winValues = new HashSet<>();
        for (Entry<Value,Integer> entrySet : map.entrySet()){
            if (entrySet.getValue() == numOfInstances) {
                winValues.add(entrySet.getKey());
            }
        }
        Map<Boolean, List<Card>> winAndRemainingCards = Arrays.stream(cards).collect(Collectors.partitioningBy(card -> winValues.contains(card.getValue())));
        return new RankAndRemainingCards(winAndRemainingCards.get(true), winAndRemainingCards.get(false));
    }


    /**
     * A utility method that goes through the cards. Creates a Hashmap and if the card already exists in the map,
     * it just adds +1 to the value(number of instances of said Card) of that key (Card).
     * Otherwise, it puts the new key:value inside the map.
     * @param cards
     * @return a Map<Value,Integer>, where Value is the Value of the Card,
     * and Integer is the number of instances of said card in the Hand
     */
    public static Map<Value, Integer> numOfInstances(Card[] cards) {
        Map<Value, Integer> map = new HashMap<>();
        for (Card card : cards) {
            Value currentValue = card.getValue();
            if (map.containsKey(currentValue))
                map.put(currentValue, map.get(currentValue) + 1);
            else
                map.put(currentValue, 1);
        }
        return map;
    }
}
