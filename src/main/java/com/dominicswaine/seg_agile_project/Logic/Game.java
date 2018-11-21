package com.dominicswaine.seg_agile_project.Logic;
import com.dominicswaine.seg_agile_project.Board.GameWindow;

public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    public Game() {
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();
    }

    public static void main(String[] args){
        new Game();
    }
}
