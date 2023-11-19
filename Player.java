import java.util.ArrayList;

public class Player{

    private String playerName;
    private int tokenCounter;
    private Card[] playerDeck;
    private boolean fullHand;
    private static final int MAX = 5;
    //private int cardIndex;

    public Player(String n){
        playerName = n;
        tokenCounter = 0;
        playerDeck = new Card[MAX];
        fullHand = false;
        //cardIndex = 0;
    }

    public String getName(){
        return playerName;
    }

    public void drawCard(Card c){
        for(int i=0; i<MAX; i++){
            if(playerDeck[i] == null){
                playerDeck[i] = c;
                break;
            }
        }
        //playerDeck[cardIndex] = c;
        //cardIndex += 1;
        }

    public boolean handIsFull(){
        int nonNull = 0;
        for(int i=0; i<MAX; i++){
            if(playerDeck[i] != null){
                nonNull += 1;
            }
        }
        if(nonNull == MAX){
            fullHand = true;
            //cardIndex = -1;
        } else{
            fullHand = false;
        }return fullHand;
    }

    public Card getActiveCard(){
        return playerDeck[0];
    }

    private int findCard(){
        int[] products = new int[playerDeck.length];
        for(int n = 1; n < playerDeck.length; n++){
            products[n-1] = playerDeck[n].getHealth() * playerDeck[n].getPower();
        }
        int same = 0;
        int toSwap = products[0];
        int index = 0;
        for(int i=0; i<products.length-1; i++){
            if(toSwap > products[i+1]){
                toSwap = toSwap; //[a,) c, e, g, i]
            }else if(toSwap < products[i+1]){
                toSwap = products[i+1];
                index = i+1;
            }else{
                same += 1;
                continue;
            }
        }if(same == products.length){
                index = -1;
            }return index;
    }

    public String swap(){
        String swapMessage = "";
        if(playerDeck.length > 2){
            int index = findCard();
            Card s = playerDeck[0];
            playerDeck[0] = playerDeck[index+1];
            playerDeck[index+1] = s;
            swapMessage += playerDeck[0].getName() + " is now active with " + playerDeck[0].getHealth() + " health.\n";
            //System.out.println(swapMessage);
        }else if(playerDeck.length == 2){
            Card s = playerDeck[0];
            playerDeck[0] = playerDeck[-1];
            playerDeck[-1] = s;
            swapMessage += playerDeck[0].getName() + " is now active with " + playerDeck[0].getHealth() + " health.\n";
            //System.out.println(swapMessage);
        }else{
            swapMessage += playerName + "has no other card to swap with. Turn forfeited.";
            //System.out.println(swapMessage);
        }return swapMessage;
    }

    public void discard(){
        //Card toReplace = playerDeck[0]
        if(playerDeck.length > 1){
            for(int i=0; i<playerDeck.length-1; i++){
                playerDeck[i] = playerDeck[i+1];
            }
            playerDeck[4] = null;
        }else{
            playerDeck[0] = null;
        }
    }

    public void claimToken(){
        tokenCounter += 1;
    }

    public int getTokens(){
        return tokenCounter;
    }

    public String statusReport(){
        String statusReport = "";
        statusReport += getName() + "\n";
        for(Card playerCard: playerDeck){
            statusReport += "    " + playerCard.getName() + " : " + playerCard.getHealth() + "\n";
        }return statusReport;
    }
}
