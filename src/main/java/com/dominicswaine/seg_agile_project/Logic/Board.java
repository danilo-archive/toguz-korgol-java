package com.dominicswaine.seg_agile_project.Logic;

public class Board {
    private Hole[] holes = new Hole[18];
    private Kazan[] kazans = new Kazan[2];

    public Board(){
        for(int holeIndex = 0; holeIndex<holes.length; holeIndex++)
            holes[holeIndex] = new Hole(holeIndex);
        for(int kazanIndex = 0; kazanIndex<kazans.length; kazanIndex++)
            kazans[kazanIndex] = new Kazan(kazanIndex);
    }

    public Hole[] getHoles(){
        return holes;
    }

    public Kazan[] getKazans(){
        return kazans;
    }

    public Kazan getKazanByIndex(int index){
        return getKazans()[index];
    }

    public Hole getHoleByIndex(int index){
        return getHoles()[index];
    }


}
