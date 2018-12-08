package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import com.dominicswaine.seg_agile_project.Board.HoleUI;
import com.dominicswaine.seg_agile_project.Board.KazanUI;

import java.awt.event.*;

/**
 *
 * @Author Ayberk Demirkol, Dominic Swaine
 */
public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    /**
     * Initialize the GUI, connect each logical hole to holeUI and add mouse listeners.
     * Connect each kazan to kazanUI
     */
    private void initialiseGame() {
        //TODO: Add an option to GUI to choose side.
        //TODO: Make player choose between AI and randomMove.
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();

        for (int holeNo = 0; holeNo < game_board.getHoles().length; holeNo++) {
            Hole logicHole = game_board.getHoleByIndex(holeNo);
            HoleUI guiHole = holeNo < 9 ? gui.getHolesTopRow().get(8 - holeNo) : gui.getHolesBottomRow().get(holeNo - 9);
            logicHole.setGui(guiHole);

            final int holeIndex = holeNo;
            guiHole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game_board.redistribute(holeIndex);
                }
            });
        }

        for(int i = 0; i < game_board.getKazans().length; i++){
            //TODO: Indicate which kazan belongs to player. @Danilo
            Kazan logicKazan = game_board.getKazanByIndex(i);
            KazanUI guiKazan = gui.getKazans().get(i);
            logicKazan.setGui(guiKazan);
        }
    }

    /**
     * Default game constructor. Adds 9 korgools for each hole
     */
    public Game() {
        initialiseGame();
        for(Hole hole : game_board.getHoles()){
            for(int i = 0; i<9; i++){
                hole.addKorgool(new Korgool());
            }
        }
    }

    /**
     * Custom game constructor
     * @param playerTuz String storing players tuz. 0 if no tuz
     * @param opponentTuz String storing opponents tuz. 0 if no tuz
     * @param playerHoles Array of int storing number of korgools in players kazan at index 0 and holes at indexes 1-9
     * @param opponentHoles Array of int storing number of korgools in opponents kazan at index 0 and holes at indexes 1-9
     */
    public Game(String playerTuz, String opponentTuz, int[] playerHoles, int[] opponentHoles){
        initialiseGame();

        // Initializes to korgools per each hole. +1 for index because index 0 of playerHoles is for kazan.
        // -8 for opponentHoles because holeNo starts from 8 for opponent.
        for(Hole holeNo : game_board.getHoles()) {

            if(holeNo.getOwner() == player_side) {
                for (int korgoolNo = 0; korgoolNo < playerHoles[holeNo.getHoleIndex() + 1]; korgoolNo++) {
                    holeNo.addKorgool(new Korgool());
                }
            }
            else{
                for (int korgoolNo = 0; korgoolNo < opponentHoles[holeNo.getHoleIndex() - 8]; korgoolNo++) {
                    holeNo.addKorgool(new Korgool());
                }
            }
        }

        //Initialise Kazans.
        for(Kazan k : game_board.getKazans()){
            if(k.getOwner() == player_side) {
                for (int korgoolNo = 0; korgoolNo < playerHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }

            else{
                for (int korgoolNo = 0; korgoolNo < opponentHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }
        }

        //TODO: We need to add a explanation to GUI. When on player side tuz 4 is chosen, opponents hole #4 becomes tuz for player. That's the logic of this code.
        // Sets tuz for each player.
        int playerTuzNo = Integer.parseInt(playerTuz);
        int opponentTuzNo = Integer.parseInt(opponentTuz);

        if(playerTuzNo != 0) {
            game_board.getHoleByIndex(playerTuzNo + 8).markAsTuz();
            player_side.makeTuz();
        }
        if(opponentTuzNo != 0) {
            game_board.getHoleByIndex(opponentTuzNo-1).markAsTuz();
            Side.BLACK.makeTuz();
        }
    }

    public static void main(String[] args){
        // Example data
        //int[] playerData = {15,1,2,3,4,5,6,7,8,9};
        //int[] opponentData = {15,5,6,7,8,9,10,11,12,13};
        //Game game1 = new Game("5","8",playerData,opponentData);
        Game game1 = new Game();
        //TODO: Retrieve info from Custom game window.
        while(game1.game_board.getKazanByIndex(0).getKoorgools().size() <= 81 || game1.game_board.getKazanByIndex(1).getKoorgools().size() <= 81){
            Side nextToPlay = game1.game_board.getNextToPlay();
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException ie){
                System.out.println("Interrupted exception...");
            }
            //System.out.print("");
            if(nextToPlay == Side.BLACK){
                game1.game_board.challengeMove();
                System.out.println("challenge move has been made!");
            }
        }
        //TODO: End game screen after while loop ends.
    }
}
