package com.dominicswaine.seg_agile_project.Board;

import com.dominicswaine.seg_agile_project.Logic.Parser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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

        JTextField buttons = new JTextField();
        buttons.setLayout(new GridLayout(1,2));

        JButton back = new JButton("Back");
        saveButton = new JButton("Save");
        //todo: make buttons taller

        back.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });

        buttons.add(back);
        buttons.add(saveButton);
        buttons.setVisible(true);

        add(buttons, BorderLayout.SOUTH);

        whiteScore.setBorder(BorderFactory.createEmptyBorder());
        blackScore.setBorder(BorderFactory.createEmptyBorder());

        this.add(scores,CENTER);

    }

    /**
     * Update the score of the game
     */
    public void update(){
        whiteScore.setText("  "+(kazans.get(0).getLastKorgolInd()+1) + "");
        blackScore.setText((kazans.get(1).getLastKorgolInd()+1)+ "  ");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(190,720);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
