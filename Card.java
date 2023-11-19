public class Card{

    private String cardType;
    private String cardName;
    private int cardHealth;
    private int cardPower;

    public Card(String t, String n, int h, int p){
        cardType = t;
        cardName = n;
        cardHealth = h;
        cardPower = p;
    }

    public String getName(){
        return cardName;
    }

    public String getType(){
        return cardType;
    }

    public int getHealth(){
        return cardHealth;
    }

    public int getPower(){
        return cardPower;
    }

    public void takeDamage(int d){
        cardHealth -= d;
    }
}