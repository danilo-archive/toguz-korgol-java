package com.dominicswaine.seg_agile_project.Logic;

public class Kazan extends KorgoolContainer {
    public Kazan(int index){
        super(index==0 ? Side.WHITE : Side.BLACK);
    }

    public Side getOwner() {
        return super.original0wner;
    }
}
