/*

This Rank enum class acts as an environment file and contains properties of all 
possible Ranks in a deck of card.

Each rank property is assigned two values:
    rankCode  - uniquely identifies the Rank.
                It also provides weightage to the Rank.
                Ex. ACE has highest weightage and Rank two has lowest weightage.
    rankInput - used to compare with the Rank input of each card in the main 
                argument.
*/
public enum Rank {
    Two(2, "2"),
    Three(3,"3"),
    Four(4,"4"),
    Five(5,"5"),
    Six(6,"6"),
    Seven(7,"7"),
    Eight(8,"8"),
    Nine(9,"9"),
    Ten(10,"T"),
    Jack(11,"J"),
    Queen(12,"Q"),
    King(13,"K"),
    Ace(14,"A");

    private final int rankCode;
    private final String rankInput;

    Rank(int rankCode,String rankInput){
        this.rankCode = rankCode;
        this.rankInput = rankInput;
    }

    public int getRankCode() {
        return rankCode;
    }

    public String getRankInput() {
        return rankInput;
    }
}
