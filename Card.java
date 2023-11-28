/**
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


public class Card{

    private String cardType;
    private String cardName;
    private int cardHealth;
    private int cardPower;

    /**
        Constructor initializes cardType, cardName, cardHealth, cardPower 
        to parameter values t, n, h, p, respectively
        
        @param t type of card
        @param n name of card
        @param h health of card
        @param p attack power of card

    **/

    public Card(String t, String n, int h, int p){
        cardType = t;
        cardName = n;
        cardHealth = h;
        cardPower = p;
    }

    /**
        Gets the name of the card
        @return cardName

    **/

    public String getName(){
        return cardName;
    }

    /**
        Gets the type of the card
        @return cardType

    **/

    public String getType(){
        return cardType;
    }

    /**
        Gets the health of the card
        @return cardHealth

    **/

    public int getHealth(){
        return cardHealth;
    }

    /**
        Gets the attack power of the card
        @return cardPower

    **/

    public int getPower(){
        return cardPower;
    }

    /**
        Updates the value of card health
        @param d value of damage

    **/

    public void takeDamage(int d){
        cardHealth -= d;
    }
}