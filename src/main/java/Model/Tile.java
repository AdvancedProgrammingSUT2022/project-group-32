package Model;

import Model.Resources.Resource;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Tile {
    private int row, column;
    private Terrain terrain;
    private Resource resource;
    private Ruin ruin;
    private City city;  //can be null
    private Improvement improvement;
    private Unit unit;
    private Troop troop;
    private FogState fogState;
    private RouteType roadType; // can be null
    private HashMap<Integer, Integer> isRiver; // Clock-based directions: 0 - 2 - 4 - 6 - 8 - 10
    private boolean hasCitizen;

    public Tile(int row, int column, Terrain terrain, FogState fogState, Ruin ruin) {
        this.row = row;
        this.column = column;
        this.terrain = terrain;
        this.fogState = fogState;
        this.resource = null;
        this.ruin = ruin;
        this.city = null;
        this.improvement = null;
        this.unit = null;
        this.troop = null;
        this.roadType = null;
        this.isRiver = new HashMap<>();
        isRiver.put(0, 0);
        isRiver.put(2, 0);
        isRiver.put(4, 0);
        isRiver.put(6, 0);
        isRiver.put(8, 0);
        isRiver.put(10, 0);
    }

    // builds a tile based on a tile
    public Tile(Tile tile){
        this.row = tile.row;
        this.column = tile.column;
        this.terrain = tile.terrain;
        this.fogState = tile.fogState;
        this.resource = tile.resource;
        this.ruin = tile.ruin;
        this.city = tile.city;
        this.improvement = tile.improvement;
        this.unit = tile.unit;
        this.troop = tile.troop;
        this.roadType = tile.roadType;
        this.isRiver = tile.isRiver;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public TerrainType getTerrainType() {
        return terrain.getTerrainType();
    }

    public TerrainFeature getTerrainFeature() {
        return terrain.getTerrainFeature();
    }

    public TerrainFeature getBaseFeature() {
        return terrain.getBaseFeature();
    }

    public ResourceType getResourceType() {
        return terrain.getResourceType();
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

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public Unit getUnit() {
        return this.unit;
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

    public RouteType getRoadType() {
        return roadType;
    }

    public void setRoadType(RouteType roadType) {
        this.roadType = roadType;
    }

    public HashMap<Integer, Integer> getIsRiver() {
        return isRiver;
    }

    public void setIsRiver(HashMap<Integer, Integer> isRiver) {
        this.isRiver = isRiver;
    }

    public void setRiverInDirection(int direction, int river){
        isRiver.put(direction, river);
    }

    public int getRiverInDirection(int direction){
        return isRiver.get(direction);
    }

    public boolean isHasCitizen() {
        return hasCitizen;
    }

    public void setHasCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }

    public int getDirectionTo(Tile tile){
        if(tile.column == this.column){
            if(tile.row == this.row - 1) return 0;
            if(tile.row == this.row + 1) return 6;
        }
        if(this.column % 2 == 0){
            if(this.row == tile.row && this.column - 1 == tile.column) return 10;
            if(this.row == tile.row && this.column + 1 == tile.column) return 2;
            if(this.row + 1 == tile.row && this.column - 1 == tile.column) return 8;
            if(this.row + 1 == tile.row && this.column + 1 == tile.column) return 4;
        }
        else{
            if(this.row - 1 == tile.row && this.column - 1 == tile.column) return 10;
            if(this.row - 1 == tile.row && this.column + 1 == tile.column) return 2;
            if(this.row == tile.row && this.column - 1 == tile.column) return 8;
            if(this.row == tile.row && this.column + 1 == tile.column) return 4;
        }
        return -1; // meaning they are not neighbours
    }

    public Tile getTileInDirection(Map map, int direction){
        if(direction == 0) return map.getTile(this.row - 1, this.column);
        if(direction == 6) return map.getTile(this.row + 1, this.column);
        if(this.column % 2 == 0){
            if(direction == 10) return map.getTile(this.row, this.column - 1);
            if(direction == 2) return map.getTile(this.row, this.column + 1);
            if(direction == 8) return map.getTile(this.row + 1, this.column - 1);
            if(direction == 4) return map.getTile(this.row + 1, this.column + 1);
        }
        else{
            if(direction == 10) return map.getTile(this.row - 1, this.column - 1);
            if(direction == 2) return map.getTile(this.row - 1, this.column + 1);
            if(direction == 8) return map.getTile(this.row, this.column - 1);
            if(direction == 4) return map.getTile(this.row, this.column + 1);
        }
        return null;
    }

    public ArrayList<Tile> getNeighbouringTiles(Map map){
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int d = 0; d < 12; d += 2){
            if(this.getTileInDirection(map, d) != null){
                tiles.add(this.getTileInDirection(map, d));
            }
        }
        return tiles;
    }

    // the next 3 methods are for both troops and normal units
    public boolean canFit(Unit unit){
        if(unit instanceof Troop){
            return (this.troop == null);
        } else {
            return (this.unit == null);
        }
    }

    public void putUnit(Unit unit){
        if(unit instanceof Troop){
            this.troop = (Troop) unit;
        } else {
            this.unit = unit;
        }
    }

    public void takeUnit(Unit unit){
        if(unit instanceof Troop){
            this.troop = null;
        } else {
            this.unit = null;
        }
    }

    public int getMP(Tile incomingTile) {
        // TODO: 4/24/2022 roads to be handled
        int mp = 0;
        mp += terrain.getMP();
        int direction = incomingTile.getDirectionTo(this);
        if(direction == -1) return 9999;
        mp += incomingTile.getIsRiver().get(direction);
        if(this.roadType == RouteType.ROAD && incomingTile.roadType == RouteType.ROAD){
            mp --;
        }
        return mp;
    }

    public int getFood() {
        // TODO: 4/17/2022 checks food income based on Terrain object and the improvements and resource and building
        // Total food of a city is just sum of getFood()s of its tiles
        throw new RuntimeException("NOT IMPLEMENTED!");
    }

    public int getGold() {
        // TODO: anything else?
        // + : terrain, terrain Feature, resource if improved, rivers
        return terrain.getGold() +
                ((resource.getNeededImprovement().name.equals(improvement.getName())) ? resource.getGold() : 0) +
                (int) isRiver.values().stream().filter(i -> i.equals(1)).count();
    }

    public int getProduction() {
        // TODO: 4/17/2022 checks production income based on Terrain object and the improvements and resource
        throw new RuntimeException("NOT IMPLEMENTED!");

    }

}
