package com.dominicswaine.seg_agile_project.Logic;
import com.dominicswaine.seg_agile_project.Board.HoleUI;

import java.awt.*;
import java.lang.*;

/**
 * @Author Ayberk Demirkol, Dominic Swaine
 */
public class Hole extends KorgoolContainer {
    private boolean isTuz;  //Field for marking tuz
    private int index;  //Field for index of the hole
    private HoleUI gui; //Field for UI of hole

    /**
     * Constructor for hole object
     * @param index index of the hole constructed
     */
    public Hole(int index){
        super(index<9 ? Side.WHITE : Side.BLACK);
        this.index = index;
        isTuz = false;
    }

    /**
     * Connects the HoleUI to hole
     * @param gui
     */
    public void setGui(HoleUI gui){
        this.gui = gui;
    }

    /**
     * Function to mark hole as tuz
     * Hole is marked red if originalOwner is player, else marked blue
     */
    public void markAsTuz(){
        if(index < 9){ gui.displayTuz(Color.RED); }
        else{ gui.displayTuz(Color.BLUE); }
        isTuz = true;
        System.out.println("Hole number" + index + "is marked as Tuz!");
    }

    /**
     * Returns boolean isTuz
     * @return boolean indicating if hole is tuz
     */
    public boolean isTuz(){
        return isTuz;
    }

    /**
     * Returns the originalOwner of hole
     * @return originalOwner
     */
    public Side getOwner() {
        return !isTuz() ? super.original0wner : ((super.original0wner == Side.WHITE) ? Side.BLACK : Side.WHITE);
    }

    @Override
    /**
     * Function to add korgools to hole
     */
    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
        gui.addKorgol();
    }

    /**
     * Function to remove all korgools from hole and remove them from GUI
     */
    public void emptyHole(){
        gui.removeKorgols(getNumberOfKoorgools());
        System.out.println("emptyHole in Logic.Hole removed " + getNumberOfKoorgools() + " holes.");
        korgools.clear();
    }

    /**
     * Function to leave only 1 korgool in the hole, remove others from both hole and GUI
     */
    protected void emptyBarOne(){
        gui.removeKorgols(getNumberOfKoorgools() - 1);
        System.out.println("emptyBarOne in Logic.Hole removed " + (getNumberOfKoorgools() -1) + " holes.");
        Korgool first = korgools.get(0);
        korgools.clear();
        addKorgool(first);
    }

    /**
     * Returns index of hole
     * @return index of hole
     */
    protected int getHoleIndex(){
        return index;
    }

    /**
     * Returns HoleUI connected to hole
     * @return HoleUI connected to hole
     */
    public HoleUI getGui(){return gui;}

}
