package com.dominicswaine.seg_agile_project.Logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private Hole[] holes = new Hole[18];
    private Kazan[] kazans = new Kazan[2];
    private Side nextToPlay;

    public Board(){
        for(int holeIndex = 0; holeIndex<holes.length; holeIndex++)
            holes[holeIndex] = new Hole(holeIndex);
        for(int kazanIndex = 0; kazanIndex<kazans.length; kazanIndex++)
            kazans[kazanIndex] = new Kazan(kazanIndex);
        nextToPlay = Side.WHITE;
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

    public void redistribute(int holeIndex) {
        if((holeIndex <= 8 && nextToPlay == Side.WHITE) ||
                (holeIndex >= 9 && holeIndex <= 17 && nextToPlay == Side.BLACK)) {

            ArrayList<Korgool> korgools = holes[holeIndex].getKoorgools();

            int indexOfLastHole = (korgools.size()==1)
                    ? (holeIndex+1)%18 : (holeIndex+korgools.size()-1)%18;

            if(korgools.size()>=2){
                for(int i = 1 ; i < korgools.size() ; ++i) {
                    holes[(holeIndex+i)%18].addKorgool(korgools.get(i));
                }

                holes[holeIndex].emptyBarOne();
            }else {
                Korgool first = korgools.get(0);
                holes[holeIndex].emptyHole();
                holes[(holeIndex + 1) % 18].addKorgool(first);
            }

            if(holes[indexOfLastHole].getNumberOfKoorgools() % 2 == 0) {
                int playersKazanIndex = (nextToPlay == Side.WHITE) ? 0 : 1;
                kazans[playersKazanIndex].addKorgools(holes[indexOfLastHole].getKoorgools());
                holes[indexOfLastHole].emptyHole();
            }
        }
    }
}
