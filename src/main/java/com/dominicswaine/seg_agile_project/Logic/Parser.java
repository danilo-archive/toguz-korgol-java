package com.dominicswaine.seg_agile_project.Logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Parser {

    private JSONObject obj;
    private Board board;

    public Parser(Board board) {
        obj = new JSONObject();
        this.board = board;
    }

    @SuppressWarnings("unchecked")
    public void addContent() {
        Hole[] holes = board.getHoles();
        Kazan[] kazans = board.getKazans();

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONArray players = new JSONArray();

        // Tuzes
        int whiteTuzIndex = board.getPlayerTuz(Side.WHITE);
        int blackTuzIndex = board.getPlayerTuz(Side.BLACK);

        // Configuration for player '1'

        obj1.put("name","white");

        JSONObject whiteTuz = new JSONObject();
        whiteTuz.put("tuz", whiteTuzIndex);

        JSONArray p1 = new JSONArray();
        JSONObject player1Kazan = new JSONObject();
        player1Kazan.put("kazan",kazans[0].getNumberOfKoorgools());
        p1.add(player1Kazan);
        p1.add(whiteTuz);
        for(int i = 0 ; i < 9 ; ++i) {
            JSONObject player1Hole = new JSONObject();
            player1Hole.put("hole:" + i, holes[i].getNumberOfKoorgools() );
            p1.add(player1Hole);
        }

        obj1.put("config",p1);

        // Configuration for player '2'

        obj2.put("name","black");


        JSONObject blackTuz = new JSONObject();
        blackTuz.put("tuz", blackTuzIndex);

        JSONArray p2 = new JSONArray();
        JSONObject player2Kazan = new JSONObject();
        player2Kazan.put("kazan",kazans[0].getNumberOfKoorgools());
        p2.add(player2Kazan);
        p2.add(blackTuz);
        for(int i = 9 ; i < 18 ; ++i) {
            JSONObject player2Hole = new JSONObject();
            player2Hole.put("hole:" + (i - 9), holes[i].getNumberOfKoorgools() );
            p2.add(player2Hole);
        }

        obj2.put("config",p2);

        // Put everything in the object to be returned `obj`
        players.add(obj1);
        players.add(obj2);

        obj.put("players",players);
    }

    public void writeToFile(String filePath) {
        try(FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map readFromFile(String filePath) {
        JSONParser parser = new JSONParser();
        Map<String,Integer> whiteSideMap = new HashMap<>();
        Map<String,Integer> blackSideMap = new HashMap<>();
        Map<String,Map<String,Integer>> doubleMap = new HashMap<>();
        try {
            // Disentangle the parent JSON object
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray listOfPlayers = (JSONArray) jsonObj.get("players");

            // Getting the data from the 'players' JSONArray
            JSONObject whiteSideData = (JSONObject) listOfPlayers.get(0);
            JSONObject blackSideData = (JSONObject) listOfPlayers.get(1);

            // Parsing white side data:
            JSONArray whiteSideHoles = (JSONArray) whiteSideData.get("config");

            JSONObject kazanObj = (JSONObject) whiteSideHoles.get(0);
            whiteSideMap.put("kazan", Math.toIntExact((Long) kazanObj.get("kazan")));

            JSONObject tuzObj = (JSONObject) whiteSideHoles.get(1);
            whiteSideMap.put("tuz", Math.toIntExact((Long) tuzObj.get("tuz")));

            for(int i = 2 ; i < whiteSideHoles.size() ; ++i) {
                JSONObject holeI = (JSONObject) whiteSideHoles.get(i);
                whiteSideMap.put("hole:" + (i-2), Math.toIntExact((Long) holeI.get("hole:"+(i-2))));
            }

            // Parsing black side data:
            JSONArray blackSideHoles = (JSONArray) blackSideData.get("config");

            kazanObj = (JSONObject) blackSideHoles.get(0);
            tuzObj = (JSONObject) blackSideHoles.get(1);

            blackSideMap.put("kazan",Math.toIntExact((Long) kazanObj.get("kazan")));
            blackSideMap.put("tuz",Math.toIntExact((Long) tuzObj.get("tuz")));

            for(int i = 2 ; i < blackSideHoles.size() ; ++i) {
                JSONObject holeI = (JSONObject) whiteSideHoles.get(i);
                blackSideMap.put("hole:" + (i-2), Math.toIntExact((Long) holeI.get("hole:"+(i-2))));
            }

            // Put everything in the double map
            doubleMap.put("white",whiteSideMap);
            doubleMap.put("black",blackSideMap);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return doubleMap;

    }

}
