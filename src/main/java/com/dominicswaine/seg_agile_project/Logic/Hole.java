package com.dominicswaine.seg_agile_project.Logic;
import java.lang.*;

public class Hole extends KorgoolContainer {
    private boolean isTuz;

    public Hole(int index){
        super(index<9 ? Side.WHITE : Side.BLACK);
        isTuz = false;
    }

    public void markAsTux(){
        isTuz = true;
    }

    public boolean isTuz(){
        return isTuz;
    }

    public void emptyHole(){
        korgools.clear();
    }

    protected void emptyBarOne(){
        Korgool first = korgools.get(0);
        emptyHole();
        addKorgool(first);
    }

}
