/*

This Hand class represent one hand (set of 5 cards).
Assumption : A hand cannot have duplicate cards. 
             i.e. A hand cannot have two "Club of 2's"

Variables:

cardList       - contains the list of card objects each representing a card in a 
                 hand.
handNumber     - represents the player number.
handCode       - identifies the handtype.
handDesc       - used to store the corresponding hand Description from handType 
                 enum property.
isRankSequence - used to represent whether the card ranks in the hand form a 
                 sequence.
isSameSuit     - used to represent whether the card suits are all of same type.
rankOrderArray - contains an array of RankCodes in a order of what rank has to 
                 be compared first to last while comparing two hands of same 
                 type.
                    
                 Ex. for hand input "2D 2H 4C 6D 6D" -> the array will be [2,6]
                     for hand input "QS JD TS 9S KS" -> [13, 12, 11, 10, 9]

Constructor:

Constructor receives two input:
    1. String array of cardString each representing a card.
    2. handNumber to identify the player number.

The constructor creates card objects for each card and forms a List of cards as 
cardList variable. Then required methods are called to analyse and determine the
hand types and rankOrderArray, and display the hand description.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;

public class Hand implements Comparable<Hand>{
    private List<Card> cardList = new ArrayList<>();
    private int handNumber;
    private int handCode;
    private String handDesc;
    private boolean isRankSequence = true;
    private boolean isSameSuit = true;
    private int[] rankOrderArray;

    public Hand(String[] inputHand, int handNumber){
        this.setHandNumber(handNumber);
        for (String cardString:inputHand) {
            this.getCardList().add(new Card(cardString));
        }

        Collections.sort(this.getCardList());
        HashMap<Integer,Integer> rankMap 
                = this.checkRanksAndSuits(this.getCardList());
        this.setRankOrderArray(this.determineHandType(rankMap));
        this.displayHandDescription(this.getRankOrderArray(),
                this.getHandNumber(),this.getHandDesc());
    }
    
    /*
    the checkRanksAndSuits method takes the cardList as input and forms rankMap 
    which contains unique Ranks in a hand as keys and values as the number of
    types the corresponding ranks repeated in the hand. It also return the
    HashMap - rankMap.
    */
    private HashMap<Integer, Integer> checkRanksAndSuits(List<Card> cardList){
        HashMap<Integer,Integer> rankMap = new HashMap<>();
        int previousRankCode = 0;
        int rankCount = 1;
        int currentRankCode, currentSuitCode;
        int previousSuitCode = 0;
        for (Card currentCard: cardList) {
            currentRankCode = currentCard.getRankCode();
            currentSuitCode = currentCard.getSuitCode();
            //logic to check if the current card and previous card are in 
            //sequence or not. Also identifies repeated ranks and evaluate 
            //counts of the repeated ranks.
            if(previousRankCode != 0){
                if(currentRankCode - previousRankCode != 1){
                    this.setRankSequence(false);
                }
                if(currentRankCode - previousRankCode == 0){
                    rankCount++;
                }else{
                    rankCount = 1;
                }
            }
            previousRankCode = currentRankCode;
            rankMap.put(currentRankCode, rankCount);
            
            //logic to check if all suits in the hand are same.
            if(previousSuitCode == 0){
                previousSuitCode = currentSuitCode;
            }else if(previousSuitCode != currentSuitCode){
                this.setSameSuit(false);
            }
        }
        return rankMap;
    }
    
    /*
    the determineHandType method takes rankMap as input and, forms and returns 
    the rankOrderArray. 
    */
    private int[] determineHandType(Map rankMap){
        //creates a list of map entries from the Map so as to sort the Map 
        //entries based on Values first and then keys. This will give the order
        //of ranks of what has to be sorted first to last.
        List<Map.Entry<Integer,Integer>> rankMapEntryList 
                = new ArrayList<Map.Entry<Integer,Integer>>(rankMap.entrySet());
        rankMapEntryList.sort(new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> rankMapPair1,
                    Map.Entry<Integer,Integer> rankMapPair2) {
                if (rankMapPair1.getValue().equals(rankMapPair2.getValue())) {
                    return rankMapPair2.getKey() - rankMapPair1.getKey();
                } else {
                    return rankMapPair2.getValue() - rankMapPair1.getValue();
                }
            }
        });

        int[] rankOrderArray = new int[rankMap.size()];
        int[] numOfRankOrderArray = new int[rankMap.size()];
        int rankOrderArrayIndex = 0;
        for (Map.Entry<Integer,Integer> rankMapEntry: rankMapEntryList ) {
            rankOrderArray[rankOrderArrayIndex] = rankMapEntry.getKey();
            numOfRankOrderArray[rankOrderArrayIndex] = rankMapEntry.getValue();
            rankOrderArrayIndex++;
        }

        int maxNumOfRank = numOfRankOrderArray[0];
        //logic to evaluates the hand Type comparing the 
        //4 properties(rankMapLength, maxNumOfRank, isRankSequence and 
        //isSameSuit) of this hand object to all of the handtpe properties in
        //the handType enum and assign the handType code and description if a
        //match is found.
        for(HandType handtype : HandType.values()){
            if(handtype.getRankMapLength()==rankMap.size() 
                    && handtype.getMaxNumOfRank() == maxNumOfRank
                    && handtype.isRankSequence() == this.isRankSequence() 
                    && handtype.isSameSuit() == this.isSameSuit()){
                
                this.setHandCode(handtype.getHandCode());
                this.setHandDesc(handtype.getHandDesc());
            }
        }
        return rankOrderArray;
    }
    
    /*
    displayHandDescription method outputs the required hand description by 
    taking first two ranks from rankOrderArray and the player Number.
    */
    private void displayHandDescription(int[] rankOrderArray, int handNumber, 
            String handDescription){
        String FirstRankDesc = "";
        String SecondRankDesc = "";
        for(Rank rank : Rank.values()){
            if(rankOrderArray[0]==rank.getRankCode()){
                if(rank.getRankCode() <=10){
                    FirstRankDesc = Integer.toString(rank.getRankCode());
                }else{
                    FirstRankDesc = rank.name();
                }
            }

            if(rankOrderArray[1]==rank.getRankCode()){
                if(rank.getRankCode() <=10){
                    SecondRankDesc = Integer.toString(rank.getRankCode());
                }else{
                    SecondRankDesc = rank.name();
                }
            }
        }
        System.out.printf("Player " + handNumber + ": " + handDescription,
                FirstRankDesc,SecondRankDesc);
    }
    
    /*
    provides a comparator method for Collection.Sort method to sort the hands 
    in a list of hand objects based on the handCode first and then based on the 
    Ranks in the rankOrderArray from index 0 to last index.
    */
    @Override
    public int compareTo(Hand hand){
        int inputHandCode = hand.getHandCode();
        int[] inputRankOrderArray = hand.getRankOrderArray();
        if(inputHandCode != this.getHandCode()){
            return this.getHandCode() - inputHandCode;
        }else{
            for(int rankOrderArrayIndex = 0 ; rankOrderArrayIndex 
                    < this.getRankOrderArray().length; rankOrderArrayIndex++){
                
                if(inputRankOrderArray[rankOrderArrayIndex] 
                        != this.getRankOrderArray()[rankOrderArrayIndex]){
                    
                    return inputRankOrderArray[rankOrderArrayIndex] 
                            - this.getRankOrderArray()[rankOrderArrayIndex];
                }
            }
            return 0;
        }

    }
    
    //equals method provides a way to compare two hand objects using the
    //handCode and the values in rankOrderArray in the corresponding indexes.
    public boolean equals(Hand hand){
        return this.getHandCode() == hand.getHandCode() &&
               Arrays.equals(this.getRankOrderArray(),hand.getRankOrderArray());
    }

    public int getHandNumber() {
        return handNumber;
    }

    private void setHandNumber(int handNumber) {
        this.handNumber = handNumber;
    }

    private List<Card> getCardList() {
        return cardList;
    }

    private void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    private boolean isRankSequence() {
        return isRankSequence;
    }

    private void setRankSequence(boolean rankSequence) {
        isRankSequence = rankSequence;
    }

    private boolean isSameSuit() {
        return isSameSuit;
    }

    private void setSameSuit(boolean sameSuit) {
        isSameSuit = sameSuit;
    }

    private int getHandCode() {
        return handCode;
    }

    private void setHandCode(int handCode) {
        this.handCode = handCode;
    }

    private String getHandDesc() {
        return handDesc;
    }

    private void setHandDesc(String handDesc) {
        this.handDesc = handDesc;
    }

    private int[] getRankOrderArray() {
        return rankOrderArray;
    }

    private void setRankOrderArray(int[] rankOrderArray) {
        this.rankOrderArray = rankOrderArray;
    }

}
