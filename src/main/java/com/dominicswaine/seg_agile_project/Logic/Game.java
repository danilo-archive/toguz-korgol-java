package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import com.dominicswaine.seg_agile_project.Board.HoleUI;
import com.dominicswaine.seg_agile_project.Board.KazanUI;
import com.dominicswaine.seg_agile_project.Board.ScoreboardUI;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.File;

/**
 * @author Ayberk Demirkol, Dominic Swaine
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
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();
        gui.getScoreboard().getSaveButton().addActionListener(e-> saveCurrentState());


        for (int holeNo = 0; holeNo < game_board.getHoles().length; holeNo++) {
            Hole logicHole = game_board.getHoleByIndex(holeNo);
            HoleUI guiHole = holeNo < 9 ? gui.getHolesTopRow().get(8 - holeNo) : gui.getHolesBottomRow().get(holeNo - 9);
            logicHole.setGui(guiHole);

            final int holeIndex = holeNo;
            // Statement below adds mouselisteners to each hole. When a mouse is pressed on top of a hole, it redistributes that hole.
            // When mouse is released, after 1 sec AI makes the move.
            // @checks if player earns a tuz after the move, it they do; a new mouse pressed and released is added to Tuz Hole. If the player doesnt earn a tuz,
            // game continues normally. After every move, scoreboard is updated.
            if (logicHole.getOwner() == Side.WHITE) {
                guiHole.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(game_board.getNextToPlay() == Side.WHITE) {
                            if (game_board.getKazanByIndex(0).getKoorgools().size() <= 81 && game_board.getKazanByIndex(1).getKoorgools().size() <= 81) {
                                if(!Side.WHITE.hasTuz()) {
                                    game_board.redistribute(holeIndex);
                                    gui.getScoreboard().update();
                                    if(Side.WHITE.hasTuz()){
                                        int tuzHoleIndex = game_board.getPlayerTuz(Side.WHITE);
                                        Hole tuzHole = game_board.getHoleByIndex(tuzHoleIndex);
                                        HoleUI tuzHoleUI = tuzHole.getGui();
                                        tuzHoleUI.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                if(game_board.getNextToPlay() == Side.WHITE && game_board.getKazanByIndex(0).getKoorgools().size() <= 81 && game_board.getKazanByIndex(1).getKoorgools().size() <= 81) {

                                                    game_board.redistribute(tuzHoleIndex);
                                                    gui.getScoreboard().update();
                                                }
                                            }

                                            @Override
                                            public void mouseReleased(MouseEvent e) {
                                                if(game_board.getNextToPlay() == Side.BLACK && game_board.getKazanByIndex(0).getKoorgools().size() <= 81 && game_board.getKazanByIndex(1).getKoorgools().size() <= 81) {
                                                    try{
                                                        Thread.sleep(1000);
                                                    }
                                                    catch(InterruptedException exception){
                                                        System.out.println("main thread interrupted");
                                                    }
                                                    game_board.challengeMove();
                                                    gui.getScoreboard().update();
                                                }
                                            }
                                        });
                                    }
                                }
                                else{
                                    game_board.redistribute(holeIndex);
                                    gui.getScoreboard().update();
                                }
                            }
                        }
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(game_board.getNextToPlay() == Side.BLACK && game_board.getKazanByIndex(0).getKoorgools().size() <= 81 && game_board.getKazanByIndex(1).getKoorgools().size() <= 81) {
                            try{
                                Thread.sleep(1000);
                            }
                            catch(InterruptedException exception){
                                System.out.println("main thread interrupted");
                            }
                            game_board.challengeMove();
                            gui.getScoreboard().update();
                        }
                    }

                });
            }
        }

        for(int i = 0; i < game_board.getKazans().length; i++){
            Kazan logicKazan = game_board.getKazanByIndex(i);
            KazanUI guiKazan = gui.getKazans().get(i);
            logicKazan.setGui(guiKazan);
        }
    }

    private void saveCurrentState() {
        Hole[] holes = game_board.getHoles();
        Kazan[] kazans = game_board.getKazans();

        int[] playerHoles = new int[10];
        int[] opponentHoles = new int[10];

        // Handling Player Tuz
        String playerTuz = "";
        boolean changedPT = false;
        for(int i = 0 ; i < 9 ; ++i) {
            if (holes[i].isTuz()) {
                changedPT = true;
                playerTuz = Integer.toString(i+1);
            }
        }
        if(!changedPT) {
            playerTuz = "0";
        }

        // Handling Opponent Tuz
        String opponentTuz = "";
        boolean changedOT = false;
        for(int i = 9 ; i < 17 ; ++i) {
            if (holes[i].isTuz()) {
                changedOT = true;
                opponentTuz = Integer.toString(i-8);
            }
        }
        if(!changedOT) {
            opponentTuz = "0";
        }

        // Handling player and opponent holes

        playerHoles[0] = kazans[0].getNumberOfKoorgools();
        opponentHoles[0] = kazans[1].getNumberOfKoorgools();
        for (int i = 1 ; i < 10 ; ++i) {
            playerHoles[i] = holes[i-1].getNumberOfKoorgools();
            opponentHoles[i] = holes[i+8].getNumberOfKoorgools();
        }

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            Parser.saveCustomGame(selectedFile.getAbsolutePath(), playerTuz, opponentTuz, playerHoles, opponentHoles);

            JOptionPane.showMessageDialog(null,
                    "Your game has been saved.",
                    "Saved Game",
                    JOptionPane.INFORMATION_MESSAGE);

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

    public GameWindow getGui(){
        return gui;
    }

    public Board getGameBoard() {
        return game_board;
    }

    public Side getPlayerSide() {
        return player_side;
    }
}
