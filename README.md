##  Poker Hands - The Euler Project #54

 This repo contains the solution to the [PokerHands](http:///projecteuler.net/problem=54 "PokerHands") problem from the Euler Project.

###### The Solution Overview:


So at first, we read the cards information. For now, the implementation only works for that specific text format. It could be easily refactored to accept other text formats aswell, but thats out of scope for this solution.

We split each line of text to a pair of Hands. In order to follow OOP principles, I created 5 different model classes: ***Card, Hand, HandDeepEval, PlayerHands, RankAndRemainingCards***

Moreover, I created 4 Enums to hold the never-changing fixed values:*** Value, Suit, HandRankingEnum, Winner.***

The heavy lifting is done by the 2 utility classes: **HandEvaluator, WinnerEvaluator**.


------------


###### So lets follow the flow of this pair of Hands, as an example:
 - Hand 1 :  **2H 2D 4C 4D 4S**
 - Hand 2 :  **3C 3D 3S 9S 9D**

Those 2 Hand instances are created after reading the line of the text file, through the PlayerHands class.

1. The Hand gets evaluated according to the rules set by the problem. First we check for the **Hand Rankings** *[One Pair, Two Pairs , ... , Royal Flush]*.

	If they were different we would be done by now!

2. In case the Hand Rankings are equal, we check the Values of the Hand Ranking Cards.
3. If those values are equal aswell, then we check the Values of the Remaining Cards.


------------


#### The OOP principles applied during the development of the solution:
- **Encapsulation** : Private fields with getters. We do not need any setters since the fields are set once in creation, when we read the .txt file and we do not need to alter them.

- **Composition** : Classes with other classes as fields, avoiding the *is-a*  association. I do not like inheritance either.
