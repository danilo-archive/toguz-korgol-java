package com.dominicswaine.seg_agile_project.Logic;
import com.dominicswaine.seg_agile_project.Board.HoleUI;

import java.lang.*;

public class Hole extends KorgoolContainer {
    private boolean isTuz;
    private int index;
    private HoleUI gui;

    public Hole(int index){
        super(index<9 ? Side.WHITE : Side.BLACK);
        this.index = index;
        isTuz = false;
    }

    public void setGui(HoleUI gui){
        this.gui = gui;
    }

    public void markAsTuz(){
        isTuz = true;
    }

    public boolean isTuz(){
        return isTuz;
    }

    public Side getOwner() {
        return !isTuz() ? super.original0wner : ((super.original0wner == Side.WHITE) ? Side.BLACK : Side.WHITE);
    }

    @Override
    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
        gui.addKorgol();
    }

    public void emptyHole(){
        gui.removeKorgols(getNumberOfKoorgools());
        System.out.println(getNumberOfKoorgools() + " number of korgools are being removed");
        korgools.clear();
    }

    protected void emptyBarOne(){
        gui.removeKorgols(getNumberOfKoorgools() - 1);
        System.out.println("Empty Bar one returned this many remove methods" + (getNumberOfKoorgools() - 1));
        Korgool first = korgools.get(0);
        addKorgool(first);
    }

    protected int getHoleIndex(){
        return index;
    }

    public HoleUI getGui(){return gui;}

}
