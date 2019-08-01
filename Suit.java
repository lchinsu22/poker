/*

This Suit enum class acts as an environment file and contains properties of all 
possible Suits in a deck of card.
Each Suit property is assigned two values:
    suitCode  - uniquely identifies the Suit.
    suitInput - used to compare with the Suit input of each card in the main 
                argument.
*/
public enum Suit {
    CLUBS(1,"C"),
    DIAMONDS(2,"D"),
    HEARTS(3,"H"),
    SPADES(4,"S");

    private final int suitCode;
    private final String suitInput;

    Suit(int suitCode,String suitInput){
        this.suitCode = suitCode;
        this.suitInput = suitInput;
    }

    public int getSuitCode() {
        return suitCode;
    }

    public String getSuitInput() {
        return suitInput;
    }

}
