package Model;

import Model.Resources.Resource;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.FogState;
import enums.RoadType;

import java.util.HashMap;

public class Tile {
    private static int count = 0;
    private int id;
    private int x, y;
    private Terrain terrain;
    private Resource resource;
    private Ruin ruin;
    private City city;  //can be null
    private Unit unit;
    private Troop troop;
    private FogState fogState;
    private RoadType roadType; // can be null
    private HashMap<Integer, Boolean> isRiver; // Clock-based directions: 0 - 2 - 4 - 6 - 8 - 10

    public Tile(int x, int y, Terrain terrain, FogState fogState, Resource resource, Ruin ruin) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.fogState = fogState;
        this.resource = resource;
        this.ruin = ruin;
        this.city = null;
        this.unit = null;
        this.troop = null;
        this.roadType = null;
        this.id = count;
        this.isRiver = new HashMap<>(); // to be yad gerefte beshe
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

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Ruin getRuin() {
        return ruin;
    }

    public void setRuin(Ruin ruin) {
        this.ruin = ruin;
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    public HashMap<Integer, Boolean> getIsRiver() {
        return isRiver;
    }

    public void setIsRiver(HashMap<Integer, Boolean> isRiver) {
        this.isRiver = isRiver;
    }

    public int getMP() {
        // TODO: 4/17/2022 checks MP based on Terrain object and the improvements and resource
        return 0;
    }

    public int getFood() {
        // TODO: 4/17/2022 checks food income based on Terrain object and the improvements and resource and building
        // Total food of a city is just sum of getFood()s of its tiles
        return 0;
    }

    public int getGold() {
        // TODO: 4/17/2022 checks gold income based on Terrain object and the improvements and resource

        return 0;
    }

    public int getProduction() {
        // TODO: 4/17/2022 checks production income based on Terrain object and the improvements and resource
        return 0;
    }

}
