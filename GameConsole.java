import java.util.Scanner;


public class GameConsole{

    public static void main(String[] args){
        Player player1 = new Player(args[0]);
        Player player2 = new Player(args[1]);
        System.out.println("Welcome, " + player1.getName() + " and " + player2.getName() +"!" );
        System.out.println("Do you want the cards to be dealt in random?\n");
        Scanner randomScanner = new Scanner(System.in);
        GameMaster gameMaster = new GameMaster(player1, player2);
        String answer = randomScanner.nextLine();
        //System.out.println(gameMaster.dealCard());
        if(answer.equals("yes")){
            System.out.println("The game begins.\n");
            System.out.println(gameMaster.randomDealCard());
        }else if(answer.equals("no")){
            System.out.println("The game begins.\n");
            System.out.println(gameMaster.dealCard());
        }
        //System.out.print(gameMaster.dealCard());
        Scanner scanner = new Scanner(System.in);
        while(gameMaster.hasWinner() == false){
            System.out.print("Attack or Swap? ");
            System.out.println(gameMaster.play(scanner.nextLine()));
        }
        System.out.println(gameMaster.gameReport());
    }
    
}