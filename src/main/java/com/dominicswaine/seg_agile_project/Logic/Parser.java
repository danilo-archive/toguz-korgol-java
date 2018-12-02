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


        JSONArray p1 = new JSONArray();
        JSONObject player1Kazan = new JSONObject();
        player1Kazan.put("kazan",kazans[0].getNumberOfKoorgools());
        p1.add(player1Kazan);
        for(int i = 0 ; i < 9 ; ++i) {
            JSONObject player1Hole = new JSONObject();
            player1Hole.put("hole:" + i, holes[i].getNumberOfKoorgools() );
            p1.add(player1Hole);
        }

        obj1.put("config",p1);

        // Configuration for player '2'


        JSONArray p2 = new JSONArray();
        JSONObject player2Kazan = new JSONObject();
        player2Kazan.put("kazan",kazans[0].getNumberOfKoorgools());
        p1.add(player2Kazan);
        for(int i = 9 ; i < 18 ; ++i) {
            JSONObject player2Hole = new JSONObject();
            player2Hole.put("hole:" + i, holes[i].getNumberOfKoorgools() );
            p1.add(player2Hole);
        }

        obj2.put("config",p1);

        // Put everything in the object to be returned `obj`
        obj.put("player1",obj1);
        obj.put("player2",obj2);
    }

    public void writeToFile(String filePath) {

    }

    public void readFromFile(String filePath) {

    }

}
