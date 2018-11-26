package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;

/**
 * A kazan is the container of korgols that the player owns.
 * It is a visual representation of the score of a player.
 *
 * @author Danilo Del Busso
 * @version 26-11-2018
 */
public class Kazan extends KorgolsContainer {
    /**
     * This container maintains korgols and shows them to screen
     */
    public Kazan() {
        setOpaque(false);
        String backgroundPath = System.getProperty("user.dir") + "/src/main/resources/frames/kazan.png";
        setIcon(new ImageIcon(backgroundPath));
        setLayout(new GridLayout(5,16));
    }


    @Override
    public int getMaxKorgolsPossible() {
        return 95;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(540,240);
    }
}
