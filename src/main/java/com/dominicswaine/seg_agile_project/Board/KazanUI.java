package com.dominicswaine.seg_agile_project.Board;

import com.dominicswaine.seg_agile_project.Logic.Korgool;

import javax.swing.*;
import java.awt.*;

/**
 * A kazan is the container of korgols that the player owns.
 * It is a visual representation of the score of a player.
 *
 * @author Danilo Del Busso
 * @version 30-11-2018
 */
public class KazanUI extends KorgoolsContainerUI {
    /**
     * This container maintains korgols and shows them to screen
     */
    public KazanUI() {
        setOpaque(false);
        String backgroundPath = System.getProperty("user.dir") + "/src/main/resources/frames/kazan.png";
        setIcon(new ImageIcon(backgroundPath));
        setLayout(new GridLayout(5, 16));
    }

    /**
     * Function to return the maximum number of Korgools which can be contained within a Kazan
     * return the maximum number of Korgools which can be contained within a Kazan
     */
    @Override
    public int getMaxKorgolsPossible() {
        return 85;
    }
    
    /**
     * Return a Dimension object of the preferred window size
     * @return a Dimension object of the preferred window size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(540, 240);
    }
}
