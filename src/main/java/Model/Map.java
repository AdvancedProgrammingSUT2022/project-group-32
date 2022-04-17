package Model;

import Model.Units.Unit;

import java.util.ArrayList;

public class Map {
    private final int width;
    private final int height;
    private Tile[][] tiles;
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Unit> units = new ArrayList<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        // TODO: 4/17/2022 initialized tiles , ....
    }

}
