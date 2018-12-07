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

    public Hole[] getHoles(){
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

    public void redistribute(int holeIndex){
        System.out.println("Chosen hole is: " + holeIndex);

        if(holes[holeIndex].getOwner() == nextToPlay) {
            //TODO: Add a check to see if move being made is a tuz.
            ArrayList<Korgool> korgoolsToMove = holes[holeIndex].getKoorgools();
            Hole holeChosen = holes[holeIndex];
            Hole lastHole;
            //@Check if there are no korgools in the hole.
            if(korgoolsToMove.size() == 0){
                System.out.println("You cannot make this move! Chosen hole has only 0 korgools!");
                return;
            }
            //@Check if there are 1 korgool in the hole.
            else if(korgoolsToMove.size() == 1){
                //System.out.println(holeIndex + " numbered hole has only 1 korgools.");
                lastHole = holes[(holeIndex + 1) % 18];
                //System.out.println("Transferring 1 korgools to hole number" + lastHole.getHoleIndex());
                lastHole.addKorgool(holeChosen.getKoorgools().get(0));
                holeChosen.emptyHole();
            }
            else{
                lastHole = holes[(holeIndex + korgoolsToMove.size() - 1) % 18];
                //Distributes each korgools
                for(int distributeIndex = 1; distributeIndex < korgoolsToMove.size(); distributeIndex++){
                    //System.out.println("1 korgool from hole: " + holeChosen.getHoleIndex() + " is moved to" + holes[(holeIndex + distributeIndex) % 18].getHoleIndex());
                    holes[(holeIndex + distributeIndex) % 18].addKorgool(korgoolsToMove.get(distributeIndex));
                }
                //System.out.println("We try deleting after the loop");
                Korgool first = korgoolsToMove.get(0);
                holeChosen.emptyHole();
                holeChosen.addKorgool(first);
            }
            //@Check if we add to kazan or make tuz.
            if(lastHole.getOwner() != nextToPlay) {
                int playersKazanIndex = (nextToPlay == Side.WHITE) ? 0 : 1;
                ArrayList<Korgool> lastHoleKorgools = lastHole.getKoorgools();
                if((lastHole.getHoleIndex() != 9 && lastHole.getHoleIndex() != 17) && lastHoleKorgools.size() == 3 && !lastHole.isTuz() && !nextToPlay.hasTuz()){
                    System.out.println(lastHole.getHoleIndex() + " is marked as tuz and" + lastHole.getKoorgools().size() + " korgools are won by " + nextToPlay);
                    lastHole.markAsTuz();

                    nextToPlay.makeTuz();
                    for(int i = 0; i < lastHoleKorgools.size(); i++){
                        kazans[playersKazanIndex].addKorgool(new Korgool());
                    }
                    kazans[playersKazanIndex].addKorgools(lastHole.getKoorgools());
                    lastHole.emptyHole();
                }
                else if(lastHoleKorgools.size() % 2 == 0){
                    System.out.println(lastHole.getKoorgools().size() + " korgools are won by " + nextToPlay);
                    for(int i = 0; i < lastHoleKorgools.size(); i++) {
                        kazans[playersKazanIndex].addKorgool(new Korgool());
                    }
                    System.out.println("Last Hole size is : " + lastHole.getKoorgools().size());
                    lastHole.emptyHole();
                }
            }
            nextToPlay = nextToPlay==Side.WHITE ? Side.BLACK : Side.WHITE;
            System.out.println("Side White has" + kazans[0].getKoorgools().size() + " korgools");
            System.out.println("Side Black has" + kazans[1].getKoorgools().size() + " korgools");
        }
        else{
            System.out.println("This hole does not belong to the player!" + " Pick another hole!");
        }
    }

    public void randomMove(){
        int holeIndex = (int)(Math.random() * ((17 - 9) + 1)) + 9;
        ArrayList<Korgool> korgools = holes[holeIndex].getKoorgools();
        while(korgools.size() == 0 || holes[holeIndex].getOwner() == Side.WHITE || holes[holeIndex].isTuz()){
            holeIndex = (int)(Math.random() * ((17 - 9) + 1)) + 9;
            korgools = holes[holeIndex].getKoorgools();
        }
        System.out.println("Next Random move is Hole:" + (holeIndex));
        redistribute(holeIndex);
    }

    public void challengeMove(){
        int maxOutcome = -1;
        int returnIndex = -1;
        for(int holeIndex = 0; holeIndex < 17; holeIndex++){
            if(holes[holeIndex].getOwner() == Side.BLACK && !holes[holeIndex].isTuz()) {
                Hole outcomeHole = holes[(holeIndex+ holes[holeIndex].getKoorgools().size() - 1) % 18];
                if (outcomeHole.getOwner() == Side.WHITE) {
                    //TODO: Give priority to tuz making. Create an extra case for tuz.
                    //TODO: Make sure cases for holes containing 1 or 0 korgool.
                    int numOfKorgools = outcomeHole.getKoorgools().size() + 1;
                    if(numOfKorgools == 3 && !nextToPlay.hasTuz()){
                        System.out.println("Next viable move is: make Tuz: " + (holeIndex) + " of opponent");
                        redistribute(holeIndex);
                    }
                    if (numOfKorgools % 2 == 0 && numOfKorgools > maxOutcome) {
                        maxOutcome = numOfKorgools;
                        returnIndex = holeIndex;
                    }
                }
            }
        }
        if(returnIndex <= -1){
            System.out.println("No Available Moves Left. Random move will be made.");
            randomMove();
            return;
        }
        System.out.println("Next viable move is: Hole " + (returnIndex) + " of opponent");
        redistribute(returnIndex);
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
