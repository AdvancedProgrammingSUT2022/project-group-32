package Model;

import Model.Resources.Resource;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.FogState;

public class Tile {
    static int count = 0;
    private int id;
    private int x, y;
    private Terrain terrain;
    private Resource resource;
    private City city;  //can be null
    private Unit unit;
    private Troop troop;
    private FogState fogState;

    public Tile(int x, int y, Terrain terrain, FogState fogState, Resource resource) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.fogState = fogState;
        this.resource = resource;
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

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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
