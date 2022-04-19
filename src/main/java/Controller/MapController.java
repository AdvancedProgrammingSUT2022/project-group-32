package Controller;

import Model.Map;
import Model.Tile;
import enums.Responses.Response;

import java.util.ArrayList;

public class MapController {
    // get map from GameController
    public static int getDistanceTo(Tile start, Tile finish) {
        Map map = GameController.getGame().getMap();
        // uses Dijkstra
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Tile getNextMoveTo(Tile start, Tile finish) {
        // returns first move from start to finish
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static ArrayList<Tile> getTilesInRange(Tile tile, int range) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu BuildCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
