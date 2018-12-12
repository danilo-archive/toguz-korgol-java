package com.dominicswaine.seg_agile_project.Logic;
import com.dominicswaine.seg_agile_project.Board.KazanUI;
import java.util.ArrayList;

/**
 * @author Ayberk Demirkol, Dominic Swaine
 */
public class Kazan extends KorgoolContainer {
    /**
     * Field for the UI associated with the Kazan
     */
    private KazanUI gui;
    //private int index;

    /**
     * Constructor for the Kazan object
     * @param index index of the Kazan constructed
     */
    public Kazan(int index){
        super(index==0 ? Side.WHITE : Side.BLACK);
        //this.index = index;
    }

    /**
     * Associate the Kazan with its corresponding KazanUI
     * @param gui gui of Kazan
     */
    public void setGui(KazanUI gui){
        this.gui = gui;
    }

    /**
     * Returns the owner of the Kazan
     * @return the side of the owner of the Kazan
     */
    public Side getOwner() {
        return super.original0wner;
    }

    /**
     * Function to add a korgool to the Kazan
     * @param korgool korgool to be added to the Kazan
     */
    @Override
    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
        gui.addKorgol();
    }

    /**
     * Function to add one or more korgools to the Kazan
     * @param korgools_list ArrayList of korgools to be added to the Kazan
     */
    @Override
    public void addKorgools(ArrayList<Korgool> korgools_list){
        korgools.addAll(korgools_list);
        gui.addKorgols(korgools_list.size());
    }

    /**
     * Returns KazanUI associated with the Kazan
     * @return KazanUI associated with the Kazan
     */
    public KazanUI getGui(){return gui;}
}
