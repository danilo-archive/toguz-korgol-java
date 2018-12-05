package com.dominicswaine.seg_agile_project.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A board is the panel that contains the game board and draws
 * the background image.
 * @author Danilo Del Busso
 * @version 30-11-2018
 */
public class BoardUI extends JPanel {

    private BufferedImage image; //the background image being displayeds
    private int w, h;

    /**
     * Create the board by giving it the path of the background image
     * @param p the path of the background image
     */
    public BoardUI(String p) {
        setLayout(new BorderLayout());

        try {
            File f = new File(p);
            image = ImageIO.read(f);
            w = image.getWidth();
            h = image.getHeight();

        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }


}