/*
This Card class represents a card object. 

Variables:

rankCode - identifies the unique rankCode of the Card.
suitCode - identifies the unique suitCode of the card.

Constructor : 
    Constructor receives a card String as input and compares the rankInput 
    character and SuitInput character with rankInput and SuitInput properties in 
    Rank and Suit enum class and when a match is found corresponding rankCode
    and suitCode values are assigned to the class variables.
*/

public class Card implements Comparable<Card>{
    private int rankCode = 0;
    private int suitCode = 0;
    
    
    public Card(String card){
        String rankInput = String.valueOf(card.charAt(0));
        String suitInput = String.valueOf(card.charAt(1));
        for(Rank rank : Rank.values()){
            if(rankInput.equals(rank.getRankInput())){
                this.setRankCode(rank.getRankCode());
            }
        }

        for(Suit suit : Suit.values()){
            if(suitInput.equals(suit.getSuitInput())){
                this.setSuitCode(suit.getSuitCode());
            }
        }

        if(this.rankCode == 0 || this.suitCode ==0 || card.length()!=2){
            System.out.println("Error: invalid card name '" + card + "'");
            System.exit(1);
        }
    }
    
    //provides a comparator method for Collection.Sort method to sort the cards 
    //in a list of card Objects based on the rankCode. 
    @Override
    public int compareTo(Card card){
        return this.rankCode - card.rankCode;
    }

    public int getRankCode() { return rankCode; }

    private void setRankCode(int rankCode) { this.rankCode = rankCode; }

    public int getSuitCode() { return suitCode; }

    private void setSuitCode(int suitCode) { this.suitCode = suitCode; }
}
