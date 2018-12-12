package com.dominicswaine.seg_agile_project.Board;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import com.dominicswaine.seg_agile_project.Logic.Parser;

/**
 * The main menu enables users to start a game, start a custom game, load a game and get help.
 *
 * @author David Mahgerefteh
 * @version 11/12/2018
 */
public class MainMenu {

    private JFrame frame;
    private JPanel containerOfEverything, containerOfText, containerOfButtons;
    private com.dominicswaine.seg_agile_project.Logic.Game game;
    private CustomGame customGame;

    /**
     * Construct the main menu window.
     */
    public MainMenu() {

        //Initialise the frame

        frame  = new JFrame();
        frame.setSize(new Dimension(475, 500));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Make and populate the panels

        makePanels();
        populateContainerOfText();
        populateContainerOfButtons();

        containerOfEverything.add(containerOfText);
        containerOfEverything.add(Box.createVerticalStrut(10));
        containerOfEverything.add(containerOfButtons);
        frame.add(containerOfEverything);
        frame.setTitle("Toguz Korgool - Main Menu");
        frame.setVisible(true);

    }

    /**
     * Generate all the panels required by the class.
     */
    private void makePanels() {

        containerOfEverything = new JPanel();
        containerOfEverything.setLayout(new BoxLayout(containerOfEverything, BoxLayout.Y_AXIS));
        containerOfText = new JPanel();
        containerOfText.setLayout(new BoxLayout(containerOfText, BoxLayout.Y_AXIS));
        containerOfButtons = new JPanel();
        containerOfButtons.setLayout(new BoxLayout(containerOfButtons, BoxLayout.Y_AXIS));

    }

    /**
     * Create the title and add it to its respective container.
     */
    private void populateContainerOfText() {

        JLabel title = new JLabel("Toguz Korgool");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("<html><p>Welcome to Toguz Korgool, by David Mahgerefteh" +
                " Ayberk Demirkol, Danilo Del Busso, Horia Pavel and Dominic Swaine. " +
                "To begin a game, press Start Game. To begin a custom game, press Custom Game.  " +
                "To load a custom game configuration you have created in the past, press Load Game " +
                "and select the file from the file chooser. For help, press the Help button. " +
                "</p></html>");

        instructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerOfText.add(Box.createVerticalStrut(20));
        containerOfText.add(title);
        containerOfText.setBorder(new EmptyBorder(0, 20, 0, 20));
        containerOfText.add(Box.createVerticalStrut(17));
        containerOfText.add(instructions);
        containerOfText.add(Box.createVerticalStrut(20));

    }

    /**
     * Create the buttons and add them to their respective container.
     */
    private void populateContainerOfButtons() {

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setName("startGameButton");
        startGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        startGameButton.addActionListener(e -> startGame());

        JButton customGameButton = new JButton("Custom Game");
        customGameButton.setName("customGameButton");
        customGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        customGameButton.addActionListener(e -> customGame());

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setName("loadGameButton");
        loadGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loadGameButton.addActionListener(e -> loadGame());

        JButton helpButton = new JButton("Help");
        helpButton.setName("helpButton");
        helpButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        helpButton.addActionListener(e -> help());

        JPanel containerOfButton1 = new JPanel();
        containerOfButton1.setLayout(new BorderLayout());
        containerOfButton1.add(startGameButton, BorderLayout.CENTER);
        JPanel containerOfButton2 = new JPanel();
        containerOfButton2.setLayout(new BorderLayout());
        containerOfButton2.add(customGameButton, BorderLayout.CENTER);
        JPanel containerOfButton3 = new JPanel();
        containerOfButton3.setLayout(new BorderLayout());
        containerOfButton3.add(loadGameButton, BorderLayout.CENTER);
        JPanel containerOfButton4 = new JPanel();
        containerOfButton4.setLayout(new BorderLayout());
        containerOfButton4.add(helpButton, BorderLayout.CENTER);

        containerOfButtons.add(containerOfButton1);
        containerOfButtons.add(Box.createVerticalStrut(15));
        containerOfButtons.setBorder(new EmptyBorder(0,75, 0, 75 ));
        containerOfButtons.add(containerOfButton2);
        containerOfButtons.add(Box.createVerticalStrut(15));
        containerOfButtons.setBorder(new EmptyBorder(0,75, 0, 75 ));
        containerOfButtons.add(containerOfButton3);
        containerOfButtons.add(Box.createVerticalStrut(15));
        containerOfButtons.add(containerOfButton4);
        containerOfButtons.add(Box.createVerticalStrut(15));
        containerOfButtons.setBorder(new EmptyBorder(0,75, 0, 75 ));

    }

    //Listeners

    /**
     * Start a game.
     */
    private void startGame() {

        game = new com.dominicswaine.seg_agile_project.Logic.Game();
        frame.dispose();

    }

    /**
     * Start a custom game.
     */
    private void customGame() {

        customGame = new CustomGame();
        frame.dispose();

    }

    /**
     * Load a game.
     */
    private void loadGame() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            Parser.loadCustomGame(selectedFile.getAbsolutePath());

        }

    }

    /**
     * Help option pane outlining how the game works.
     */
    private void help() {

        JOptionPane.showMessageDialog(frame,
                "<html>" +
                        "<h2>Game board</h2>" +
                        "<p>The board consists of two sides: a white/light side and a black/dark side.</p>" +
                        "<p>The player on the white/light side starts the game.</p>" +
                        "<p>Each player side has nine holes, numbered 1-9 from left to right, and one larger rectangular store called the player's kazan.</p>" +
                        "<p>Initially, 162 korgools (small round stones) are distributed equally over the 18 holes in the board: 9 korgools per hole.</p>" +
                        "<p>No stones are placed in the player's kazans.</p>" +
                        "<br/>" +
                        "<h2>Move</h2>" +
                        "<p>Players make moves by selecting one of the holes on their side of the game that contains korgools.</p>" +
                        "<p>A move consists of taking all the korgools from the selected hole and redistributing or seeding them to other holes in the anticlockwise direction.</p>" +
                        "<p>The first korgool is put in the hole the korgools were taken from. The next korgool goes in the adjacent hole to the right, and so on.</p>" +
                        "<p>Once the player has dropped a korgool in hole 9, the next korgool goes into hole 1 of the other player, and so on.</p>" +
                        "<br/>" +
                        "<h2>Rules</h2>" +
                        "<ol>" +
                        "<li>The player on the white/light side makes the first move. Players alternate between making moves.</li>" +
                        "<li>The normal redistribution rule does not apply if there is only one korgool in a hole.<br/>If there is only one korgool in a hole and that hole is selected for a move, the hole is emptied and the korgool is moved to the next hole.</li>" +
                        "<li>If the last korgool in a move ends up in an opponent's hole, and the number of korgools in that hole is now even, then the player captures all the korgools in that hole.<br/>The korgools are moved into the player's kazan.</li>" +
                        "<li>If the last korgool in a move ends up in the player's own side, nobody captures these korgools.</li>" +
                        "<li>If the last korgool in a move ends up in an opponent's hole, and the number of korgools in that hole is now odd, then nobody captures these korgools.</li>" +
                        "<li>" +
                        "<span>If the last korgool in a move ends up in an opponent's hole containing two korgools (i.e. the hole contains three korgools at the end of the move), then this hole is marked as a tuz.<br/>This means that this hole now belongs to the player who claimed the tuz and all korgools in the tuz are transferred to the owner's kazan.<br/>The following restrictions apply:</span>" +
                        "<ul>" +
                        "<li>Each player can claim only one tuz.</li>" +
                        "<li>A tuz cannot be moved.</li>" +
                        "<li>Hole 9 cannot be claimed as tuz.</li>" +
                        "<li>If one player has claimed hole n as tuz, then their opponent can no longer claim hole n on the opposite side as tuz.</li>" +
                        "</ul>" +
                        "</li>" +
                        "<li>The game ends when one player has collected 82 or more korgools in their kazan.</li>" +
                        "</ol>" +
                        "</html>",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Create an instance of this class

    public static void main(String[] args) {

        new MainMenu();

    }

    //Methods required in integration testing

    /**
     * Gets the frame containing all components.
     *
     * @return the frame containing all components
     */
    public JFrame getFrame() {

        return frame;

    }

    /**
     * Gets the game constructed.
     *
     * @return the game constructed
     */
    public com.dominicswaine.seg_agile_project.Logic.Game getGame() {

        return game;

    }

    /**
     * Gets the custom game constructed.
     *
     * @return the custom game constructed
     */
    public CustomGame getCustomGame() {

        return customGame;

    }

}