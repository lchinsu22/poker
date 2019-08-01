/*

This handType enum class acts as an environment file and contains properties of 
all hand Types mentioned in the Assignment.

A Map variable - rankMap is constructed in hand.java from its input hand.
The rankMap contains unique Ranks in a hand as keys and values as the number of 
types the corresponding ranks are repeated in the hand.

Each handType is identified by the combination of below four properties:
1. rankMapLength  - The length or size of the rankMap provides the number of 
                    unique Rank in a given hand.
                    Ex. in hand Input - "2D 3H 2C 3S 2D", rankMapLength is 2.

2. maxNumOfRank   - The maximum value in the rankMap provides the maximum number 
                    of times a Rank is repeated in a hand.
                    Ex. in hand Input - "2D 3H 2C 3S 2D", maxNumOfRank is 3.

3. isRankSequence - The boolean value represents whether the Ranks in the hand 
                    form a sequence or not.

4. isSameSuit     - The boolean value represents whether all suits in the hands 
                    are of same suit or not.

The combination of above four properties uniquely identifies the handType and 
the values of the above 4 properties for each hand type are provided as values 
in the respective hand type properties.

Below two varibales are used in the enum to provide more information on handType

1. handCode  - uniquely identifies the hand type.
                It also provides weightage to the hand Type.
                Ex. StraightFlush has highest weightage and HighCard has lowest 
                    weightage.
2. handDesc  - used to get the handDescription that has to be displayed in the 
               output. The "%s" is included to accomadate the highest ranks and 
               second highest rank to be compared in the hand.

*/
public enum HandType {
    StraightFlush(1,"%s-high straight flush%n",1,5,true,true),
    FourOfAKind(2,"Four %ss%n",4,2,false,false),
    FullHouse(3,"%ss full of %ss%n",3,2,false,false),
    Flush(4,"%s-high flush%n",1,5,false,true),
    Straight(5,"%s-high straight%n",1,5,true,false),
    ThreeOfAKind(6,"Three %ss%n",3,3,false,false),
    TwoPair(7,"%ss over %ss%n",2,3,false,false),
    OnePair(8,"Pair of %ss%n",2,4,false,false),
    HighCard(9,"%s-high%n",1,5,false,false);

    private final int handCode;
    private final String handDesc;
    private final int maxNumOfRank;
    private final int rankMapLength;
    private final boolean isRankSequence;
    private final boolean isSameSuit;

    HandType(int handCode, String handDesc, int maxNumOfRank,int rankMapLength, 
            boolean isRankSequence, boolean isSameSuit){
        this.handCode = handCode;
        this.handDesc = handDesc;
        this.maxNumOfRank = maxNumOfRank;
        this.rankMapLength = rankMapLength;
        this.isRankSequence = isRankSequence;
        this.isSameSuit = isSameSuit;
    }

    public int getHandCode() {
        return handCode;
    }

    public String getHandDesc() {
        return handDesc;
    }

    public int getMaxNumOfRank() {
        return maxNumOfRank;
    }

    public int getRankMapLength() {
        return rankMapLength;
    }

    public boolean isRankSequence() {
        return isRankSequence;
    }

    public boolean isSameSuit() {
        return isSameSuit;
    }
}
