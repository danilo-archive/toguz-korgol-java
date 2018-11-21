package com.dominicswaine.seg_agile_project.Board;

import java.awt.*;

/**
 * A kazan is the container of korgols that the player owns.
 * It is a visual representation of the score of a player.
 *
 * @author Danilo Del Busso
 * @version 18-11-2018
 */
public class Kazan extends KorgolsContainer {
    /**
     * This container maintains korgols and shows them to screen
     */
    public Kazan() {
        setLayout(new GridLayout(5,17));
    }


    @Override
    public int getMaxKorgolsPossible() {
        return 95;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(15, 10, 790, 270);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(810,360);
    }
}
