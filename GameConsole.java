import java.io.FileNotFoundException;
import java.util.Scanner;
public class GameConsole{

    public static void main(String[] args)throws FileNotFoundException{
        Player player1 = new Player(args[0]);
        Player player2 = new Player(args[1]);
        boolean r = false;
        boolean n = false;
        if(args.length == 3){
            String thirdArg = args[2];
            if(thirdArg.equals("random")){
                r = true;
            }else if(thirdArg.equals("newCards.txt")){
                n = true;
            }
        }else if(args.length == 4){
            r = true;
            String newFile = args[3];
            if(newFile.equals("newCards.txt")){
                n = true;
            }
        }
        
        GameMaster gameMaster = new GameMaster(player1, player2, r, n);
        System.out.println("Welcome, " + player1.getName() + " and " + player2.getName() +"!" );
        System.out.println("The game begins.\n");
        System.out.println(gameMaster.dealCard());

        Scanner scanner = new Scanner(System.in);
        while(gameMaster.hasWinner() == false){
            System.out.print("Attack or Swap? ");
            System.out.println(gameMaster.play(scanner.nextLine()));
        }
        
        System.out.println(gameMaster.gameReport());
    }
    
}