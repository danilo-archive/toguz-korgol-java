package com.dominicswaine.seg_agile_project.Logic;

import java.util.ArrayList;

public class Board {
    private Hole[] holes = new Hole[18];
    private Kazan[] kazans = new Kazan[2];
    private Side nextToPlay;

    /**
     * Constructor for board object. Creates 18 holes and 2 kazans. Sets next turn to white side.
     */
    public Board(){
        for(int holeIndex = 0; holeIndex<holes.length; holeIndex++)
            holes[holeIndex] = new Hole(holeIndex);
        for(int kazanIndex = 0; kazanIndex<kazans.length; kazanIndex++)
            kazans[kazanIndex] = new Kazan(kazanIndex);
        nextToPlay = Side.WHITE;
    }

    /**
     * Return array of holes in game board
     * @return holes array
     */
    public Hole[] getHoles(){
        return holes;
    }

    /**
     * Return array of kazans in game board
     * @return kazans array
     */
    protected Kazan[] getKazans(){
        return kazans;
    }

    /**
     * Returns Kazan with given index
     * @param index index of Kazan to be returned
     * @return Kazan with the given index
     */
    protected Kazan getKazanByIndex(int index){
        return getKazans()[index];
    }

    /**
     * Returns Hole with given index
     * @param index index of Hole to be returned
     * @return Hole with given index
     */
    protected Hole getHoleByIndex(int index){
        return getHoles()[index];
    }

    /**
     * Returns side to play next
     * @return Side to play next
     */
    protected Side getNextToPlay(){
        return nextToPlay;
    }

    /**
     * Distribute each korgool for the selected hole.
     * @param holeIndex
     */
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
                    //System.out.println("Last Hole size is : " + lastHole.getKoorgools().size());
                    lastHole.emptyHole();
                }
            }
            nextToPlay = nextToPlay==Side.WHITE ? Side.BLACK : Side.WHITE;
            //System.out.println("Side White has" + kazans[0].getKoorgools().size() + " korgools");
            //System.out.println("Side Black has" + kazans[1].getKoorgools().size() + " korgools");
        }
        else{
            //TODO: Show this as an alert dialogue.
            System.out.println("This hole does not belong to the player!" + " Pick another hole!");
        }
    }

    /**
     * Returns an ArrayList of Holes owned by the given Side
     * @param turnSide Side that holes are linked to
     * @return an ArrayList of Holes owned by the given Side
     */
    private ArrayList<Hole> getOwnedHoles(Side turnSide){
        ArrayList<Hole> holesOwned = new ArrayList<>();
        for(Hole h : holes){
            if(h.getOwner() == turnSide){
                holesOwned.add(h);
            }
        }
        return holesOwned;
    }

    /**
     * Returns legal moves for the player next to play
     * @return ArrayList of Holes that can be redistributed and owned by the player next to play
     */
    private ArrayList<Hole> availableMoves(){
        ArrayList<Hole> holesOwned = getOwnedHoles(nextToPlay);
        for(Hole h : holesOwned){
            if(h.getNumberOfKoorgools() == 0){
                holesOwned.remove(h);
            }
        }
        return holesOwned;
    }

    /**
     * Picks a random hole belonging side to play next and redistributes that hole
     */
    public void randomMove(){
        ArrayList<Hole> availableHoles = availableMoves();
        int holeIndex = (int)(Math.random() * (((availableHoles.size()-1) - 0) + 1)) + 0;
        ArrayList<Korgool> korgools = availableHoles.get(holeIndex).getKoorgools();
        while(korgools.size() == 0){
            holeIndex = (int)(Math.random() * (((availableHoles.size()-1) - 0) + 1)) + 0;
            korgools = availableHoles.get(holeIndex).getKoorgools();
        }
        //System.out.println("Next Random move is Hole:" + (holesOwned.get(holeIndex).getHoleIndex()));
        redistribute(availableHoles.get(holeIndex).getHoleIndex());
    }

    public void challengeMove(){
        int maxOutcome = -1;
        int returnIndex = -1;
        Hole lastHole;
        Hole selectedHole;
        ArrayList<Hole> availableHoles = availableMoves();
        for(int i = 0; i < availableHoles.size(); i++){
            selectedHole = availableHoles.get(i);
            lastHole = holes[(selectedHole.getHoleIndex() + selectedHole.getNumberOfKoorgools() - 1) % 18];
            if(lastHole.getOwner() != nextToPlay){
                int numOfKorgools = lastHole.getNumberOfKoorgools() +1;
                if(numOfKorgools == 3 && !nextToPlay.hasTuz()){
                    //TODO: Give priority to tuz making. Create an extra case for tuz.
                    //TODO: Make sure cases for holes containing 1 or 0 korgool.
                    System.out.println("Next viable move is to make Tuz of hole" + lastHole.getHoleIndex());
                    redistribute(selectedHole.getHoleIndex());
                    return;
                }
                //TODO: Checkpoint for Selectedholes with 0.
                if(numOfKorgools % 2 == 0 && numOfKorgools > maxOutcome){
                    maxOutcome = numOfKorgools;
                    returnIndex = selectedHole.getHoleIndex();
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

    /**
     * Returns the index of the hole that is tuz for given side
     * @param owner Side that picked tuz belongs to
     * @return index of the hole that is tuz for given side
     */
    public int getPlayerTuz(Side owner) {
        for(int i = 0 ; i <= 17 ; ++i) {
            if(holes[i].isTuz() && holes[i].getOwner() == owner) {
                return i;
            }
        }
        return -1;
    }
}
