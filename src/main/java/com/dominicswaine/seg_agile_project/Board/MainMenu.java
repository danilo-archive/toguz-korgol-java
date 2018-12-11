//fix text
//fix styling
//fix functionality

package com.dominicswaine.seg_agile_project.Board;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.nio.file.NoSuchFileException;

import com.dominicswaine.seg_agile_project.Logic.Parser;

public class MainMenu {

    private JFrame frame;
    private JPanel containerOfEverything, containerOfText, containerOfButtons;


    public MainMenu() {

        //Initialise the frame

        frame  = new JFrame();
        frame.setSize(new Dimension(475, 500));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Make and populate the panels

        makePanels();
        populateContainerOfText();
        populateContainerOfButtons();

        containerOfEverything.add(containerOfText);
        containerOfEverything.add(containerOfButtons);
        frame.add(containerOfEverything);
        frame.setVisible(true);

    }

    private void makePanels() {

        containerOfEverything = new JPanel();
        containerOfEverything.setLayout(new BoxLayout(containerOfEverything, BoxLayout.Y_AXIS));
        containerOfText = new JPanel();
        containerOfText.setLayout(new BoxLayout(containerOfText, BoxLayout.Y_AXIS));
        containerOfButtons = new JPanel();
        containerOfButtons.setLayout(new BoxLayout(containerOfButtons, BoxLayout.Y_AXIS));

    }

    private void populateContainerOfText() {

        JLabel title = new JLabel("Toguz Korgool");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("<html><p style=\"text-align:justify\">Toguz Korgool employs a board consisting of two sides:" +
                " a white/light side and a black/dark side. The player on the white/light side starts the game. Each player side has nine holes, " +
                "numbered from 1-9 from left to right, and one larger rectangular store called the player's kazan. Initially 162 korgools are distributed  " +
                "equally over the 18 holes in the board: 9 korgools per hole. No stones are placed in the player's kazans. " +
                "and also whether a hole is a Tuz. Note that the total " +
                "number of Korgools must be exactly 162, the two Tuzzes " +
                "cannot be the same, and no Tuz can be 9.</p></html>");

        instructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerOfText.add(Box.createVerticalStrut(20));
        containerOfText.add(title);
        containerOfText.setBorder(new EmptyBorder(0, 20, 0, 20));
        containerOfText.add(Box.createVerticalStrut(20));
        containerOfText.add(instructions);
        containerOfText.add(Box.createVerticalStrut(20));

    }

    public void populateContainerOfButtons() {

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        //startGameButton.setMaximumSize(new Dimension(200, 10));
        startGameButton.addActionListener(e -> startGame());

        JButton customGameButton = new JButton("Custom Game");
        customGameButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        //customGameButton.setMaximumSize(new Dimension(200, 10));
        customGameButton.addActionListener(e -> customGame());

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        //loadGameButton.setMaximumSize(new Dimension(200, 10));
        loadGameButton.addActionListener(e -> loadGame());

        containerOfButtons.add(startGameButton);
        containerOfButtons.add(Box.createVerticalStrut(10));
        containerOfButtons.add(customGameButton);
        containerOfButtons.add(Box.createVerticalStrut(10));
        containerOfButtons.add(loadGameButton);

    }

    //Listeners

    public void startGame() {

        new com.dominicswaine.seg_agile_project.Logic.Game();
        frame.dispose();

    }

    public void customGame() {

        new CustomGame();
        frame.dispose();

    }

    public void loadGame() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            Parser.loadCustomGame(selectedFile.getAbsolutePath());


        }

    }

    //Create an instance of this class

    public static void main(String[] args) {

        new MainMenu();

    }

}