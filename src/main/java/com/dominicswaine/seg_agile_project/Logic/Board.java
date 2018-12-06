package com.dominicswaine.seg_agile_project.Logic;

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

    protected Hole[] getHoles(){
        return holes;
    }

    protected Kazan[] getKazans(){
        return kazans;
    }

    protected Kazan getKazanByIndex(int index){
        return getKazans()[index];
    }

    protected Hole getHoleByIndex(int index){
        return getHoles()[index];
    }

    protected Side getNextToPlay(){
        return nextToPlay;
    }

    public void redistribute(int holeIndex) {

        System.out.println(holeIndex);

        if((holes[holeIndex].getOwner() == nextToPlay)) {

            ArrayList<Korgool> korgools = holes[holeIndex].getKoorgools();
            /*
            System.out.println(nextToPlay);
            for(int i=0; i<18; i++){
                System.out.print(" "+i+":"+holes[i].getNumberOfKoorgools());
            }
            System.out.println();
            */

            int indexOfLastHole = (korgools.size()==1)
                    ? (holeIndex+1)%18 : (holeIndex+korgools.size()-1)%18;

            if(korgools.size()==0){
                // System.out.println("EMTPY WHOLE");
                return;
            }
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

            if(holes[indexOfLastHole].getNumberOfKoorgools() % 2 == 0 && holes[indexOfLastHole].getOwner() != nextToPlay) {
                int playersKazanIndex = (nextToPlay == Side.WHITE) ? 0 : 1;
                kazans[playersKazanIndex].addKorgools(holes[indexOfLastHole].getKoorgools());
                holes[indexOfLastHole].emptyHole();
            }else if(holes[indexOfLastHole].getNumberOfKoorgools() == 3 && holes[indexOfLastHole].getOwner() != nextToPlay) { // 'tuz' time
                if(getPlayerTuz(nextToPlay) == -1 &&
                        (indexOfLastHole+1)%9 != 0 &&
                        !holes[(indexOfLastHole + 9)%18].isTuz()) {
                    holes[indexOfLastHole].markAsTuz();
                    // System.out.println("TUZ!");
                    int playersKazanIndex = (nextToPlay == Side.WHITE) ? 0 : 1;
                    kazans[playersKazanIndex].addKorgools(holes[indexOfLastHole].getKoorgools());
                    holes[indexOfLastHole].emptyHole();
                }
            }

            /*
            for(int i=0; i<18; i++){
                System.out.print(" "+i+":"+holes[i].getNumberOfKoorgools());
            }
            System.out.println();System.out.println();
            */
            nextToPlay = nextToPlay==Side.WHITE ? Side.BLACK : Side.WHITE;
        }


    }

    public int randomMove(){
        int holeIndex = (int)Math.random() * 9 + 17;
        ArrayList<Korgool> korgools = holes[holeIndex].getKoorgools();
        while(korgools.size() == 0){
            holeIndex = (int)Math.random() * 9 + 17;
            korgools = holes[holeIndex].getKoorgools();
        }
        System.out.println("Next Random move is Hole:" + (holeIndex -9) + " of opponent");
        return holeIndex;
    }

    public int challengeMove(){
        int maxOutcome = -1;
        int returnIndex = -1;
        for(int holeIndex = 0; holeIndex < 17; holeIndex++){
            if(holes[holeIndex].getOwner() == Side.BLACK) {
                Hole outcomeHole = holes[(holeIndex+ holes[holeIndex].getKoorgools().size() - 1) % 18];
                if (outcomeHole.getOwner() == Side.WHITE) {
                    //TODO: Give priority to tuz making. Create an extra case for tuz.
                    //TODO: Make sure cases for holes containing 1 or 0 korgool.
                    int numOfKorgools = outcomeHole.getKoorgools().size() + 1;
                    if(numOfKorgools == 3){
                        System.out.println("Next viable move is: make Tuz: " + (holeIndex) + " of opponent");
                        return holeIndex;
                    }
                    if (numOfKorgools % 2 == 0 && numOfKorgools > maxOutcome) {
                        maxOutcome = numOfKorgools;
                        returnIndex = holeIndex;
                    }
                }
            }
        }
        if(returnIndex == -1){
            System.out.println("No Available Moves Left. Random move will be made.");
            return randomMove();
        }
        System.out.println("Next viable move is: Hole " + (returnIndex-9) + " of opponent");
        return returnIndex;
    }

    public int getPlayerTuz(Side owner) {
        for(int i = 0 ; i <= 17 ; ++i) {
            if(holes[i].isTuz() && holes[i].getOwner() == owner) {
                return i;
            }
        }
        return -1;
    }
}
