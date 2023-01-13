package models;

import enums.HandRankingEnum;
import enums.Value;
import utils.HandEvaluator;
import utils.RankAndRemainingCards;

import java.util.*;

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
        Arrays.sort(cards);
    }

    /**
     * This method is the one that does the final evaluation of each hand's ranking
     * @return
     */
    public HandDeepEval getHandDeepEval() {
        Optional<HandDeepEval> optionalDeep = HandEvaluator.getFlushOrStraightResult(cards);
        if (optionalDeep.isPresent()) {
            return optionalDeep.get();
        }

        Map<Value, Integer> map = HandEvaluator.numOfInstances(cards);

        if (map.containsValue(4)) {
            return new HandDeepEval(HandRankingEnum.FOUR_OF_A_KIND, HandEvaluator.splitByNumOfInstances(cards, map , 4));
        }
        else if (map.containsValue(3) && map.containsValue(2)) {
            return new HandDeepEval(HandRankingEnum.FULL_HOUSE, HandEvaluator.splitByNumOfInstances(cards,map,3));
        }
        else if (map.containsValue(3)) {
            return new HandDeepEval(HandRankingEnum.THREE_OF_A_KIND, HandEvaluator.splitByNumOfInstances(cards, map, 3));
        }
        else if (map.containsValue(2)) {
            return HandEvaluator.pairs(cards, map);
        }
        else {
            return new HandDeepEval(HandRankingEnum.HIGH_CARD, new RankAndRemainingCards(Arrays.asList(cards), Collections.emptyList()));
        }
    }


}
