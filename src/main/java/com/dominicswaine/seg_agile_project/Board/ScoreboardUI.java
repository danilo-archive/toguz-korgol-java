package com.dominicswaine.seg_agile_project.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * A scoreboard showing the current score of the players as well as a game timer
 * @author Danilo Del Busso
 * @version 11-12-2018
 */
public class ScoreboardUI extends JLabel {

    private BufferedImage image; //the background image being displayeds
    private final JFrame frame;
    private JTextField whiteScore, blackScore;  //the scores of the players
    private ArrayList<KazanUI> kazans;
    private JButton saveButton;

    /**
     * Build a scoreboard panel and assign an array containing the two kazans
     * with which the score is calculated
     * @param kazans array containing the two kazans with which the score is calculated
     */
    public ScoreboardUI(ArrayList<KazanUI> kazans, JFrame frame){
        setOpaque(false);
        this.kazans = kazans;
        this.frame = frame;
        setLayout(new BorderLayout());
        createElements();


        try {
            File f = new File(System.getProperty("user.dir") + "/src/main/resources/scoreboardbg.jpg");
            image = ImageIO.read(f);
            int w = image.getWidth();
            int h = image.getHeight();

        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
        }

        setVisible(true);

    }


    /**
     * Create and return a timer for the game
     * @return a panel containing a timer for the game
     */
    private JPanel getTimer() {
        final long START_TIME = 0;
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
        final JLabel clock = new JLabel(sdf.format(new Date(START_TIME)),JLabel.CENTER);


        clock.setFont(new Font("Tahoma", Font.BOLD, 30));

        ActionListener al = new ActionListener(){
            long x = START_TIME + 1000;

            public void actionPerformed(ActionEvent ae){
                clock.setText(sdf.format(new Date(x)));
                x += 1000;}
        };


        new javax.swing.Timer(1000, al).start();

        JPanel jp = new JPanel();
        jp.setOpaque(false);
        jp.add(clock);
        jp.setBorder(BorderFactory.createEmptyBorder());

        return jp;
    }


    /**
     * Create and show all elements contained within the scoreboard
     */

    public void createElements(){

        JLabel scores = new JLabel();
        scores.setOpaque(false);
        scores.setLayout(new BorderLayout());
        //the scores of the players
        whiteScore = new JTextField();
        blackScore = new JTextField();

        whiteScore.setEditable(false);
        blackScore.setEditable(false);

        Font font = new Font("Tahoma", Font.BOLD, 30);

        whiteScore.setFont(font);
        blackScore.setFont(font);

        whiteScore.setForeground(Color.BLUE);
        blackScore.setForeground(Color.RED);


        update();

        whiteScore.setVisible(true);
        whiteScore.setOpaque(false);
        blackScore.setVisible(true);
        blackScore.setOpaque(false);
        scores.setVisible(true);

        JPanel jp =new JPanel();
        jp.setOpaque(false);
        jp.setLayout(new BorderLayout());

        jp.add(whiteScore,BorderLayout.WEST);
        jp.add(blackScore,BorderLayout.EAST);
        scores.add(jp, BorderLayout.CENTER);
        scores.add(getTimer(), BorderLayout.SOUTH);

        //save and back button at the bottom,the save button listener is handled in backend
        JTextField buttons = new JTextField();
        buttons.setLayout(new GridLayout(1,2));

        JButton back = new JButton("Back");
        saveButton = new JButton("Save");

        back.addActionListener(e -> endGame());

        buttons.add(back);
        buttons.add(saveButton);
        buttons.setVisible(true);

        add(buttons, BorderLayout.SOUTH);

        whiteScore.setBorder(BorderFactory.createEmptyBorder());
        blackScore.setBorder(BorderFactory.createEmptyBorder());

        this.add(scores,CENTER);

    }

    /**
     * Close board window and open main menu, ending the game
     */
    private void endGame(){
        new MainMenu();
        frame.dispose();
    }

    /**
     * Update the score of the game
     * @return true if game has been closed
     */
    public boolean update() {

        whiteScore.setText("  " + (kazans.get(0).getLastKorgolInd() + 1) + "");
        blackScore.setText((kazans.get(1).getLastKorgolInd() + 1) + "  ");

        if (kazans.get(0).getLastKorgolInd() + 1 >= 82) {
            showEndGamePopup("        CONGRATULATIONS, YOU WON!");
            return true;
        }
        else if(kazans.get(1).getLastKorgolInd() + 1 >= 82) {
            showEndGamePopup("                          YOU LOST");
            return true;
        }
        else if(kazans.get(0).getLastKorgolInd() + 1 == 81 && kazans.get(1).getLastKorgolInd() + 1 ==81){
            showEndGamePopup("                         IT'S A TIE!");
            return true;
        }

        return false;
    }

    /**
     * Display a popup frame with the given message and close game on button click
     * @param s the message shown on the popup frame
     */
    private void showEndGamePopup(String s) {
        JFrame popup = new JFrame("THANK YOU FOR PLAYING!");
        popup.setVisible(true);
        frame.dispose();
        popup.setLayout(new BorderLayout());

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
                                    new MainMenu();
                                    popup.dispose();
        });

        JTextField text = new JTextField(s);
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setEditable(false);
        text.setOpaque(true);
        text.setBorder(BorderFactory.createEmptyBorder());

        popup.add(ok, BorderLayout.SOUTH);
        popup.add(text, BorderLayout.NORTH);
        popup.pack();
        popup.setSize(new Dimension(500, 100));
        popup.setVisible(true);
        popup.setLocationRelativeTo(null);

        ok.setVisible(true);
        text.setVisible(true);
        popup.setVisible(true);
    }
    
    /**
     * Return a Dimension object of the preferred window size
     * @return a Dimension object of the preferred window size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(190,720);
    }

    /**
     * Paint window components
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Return the save button
     * @return the save button
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Return the Arraylist containing the kazans
     * @return the Arraylist containing the kazans
     */
    public ArrayList<KazanUI> getKazans() {
        return kazans;
    }

    /**
     * Return the whitescore textfield
     * @return the whitescore textfield
     */
    public JTextField getWhiteScore() {
        return whiteScore;
    }

    /**
     * Return the blackscore textfield
     * @return the blackscore textfield
     */
    public JTextField getBlackScore() {
        return blackScore;
    }
}
