package Model;

import Model.Units.Troop;
import Model.Units.Unit;
import enums.FogState;
import enums.TerrainType;

public class Tile {
    static int count = 0;
    private int id;
    private int x, y;
    private TerrainType terrain;

    private City city;  //can be null
    private Unit unit;
    private Troop troop;
    private FogState fogState;

    public Tile(int x, int y, TerrainType terrain, FogState fogState) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.fogState = fogState;
        this.city = null;
        this.unit = null;
        this.troop = null;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public FogState getFogState() {
        return fogState;
    }

    public void setFogState(FogState fogState) {
        this.fogState = fogState;
    }

    public int getMP() {
        // TODO: 4/16/2022
        return 0;
    }


}
