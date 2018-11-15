package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;

/**
 * A korgol is the basic element of the game, it is drawn
 * as a circle which can be removed and added to various holes.
 * This class simply creates a panel with a circle of r = 10px.
 *
 * @author Danilo Del Busso
 * @version 14-11-2018
 */
public class Korgol extends JPanel {

    private Color color;

    /**
     * Create a korgol of specified color
     * @param color the color of the korgol
     */
    public Korgol(Color color) {
        this.color = color;
    }

    /**
     * Return the color of the korgol
     * @return color of the korgol
     */
    public Color getColor() {
        return color;
    }


    /**
     * Set the color to a value
     * @param color the color to change it to
     */
    public void setColor(Color color) {
        this.color = color;
    }


    @Override
    public void paintComponent(Graphics g) {
        setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(color);
        g2.setStroke(new BasicStroke(4));
        g2.fillOval(15,22,20,20);



    }
}
