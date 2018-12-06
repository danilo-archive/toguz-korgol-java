package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import com.dominicswaine.seg_agile_project.Board.HoleUI;
import com.dominicswaine.seg_agile_project.Board.KazanUI;

import java.awt.event.*;

public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    private void initialiseGame() {
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();

        for (int i = 0; i < game_board.getHoles().length; i++) {
            Hole logicHole = game_board.getHoleByIndex(i);
            HoleUI guiHole = i < 9 ? gui.getHolesTopRow().get(8 - i) : gui.getHolesBottomRow().get(i - 9);
            logicHole.setGui(guiHole);

            final int holeIndex = i;
            guiHole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game_board.redistribute(holeIndex);
                }
            });
        }

        for(int i = 0; i < game_board.getKazans().length; i++){
            Kazan logicKazan = game_board.getKazanByIndex(i);
            KazanUI guiKazan = gui.getKazans().get(i);
            logicKazan.setGui(guiKazan);
        }
    }

    public Game() {
        initialiseGame();
        for(Hole hole : game_board.getHoles()){
            for(int i = 0; i<9; i++){
                hole.addKorgool(new Korgool());
            }
        }
    }

    /**
     * Custom Game Constructor
     * @param playerTuz A string storing players tuz. 0 if no tuz.
     * @param opponentTuz A string storing opponents tuz. 0 if no tuz.
     * @param playerHoles An array storing number of korgools in players kazan and holes 1-9.
     * @param opponentHoles An array storing number of korgools in opponents kazan and holes 1-9.
     */
    public Game(String playerTuz, String opponentTuz, int[] playerHoles, int[] opponentHoles){
        initialiseGame();

        //System.out.println("Creating holes...");
        // Initializes to korgools per each hole. +1 for index because index 0 of playerHoles is for kazan.
        for(com.dominicswaine.seg_agile_project.Logic.Hole h : game_board.getHoles()) {

            if(h.getOwner() == player_side) {
                //System.out.println("Add " + (playerHoles[h.getHoleIndex() + 1]) + "korgools to " + player_side + " HoleUI Number: " + h.getHoleIndex());
                for (int korgoolNo = 0; korgoolNo < playerHoles[h.getHoleIndex() + 1]; korgoolNo++) {
                    h.addKorgool(new Korgool());
                }
            }

            else{
                //System.out.println("Add " + (opponentHoles[h.getHoleIndex() - 9]) + "korgools to " + Side.BLACK + " HoleUI Number: " + h.getHoleIndex());
                for (int korgoolNo = 0; korgoolNo < opponentHoles[h.getHoleIndex() - 9]; korgoolNo++) {
                    h.addKorgool(new Korgool());
                }
            }
        }

        //Initialise Kazans.
        for(com.dominicswaine.seg_agile_project.Logic.Kazan k : game_board.getKazans()){
            //System.out.println("Initialising kazans...");
            if(k.getOwner() == player_side) {
                //System.out.println("Add " + playerHoles[0] + "korgools to " + k.getOwner() + " KazanUI");
                for (int korgoolNo = 0; korgoolNo < playerHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }

            else{
                //System.out.println("Add " + opponentHoles[0] + "korgools to " + Side.BLACK + " KazanUI");
                for (int korgoolNo = 0; korgoolNo < opponentHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }
        }

        //Initialise player & opponent tuz.
        //System.out.println("Initialising tuz");
        //TODO: We need to add a explanation to GUI. When on player side tuz 4 is chosen, opponents hole #4 becomes tuz for player. That's the logic of this code.
        int playerTuzNo = Integer.parseInt(playerTuz);
        int opponentTuzNo = Integer.parseInt(opponentTuz);

        if(playerTuzNo != 0) {
            game_board.getHoleByIndex(playerTuzNo + 9).markAsTuz();
        }
        //System.out.println(game_board.getHoleByIndex(playerTuzNo + 9).getHoleIndex() + " Numbered HoleUI is marked az tuz for " + game_board.getHoleByIndex(playerTuzNo).getOwner());
        if(opponentTuzNo != 0) {
            game_board.getHoleByIndex(opponentTuzNo).markAsTuz();
        }
        //System.out.println(game_board.getHoleByIndex(opponentTuzNo).getHoleIndex() + " Numbered HoleUI is marked az tuz for" + game_board.getHoleByIndex(opponentTuzNo + 9).getOwner());
    }

    public static void main(String[] args){
        Game game1 = new Game();
        //TODO: Make player chose between AI opponent or Easy opponent.
        while(game1.game_board.getKazanByIndex(0).getKoorgools().size() <= 81 || game1.game_board.getKazanByIndex(1).getKoorgools().size() <= 81){
            Side nextToPlay = game1.game_board.getNextToPlay();
            try {
                Thread.sleep(2000);
                System.out.print("I slept");
            }
            catch(InterruptedException ie){
                System.out.println("Thinking...");
            }
            //System.out.print("");
            if(nextToPlay == Side.BLACK){
                game1.game_board.challengeMove();
                System.out.println("Random move has been made");
            }
        }
//        int playerdata[] = {35,6,6,7,8,3,15,8,9,1};
//        int opponentdata[] = {25,8,4,1,0,0,2,12,5,4};
//        new Game("2","5",playerdata,opponentdata);
    }
}
