package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import java.awt.event.*;

public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    private Game() {
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();

        for (int i = 0; i < game_board.getHoles().length; i++) {
            Hole logicHole = game_board.getHoleByIndex(i);
            com.dominicswaine.seg_agile_project.Board.Hole guiHole = i < 9 ? gui.getHolesTopRow().get(8-i) : gui.getHolesBottomRow().get(i - 9);

            for (int korgoolNo = 0; korgoolNo <= 8; korgoolNo++)
                logicHole.addKorgool(new Korgool());

            final int holeIndex = i;
            guiHole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    game_board.redistribute(holeIndex);
                }
            });


        }
    }

    /**
     * Custom Game Constructor
     * @param playerTuz A string storing players tuz. 0 if no tuz.
     * @param opponentTuz A string storing opponents tuz. 0 if no tuz.
     * @param playerHoles An array storing number of korgools in players kazan and holes 1-9.
     * @param opponentHoles An array storing number of korgools in opponents kazan and holes 1-9.
     */
    private Game(String playerTuz, String opponentTuz, int[] playerHoles, int[] opponentHoles){
        this();

        //System.out.println("Creating holes...");
        // Initializes to korgools per each hole. +1 for index because index 0 of playerHoles is for kazan.
        for(com.dominicswaine.seg_agile_project.Logic.Hole h : game_board.getHoles()) {

            if(h.getOwner() == player_side) {
                //System.out.println("Add " + (playerHoles[h.getHoleIndex() + 1]) + "korgools to " + player_side + " Hole Number: " + h.getHoleIndex());
                for (int korgoolNo = 0; korgoolNo < playerHoles[h.getHoleIndex() + 1]; korgoolNo++) {
                    h.addKorgool(new Korgool());
                }
            }

            else{
                //System.out.println("Add " + (opponentHoles[h.getHoleIndex() - 9]) + "korgools to " + Side.BLACK + " Hole Number: " + h.getHoleIndex());
                for (int korgoolNo = 0; korgoolNo < opponentHoles[h.getHoleIndex() - 9]; korgoolNo++) {
                    h.addKorgool(new Korgool());
                }
            }
        }

        //Initialise Kazans.
        for(com.dominicswaine.seg_agile_project.Logic.Kazan k : game_board.getKazans()){
            //System.out.println("Initialising kazans...");
            if(k.getOwner() == player_side) {
                //System.out.println("Add " + playerHoles[0] + "korgools to " + k.getOwner() + " Kazan");
                for (int korgoolNo = 0; korgoolNo < playerHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }

            else{
                //System.out.println("Add " + opponentHoles[0] + "korgools to " + Side.BLACK + " Kazan");
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
        //System.out.println(game_board.getHoleByIndex(playerTuzNo + 9).getHoleIndex() + " Numbered Hole is marked az tuz for " + game_board.getHoleByIndex(playerTuzNo).getOwner());
        if(opponentTuzNo != 0) {
            game_board.getHoleByIndex(opponentTuzNo).markAsTuz();
        }
        //System.out.println(game_board.getHoleByIndex(opponentTuzNo).getHoleIndex() + " Numbered Hole is marked az tuz for" + game_board.getHoleByIndex(opponentTuzNo + 9).getOwner());
    }

    public static void main(String[] args){
       int playerdata[] = {35,6,6,7,8,3,15,8,9,1};
       int opponentdata[] = {25,8,4,1,0,0,2,12,5,4};
       new Game("2","5",playerdata,opponentdata);
    }
}
