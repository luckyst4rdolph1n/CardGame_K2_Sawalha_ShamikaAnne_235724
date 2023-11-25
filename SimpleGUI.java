import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGUI{

    private JFrame frame;
    private int width;
    private int height;
    private Container cp;

    private GridBagLayout gridBag;
    private GridBagConstraints gbc;
    //private BoxLayout box;

    private JPanel upperPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel midPanel;

    private JTextField player1TF;
    private JTextField player2TF;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel question;
    private JButton startGame;
    private JButton attack;
    private JButton swap;
    private JButton displayStats;
    private JTextArea area;

    public GameMaster gameMaster;
    private String name1, name2;
    public Player player1;
    public Player player2;


    public SimpleGUI(int w, int h){
        frame = new JFrame();
        width = w;
        height = h;
        cp = frame.getContentPane();

        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        //box = new BoxLayout(, h);

        upperPanel = new JPanel();
        mainPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();

        player1Label = new JLabel("Player 1:");
        player2Label = new JLabel("Player 2:");
        question = new JLabel("Attack or Swap?");
        player1TF = new JTextField(20);
        player2TF = new JTextField(20);
        startGame = new JButton("Start Game");
        attack = new JButton("Attack");
        swap = new JButton("Swap");
        displayStats = new JButton("Display Game Report");
        area = new JTextArea(25,75);
        
        //player1 = new Player(name1);
        //player2 = new Player(name2);
        //gameMaster = new GameMaster(player1, player2);

    }

    public void setUpGUI(){
        frame.setSize(width, height);
        frame.setTitle("Card Game");

        //gbc.insets = new Insets(5, 2, 5, 2);
        //cp.setLayout(gridBag);
        
        upperPanel.setLayout(gridBag);
        upperPanel.setAlignmentX(mainPanel.LEFT_ALIGNMENT);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 2, 5, 2);
        upperPanel.add(player1Label, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        upperPanel.add(player1TF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        upperPanel.add(player2Label, gbc);
       
        gbc.gridx = 2;
        gbc.gridy = 2;
        upperPanel.add(player2TF, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 2, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(startGame, gbc);

        midPanel.setLayout(gridBag);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(3, 3, 2, 0);
        midPanel.add(question, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        midPanel.add(attack, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        midPanel.add(swap, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        midPanel.add(displayStats, gbc);

        //bottomPanel.setLayout(gridBag);

        //gbc.gridx = 2;
        //gbc.gridy = 1;
        //gbc.gridwidth = 10;
        //gbc.gridheight = ;
        //gbc.anchor = GridBagConstraints.CENTER;
        //gbc.fill = GridBagConstraints.NONE;
        bottomPanel.add(area);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(upperPanel);
        mainPanel.add(midPanel);
        mainPanel.add(bottomPanel);
        
        cp.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
        
    }
    
    
    String playerMove;
    public void gameListeners(){
        ActionListener startingActions = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                String startingText = "";
                name1 = player1TF.getText();
                name2 = player2TF.getText();
                player1 = new Player(name1);
                player2 = new Player(name2);
                startingText += "Welcome, " + name1 + " and " + name2 +"!\nThe game begins.\n\n";
                area.setText(startingText);
                gameMaster = new GameMaster(player1, player2);
                startingText += gameMaster.dealCard();
                area.setText(startingText);
            }
        };
        startGame.addActionListener(startingActions);

        ActionListener moveButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                Object move = ae.getSource();
                    if(move == attack){
                        playerMove = "attack";
                    }else if(move == swap){
                        playerMove = "swap";
                    }
            
                    String game = gameMaster.play(playerMove);
                    area.setText(game);

                    if(gameMaster.hasWinner() == true){
                        area.setText("Game Over. Click 'Display Game Report' to see game stats.");
                    }    
            }
        };
        attack.addActionListener(moveButton);
        swap.addActionListener(moveButton);

        ActionListener gameStats = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                area.setText(gameMaster.gameReport());
            }
        };
        displayStats.addActionListener(gameStats);
    }
}