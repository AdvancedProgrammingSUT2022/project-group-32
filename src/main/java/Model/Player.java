package Model;

import Model.Units.Unit;

import java.util.ArrayList;

public class Player {
    private final User user;
    private Map map;
    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<Building> buildings = new ArrayList<>();
    private final ArrayList<Technology> technologies = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private int gold, science, food, XP, happiness, population;
    private final ArrayList<Player> inWarPlayers = new ArrayList<>();
    private final int cameraX;
    private final int cameraY;

    // TODO: 4/16/2022 this class is very incomplete!!!
    public Player(User user, Map map, int cameraX, int cameraY) {
        this.user = user;
//        this.map = map;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void updateFieldOfView() {
        // TODO: 4/16/2022
    }

}
