import java.util.Scanner;
public class GameConsole{

    public static void main(String[] args){
        Player player1 = new Player(args[0]);
        Player player2 = new Player(args[1]);
        GameMaster gameMaster = new GameMaster(player1, player2);
        System.out.println("Welcome, " + player1.getName() + " and " + player2.getName() +"!" );
        System.out.println("The game begins.\n");

        if(args.length == 2){
            System.out.println(gameMaster.dealCard());
        }else if(args.length == 3 && args[2].equals("random")){
            System.out.println(gameMaster.randomDealCard());
        }

        Scanner scanner = new Scanner(System.in);
        while(gameMaster.hasWinner() == false){
            System.out.print("Attack or Swap? ");
            System.out.println(gameMaster.play(scanner.nextLine()));
        }
        
        System.out.println(gameMaster.gameReport());
    }
    
}