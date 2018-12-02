package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import java.awt.event.*;

public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    public Game() {
        player_side = Side.WHITE;
        game_board = new Board();

        for(com.dominicswaine.seg_agile_project.Logic.Hole h : game_board.getHoles()) {
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
            h.addKorgool(new Korgool());
        }

        gui = new GameWindow();

        for(int i = 0; i<=8;i++){
            com.dominicswaine.seg_agile_project.Board.Hole hole = gui.getHolesTopRow().get(i);
            hole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game_board.redistribute(hole.getN()+8);
                }
            });
        }

        for(int i = 0; i<8;i++){
            com.dominicswaine.seg_agile_project.Board.Hole hole = gui.getHolesBottomRow().get(i);
            hole.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game_board.redistribute(hole.getN()-1);
                }
            });
        }
    }

    public static void main(String[] args){
        new Game();
    }
}
