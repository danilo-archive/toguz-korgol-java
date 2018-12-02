package com.dominicswaine.seg_agile_project.Logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    private JSONObject obj;
    private Board board;

    public Parser(Board board) {
        obj = new JSONObject();
        this.board = board;
    }

    public void addContent() {
        Hole[] holes = board.getHoles();
        Kazan[] kazans = board.getKazans();

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();


        // Configuration for player '1'
        obj1.put("name","player1");

        JSONArray p1 = new JSONArray();
        JSONObject playersKazan = new JSONObject();
        playersKazan.put("kazan",kazans[0].getNumberOfKoorgools());
        p1.add(playersKazan);
        for(int i = 0 ; i < 9 ; ++i) {
            JSONObject player1Hole = new JSONObject();
            player1Hole.put("hole:" + i, holes[i].getNumberOfKoorgools() );
            p1.add(player1Hole);
        }

        obj1.put("config",p1);
    }

    public void writeToFile(String filePath) {

    }

    public void readFromFile(String filePath) {

    }

}
