import java.util.Scanner;


public class GameConsole{

    public static void main(String[] args){
        Player player1 = new Player(args[0]);
        Player player2 = new Player(args[1]);
        System.out.println("Welcome, " + player1.getName() + " and " + player2.getName() +"!" );
        System.out.println("The game begins.");
        GameMaster gameMaster = new GameMaster(player1, player2);
        gameMaster.dealCard();
        Scanner scanner = new Scanner(System.in);
        while(gameMaster.hasWinner() == false){
            System.out.print("Attack or Swap? ");
            System.out.println(gameMaster.play(scanner.nextLine()));
        }
        System.out.println(gameMaster.gameReport());
    }
    
}