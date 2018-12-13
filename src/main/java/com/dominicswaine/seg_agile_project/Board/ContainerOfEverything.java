package com.dominicswaine.seg_agile_project.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A panel with a background image
 * @author Danilo Del Busso
 * @version 12-12-2018
 */
public class ContainerOfEverything extends JPanel {

    private BufferedImage image; //the background image being displayeds
    private int w, h;  // width, height of the JPanel

    /**
     * Create the panel by giving it the path of the background image
     * @param p the path of the background image
     */
    public ContainerOfEverything(String p) {
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
    
    /**
     * Return a Dimension object of the preferred window size
     * @return a Dimension object of the preferred window size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
    
    /**
     * Paint window components
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
