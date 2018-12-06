package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.KazanUI;

import java.util.ArrayList;

public class Kazan extends KorgoolContainer {
    private KazanUI gui;
    private int index;

    public Kazan(int index){
        super(index==0 ? Side.WHITE : Side.BLACK);
        this.index = index;
    }

    public void setGui(KazanUI gui){
        this.gui = gui;
    }

    public Side getOwner() {
        return super.original0wner;
    }

    @Override
    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
        gui.addKorgol();
    }

    @Override
    public void addKorgools(ArrayList<Korgool> korgools){
        korgools.addAll(korgools);
        gui.addKorgols(korgools.size());
    }

    public KazanUI getGui(){return gui;}
}
