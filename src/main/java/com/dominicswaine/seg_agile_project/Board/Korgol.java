package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;

/**
 * A korgol is the basic element of the game, it is drawn
 * as a circle which can be removed and added to various holes.
 * This class simply creates a panel with a circle of r = 10px.
 *
 * @author Danilo Del Busso
 * @version 26-11-2018
 */
public class Korgol extends JLabel {

    private boolean isWhite;
    private JLabel jl;

    /**
     * Create a korgol of specified color
     */
    public Korgol(boolean isWhite) {
        setOpaque(false);
        this.isWhite = isWhite;
        this.setLayout(new BorderLayout());
        render();

    }

    /**
     * Return true if korgool is not diplayed to screen
     * @return
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Render korgool on screen
     */
    public void render(){
        if(!isWhite){
            jl = new JLabel();
            String korgolpath = System.getProperty("user.dir") + "/src/main/resources/korgool.png";
            jl.setIcon(new ImageIcon(korgolpath));
            this.add(jl,BorderLayout.SOUTH);
            isWhite = false;
        }
    }

    /**
     * Remove korgool from screen
     */
    public void dontRender(){
        if(jl != null){
         this.remove(jl);
         isWhite = true;
        }
    }


}
