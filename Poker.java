/*

This Poker program gets String array of inputs from the command line where each
string represents a card. 

If the number of String input is less than 5 or not a multiple of 5 or if any 
string of length is not equal to 2 then the program will exit with an error.

Each string in the input is classified as card input and each set of 5 cards
is taken as a hand. If the input has only one hand (set of 5 card strings input)
then the program will just display the description of the hand type for the
hand. if the input has more than one hands (multiple set of 5 card Strings) then
the program will display the hand description for each hand and also evaluates
all hand inputs and displays which hand or player wins or players drawn.  

Variables:
handList - contains the list of hand objects formed from the command line input.

*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Poker {
    public static void main(String[] args) {

        List<Hand> handList = new ArrayList<Hand>();

        if ((args.length%5) != 0 || args.length == 0) {
            System.out.println("Error: wrong number of arguments; must "
                    + "be a multiple of 5");
            System.exit(1);
        }
        
        //logic to club the command line argument into set of 5 cards in a
        //string and create a hand object for each set. These hand objects are
        //then added to the List of hand objects - handList 
        int noOfHands = args.length / 5;
        for (int handNumber = 1; handNumber <= noOfHands; handNumber++) {
            String[] InputHand = new String[5];
            for (int handIndex = 0; handIndex < 5; handIndex++) {
                InputHand[handIndex] = args[((handNumber - 1) * 5) 
                        + (handIndex)].toUpperCase();
            }
            handList.add(new Hand(InputHand, handNumber));
        }
        
        //logic executes only if more than one hand
        if (noOfHands != 1) {
            
            //sort all hand objects using comparator in hand.java.
            Collections.sort(handList);
            Hand highRankHand = null;
            
            //logic to identify the winning hand or drawn hands.
            String playerWinResultString = "";
            String playerStringSeparator = ", ";
            int NoOfPlayersWon = 1;
            Iterator<Hand> handListIterator = handList.iterator();
            while (handListIterator.hasNext()) {
                Hand currentHand = handListIterator.next();
                if (highRankHand == null) {
                    highRankHand = currentHand;
                    playerWinResultString = playerWinResultString 
                            + currentHand.getHandNumber() 
                            + playerStringSeparator;
                } else if (currentHand.equals(highRankHand)){
                    playerWinResultString = playerWinResultString 
                            + currentHand.getHandNumber() 
                            + playerStringSeparator;
                    NoOfPlayersWon++;
                }
            }
            
            //logic to display the winning player or players drawn.
            int lastIndexNumber 
                    = playerWinResultString.lastIndexOf(playerStringSeparator);
            int lastBeforeIndexNumber 
                    = playerWinResultString.substring(0,
                            lastIndexNumber).lastIndexOf(playerStringSeparator);
            if (NoOfPlayersWon == 1) {
                playerWinResultString 
                        = "Player " + playerWinResultString.substring(0,
                                lastIndexNumber) + " wins.";
            } else {
                playerWinResultString 
                    = "Players " + playerWinResultString.substring(0,
                                            lastBeforeIndexNumber)+ " and " 
                        + playerWinResultString.substring(lastBeforeIndexNumber
                                +playerStringSeparator.length(),lastIndexNumber)
                        + " draw.";
            }
            System.out.println(playerWinResultString);

        }
    }
}
