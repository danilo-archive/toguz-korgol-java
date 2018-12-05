package com.dominicswaine.seg_agile_project.Logic;
import java.lang.*;

public class Hole extends KorgoolContainer {
    private boolean isTuz;
    private int index;
    private com.dominicswaine.seg_agile_project.Board.Hole gui;

    public Hole(int index){
        super(index<9 ? Side.WHITE : Side.BLACK);
        this.index = index;
        isTuz = false;
    }

    public void setGui(com.dominicswaine.seg_agile_project.Board.Hole gui){
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
        korgools.clear();
    }

    protected void emptyBarOne(){
        gui.removeKorgols(getNumberOfKoorgools() - 1);
        Korgool first = korgools.get(0);
        emptyHole();
        addKorgool(first);
    }

    protected int getHoleIndex(){
        return index;
    }

    public com.dominicswaine.seg_agile_project.Board.Hole getGui(){return gui;}

}
