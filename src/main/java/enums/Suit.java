package enums;

public enum Suit {

    HEARTS,
    CLUBS,
    SPADES,
    DIAMONDS;

    public static Suit getSuitFromChar(char c) {
        switch (c) {
            case 'H':
                return HEARTS;
            case 'C':
                return CLUBS;
            case 'S':
                return SPADES;
            case 'D':
                return DIAMONDS;
            default:
                throw new IllegalArgumentException();
        }
    }
}
