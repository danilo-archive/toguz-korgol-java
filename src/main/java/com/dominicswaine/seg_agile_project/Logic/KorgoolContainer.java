package com.dominicswaine.seg_agile_project.Logic;
import java.util.ArrayList;

/**
 * Abstract method which holds Game Board Korgool objects
 * @author Ayberk Demirkol, Dominic Swaine
 * @version 13/12/2018
 */
abstract public class KorgoolContainer {
    /**
     *  Field (ArrayList) containing the Korgools contained by the KorgoolContainer
     */
    protected ArrayList<Korgool> korgools;
    /**
     *  Field containing the side of the original owner of the KorgoolContainer
     */
    protected Side original0wner;

    /**
     * Constructor for the KorgoolContainer object
     * @param original0wner the side of the original owner of the KorgoolContainer
     */
    public KorgoolContainer(Side original0wner){
        korgools = new ArrayList<>();
        this.original0wner = original0wner;
    }

    /**
     * Function to add a korgool to the KorgoolContainer
     * @param korgool korgool to be added to the KorgoolContainer
     */
    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
    }

    /**
     * Function to add one or more korgools to the KorgoolContainer
     * @param korgools ArrayList of korgools to be added to the KorgoolContainer
     */
    public void addKorgools(ArrayList<Korgool> korgools){
        korgools.addAll(korgools);
    }

    /**
     * Function to return a boolean indicating whether the KorgoolContainer is empty
     * @return a boolean indicating whether the KorgoolContainer is empty
     */
    public boolean isEmpty(){
        return korgools.isEmpty();
    }

    /**
     * Function to return the number of Korgools contained by the KorgoolContainer
     * @return the number of Korgools contained by the KorgoolContainer
     */
    public int getNumberOfKoorgools(){
        return korgools.size();
    }

    /**
     * Function to return the ArrayList of Korgools contained by the KorgoolContainer
     * @return the ArrayList of the Korgools contained by the KorgoolContainer
     */
    public ArrayList<Korgool> getKoorgools(){
        return korgools;
    }

}
