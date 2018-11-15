package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This object contains and displays any number of korgols. It contains, removes and shows
 * the korgols to screen.
 * @author Danilo Del Busso
 * @version 14-11-2018
 */
public abstract class KorgolsContainer extends JLabel {

    protected ArrayList<Korgol> korgols;


    /**
     * This container maintains korgols and shows them to screen
     */
    public KorgolsContainer(){

        korgols = new ArrayList<Korgol>();


    }

    /**
     * Return the korgols currently present in the container
     * @return the korgols currently present in the container
     */
    public ArrayList<Korgol> getKorgols() {
        return korgols;
    }

    /**
     * Adds korgols to the container so that they are aligned
     * @param c color of the korgols to add
     */
    public void adjustLooks(Color c){
        for(int i = korgols.size(); i < getMaxKorgolsPossible(); ++i){
            Korgol k = new Korgol(c);
            korgols.add(k);
            this.add(k);
            this.repaint();
        }
    }



    /**
     * Add n korgols of the specified color to the hole
     * @param n the numberof korgols to add to the hole
     * @param c the color of the korgols to add
     */
    void addKorgols(int n, Color c) {
        if(n <= 90){
            for(int i = 0; i< n; ++i){
                addKorgol(c);
            }
        }
    }

    /**
     * Add a single black korgol to the hole
     */
    public void addKorgol(){
        Korgol k = new Korgol(Color.black);
        korgols.add(k);
        this.add(k);
        this.repaint();

    }

    /**
     * Add a single korgol of the specified color to the hole
     * @param c the color of the korgol
     */
    public void addKorgol(Color c){

        for(Korgol k: korgols){
            if(k.getColor() != c){
                k.setColor(c);
                return;
            }
        }

        Korgol k = new Korgol(c);
        korgols.add(k);
        this.add(k);
        this.repaint();

    }

    /**
     * Remove n number of korgols and dont show them
     * @param n the number of korgols to remove
     */

    public void removeKorgols(int n){

        int size = korgols.size();

        if(n<=size){
            for(int i = size-1; i > size-n ; --i){
                this.remove(korgols.remove(i));
            }
        }
        this.repaint();
    }


    /**
     * Return the maximum number of korgols that can fit the container without it
     * looking odd
     * @return he maximum number of korgols that can fit the container without it
     *      * looking odd
     */
    public abstract int getMaxKorgolsPossible();



}
