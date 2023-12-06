/** The Player class represents a player's state throughout the game
    @author Shamika Anne E. Sawalha (235724)
    @version November 16, 2023
**/

/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.

I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
*/

public class Player{

    private String playerName;
    private int tokenCounter;
    private Card[] playerDeck;
    private boolean fullHand;
    private static final int MAX = 5;

    /**
        Constructor initializes playerName, tokenCounter, playerDeck, fullHand 
        to parameter value n, 0, an array of type Card, and false, respectively
        @param n name of player

    **/

    public Player(String n){
        playerName = n;
        tokenCounter = 0;
        playerDeck = new Card[MAX];
        fullHand = false;
    }

    /**
        Gets the name of the player
        @return playerName

    **/

    public String getName(){
        return playerName;
    }

    /**
        Puts cards in the player's hand as long as there is space in the array
        @param c card being drawn

    **/

    public void drawCard(Card c){
        //System.out.println(playerDeck.length);
        for(int i=0; i<MAX; i++){
            if(playerDeck[i] == null){
                playerDeck[i] = c;
                break;
            }
        }
    }

    /**
        Checks if the number of cards in the player's hand has reached the maximum number (5)
        @return fullHand

    **/

    public boolean handIsFull(){
        int nonNull = 0;
        for(int i=0; i<MAX; i++){
            if(playerDeck[i] != null){
                nonNull += 1;
            }
        }
        if(nonNull == MAX){
            fullHand = true;
        } else{
            fullHand = false;
        }return fullHand;
    }

    /**
        Gets the first card in the player's deck which is the active card
        @return playerDeck[0]

    **/

    public Card getActiveCard(){
        return playerDeck[0];
    }

    /**
        Goes through the inactive cards in the player's hand and determines the one with the highest determining product
        @return index the index of the card with highest determining product

    **/

    private int findCard(){
        int[] products = new int[playerDeck.length];
        for(int n = 1; n < playerDeck.length; n++){
            products[n-1] = playerDeck[n].getHealth() * playerDeck[n].getPower();
        }
        int same = 0;
        int toSwap = products[0];
        int index = 0;
        for(int i=1; i<products.length; i++){
            if(toSwap > products[i]){
                continue;
            }else if(toSwap < products[i]){
                toSwap = products[i];
                index = i;
            }else{
                same += 1;
                continue;
            }
        }if(same == products.length){
                index = -1;
            }return index;
    }

    /**
        Lets the player swap their card-in-play for another card in their deck
        @return swapMessage resulting message when the swap is done

    **/

    public String swap(){
        String swapMessage = "";
        if(playerDeck.length > 2){
            int index = findCard();
            Card s = playerDeck[0];
            playerDeck[0] = playerDeck[index+1];
            playerDeck[index+1] = s;
            swapMessage += playerDeck[0].getName() + " is now active with " + playerDeck[0].getHealth() + " health.\n";
        }else if(playerDeck.length == 2){
            Card s = playerDeck[0];
            playerDeck[0] = playerDeck[-1];
            playerDeck[-1] = s;
            swapMessage += playerDeck[0].getName() + " is now active with " + playerDeck[0].getHealth() + " health.\n";
        }else{
            swapMessage += playerName + "has no other card to swap with. Turn forfeited.";
        }return swapMessage;
    }

    /**
        Gets rid of the active card with health that's less than or equal to zero and replaces it with the next card in player's deck
    **/

    public void discard(){
        if(playerDeck.length > 1){
            for(int i=0; i<playerDeck.length-1; i++){
                playerDeck[i] = playerDeck[i+1];
            }
            playerDeck[4] = null;
        }else{
            playerDeck[0] = null;
        }
    }

    /**
        Increases the token counter by 1
    **/

    public void claimToken(){
        tokenCounter += 1;
    }

    /**
        Gets a player's current number of tokens
        @return tokenCounter total number of tokens
    **/

    public int getTokens(){
        return tokenCounter;
    }

    /**
        Displays the status of the cards in a player's hand
        @return statusReport the resulting string from each card's status

    **/

    public String statusReport(){
        String statusReport = "";
        statusReport += getName().toUpperCase() + "\n";
        for(Card playerCard: playerDeck){
            statusReport += "    " + String.format("%10s : %-10s\n", playerCard.getName(), playerCard.getHealth());
        }return statusReport;
    }
}
