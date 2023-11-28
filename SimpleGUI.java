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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class SimpleGUI{

    private JFrame frame;
    private int width;
    private int height;
    private Container cp;

    private GridBagLayout gridBag;
    private GridBagConstraints gbc;

    private JPanel upperPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel midPanel;

    private JTextField player1TF;
    private JTextField player2TF;
    private JLabel player1Label;
    private JLabel player2Label;
    private JCheckBox randomDeal;
    private JLabel question;
    private JButton startGame;
    private JButton attack;
    private JButton swap;
    private JButton displayStats;
    private JButton selectDeck;
    private JTextArea area;

    private GameMaster gameMaster;
    private String name1, name2;
    private Player player1;
    private Player player2;
    private boolean randomArg;
    private boolean inputDeck;

    /**
        Constructor initializes the variables
        @param w width of the frame
        @param h height of the frame

    **/

    public SimpleGUI(int w, int h){
        frame = new JFrame();
        width = w;
        height = h;
        cp = frame.getContentPane();

        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();

        upperPanel = new JPanel();
        mainPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();

        player1Label = new JLabel("Player 1:");
        player2Label = new JLabel("Player 2:");
        question = new JLabel("Attack or Swap?");
        randomDeal = new JCheckBox("Random Deal", false);
        player1TF = new JTextField(20);
        player2TF = new JTextField(20);
        startGame = new JButton("Start Game");
        attack = new JButton("Attack");
        swap = new JButton("Swap");
        selectDeck = new JButton("Upload New Deck");
        displayStats = new JButton("Display Game Report");
        area = new JTextArea(25,75);

        inputDeck = false;

    }

    /**
        Sets up the components of the GUI
    **/

    public void setUpGUI(){
        frame.setSize(width, height);
        frame.setTitle("Card Game");
        
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

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 2, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(selectDeck, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 2, 4, 0);
        gbc.fill = GridBagConstraints.NONE;
        upperPanel.add(randomDeal, gbc);

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
    
    /**
        Creates button listeners
    **/

    String playerMove;
    public void gameListeners(){

        ActionListener uploadNewDeck = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                inputDeck = true;
                JFileChooser file_upload = new JFileChooser();
                file_upload.showOpenDialog(null);
                File fromPath = new File(file_upload.getSelectedFile().getAbsolutePath());
                File toPath = new File("./newCards.txt");
                try{
                    Files.move(fromPath.toPath(), toPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                catch(IOException e){}
                
            }
        }; selectDeck.addActionListener(uploadNewDeck);

        ActionListener randomCheckBox = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){

                if(randomDeal.isSelected()){
                    randomArg = true;
                }else{
                    randomArg = false;
                }
            }
        };

        randomDeal.addActionListener(randomCheckBox);
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
                try{
                    gameMaster = new GameMaster(player1, player2, randomArg, inputDeck);
                }
                catch(FileNotFoundException e){}
                startingText += gameMaster.dealCard();
                area.setText(startingText);
            }
        };
        startGame.addActionListener(startingActions);

        ActionListener moveButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                Object move = ae.getSource();
                if(gameMaster.hasWinner() == false){
                    if(move == attack){
                        playerMove = "attack";
                    }else if(move == swap){
                        playerMove = "swap";
                    }
                    String game = gameMaster.play(playerMove);
                    area.setText(game);
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