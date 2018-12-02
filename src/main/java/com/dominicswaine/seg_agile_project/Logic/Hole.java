package com.dominicswaine.seg_agile_project.Logic;
import java.lang.*;

public class Hole extends KorgoolContainer {
    private boolean isTuz;
    private int index;

    public Hole(int index){
        super(index<9 ? Side.WHITE : Side.BLACK);
        this.index = index;
        isTuz = false;
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

    public void emptyHole(){
        korgools.clear();
    }

    protected void emptyBarOne(){
        Korgool first = korgools.get(0);
        emptyHole();
        addKorgool(first);
    }

    protected int getHoleIndex(){
        return index;
    }

}
