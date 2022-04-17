package Controller;

import Model.Map;
import Model.Tile;
import enums.Responses.Response;

import java.util.ArrayList;

public class MapController {
    // get map from GameController
    public static int getDistanceTo(Tile start, Tile finish){
        Map map = GameController.getGame().getMap();
        // uses Dijkstra
    }

    public static Tile getNextMoveTo(Tile start, Tile finish){
        // returns first move from start to finish
    }

    public static ArrayList<Tile> getTilesInRange(Tile tile, int range){

    }

    public static Response.GameMenu BuildCity(){

    }

    public static
}
