package Model;

import Model.Citizens.Citizen;
import Model.Units.Unit;
import enums.BuildingType;
import enums.UnitType;

import java.util.ArrayList;
import java.util.Arrays;

public class City {
    private String name;
    private Player owner;
    private Tile capitalTile; // TODO: 4/17/2022 Palace must be handled
    private ArrayList<Tile> territory;
    private ArrayList<Building> buildings;
    private Building buildingInProgress;
    private ArrayList<Building> incompleteBuildings;
    private Unit unitInProgress;
    private ArrayList<Unit> incompleteUnits;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private int food, production, population, health, baseStrength;

    public City(String name, Player owner, Tile capitalTile, ArrayList<Tile> territory) {
        this.name = name;
        this.owner = owner;
        this.capitalTile = capitalTile;
        this.territory = territory;
        // TODO: 4/17/2022 setts buildings, citizens, gold, food, .... to default value. and empty arraylists
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Tile getCapitalTile() {
        return capitalTile;
    }

    public void setCapitalTile(Tile capitalTile) {
        this.capitalTile = capitalTile;
    }

    public ArrayList<Tile> getTerritory() {
        return territory;
    }

    public void setTerritory(ArrayList<Tile> territory) {
        this.territory = territory;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<Citizen> citizens) {
        this.citizens = citizens;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public Building getBuildingInProgress() {
        return buildingInProgress;
    }

    public void setBuildingInProgress(Building buildingInProgress) {
        this.buildingInProgress = buildingInProgress;
    }

    public ArrayList<Building> getIncompleteBuildings() {
        return incompleteBuildings;
    }

    public void setIncompleteBuildings(ArrayList<Building> incompleteBuildings) {
        this.incompleteBuildings = incompleteBuildings;
    }

    public Unit getUnitInProgress() {
        return unitInProgress;
    }

    public void setUnitInProgress(Unit unitInProgress) {
        this.unitInProgress = unitInProgress;
    }

    public ArrayList<Unit> getIncompleteUnits() {
        return incompleteUnits;
    }

    public void setIncompleteUnits(ArrayList<Unit> incompleteUnits) {
        this.incompleteUnits = incompleteUnits;
    }

    public void addTile(Tile tile) {
        this.territory.add(tile);
    }

    public void removeTile(Tile tile) {
        this.territory.remove(tile);
    }

    public Tile getTileByXY(int x, int y) {
        for (Tile tile : territory) {
            if (tile.getRow() == x && tile.getColumn() == y) {
                return tile;
            }
        }
        return null;
    }

    public Tile getTileByID(int id) {
        for (Tile tile : territory) {
            if (tile.getId() == id) {
                return tile;
            }
        }
        return null;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }

    public Building getBuildingByName(String name) {
        for (Building building : buildings) {
            if (building.getName().equals(name)) {
                return building;
            }
        }
        return null;
    }

    public Building getBuildingByType(BuildingType buildingType) {
        for (Building building : buildings) {
            if (building.getBuildingType().equals(buildingType)) {
                return building;
            }
        }
        return null;
    }

    public void addIncompleteBuilding(Building building) {
        this.incompleteBuildings.add(building);
    }

    public void removeIncompleteBuilding(Building building) {
        this.incompleteBuildings.remove(building);
    }

    public Building getIncompleteBuildingByName(String name) {
        for (Building building : incompleteBuildings) {
            if (building.getName().equals(name)) {
                return building;
            }
        }
        return null;
    }

    public Building getIncompleteBuildingByType(BuildingType buildingType) {
        for (Building building : incompleteBuildings) {
            if (building.getBuildingType().equals(buildingType)) {
                return building;
            }
        }
        return null;
    }

    public void addIncompleteUnit(Unit unit) {
        this.incompleteUnits.add(unit);
    }

    public void removeIncompleteUnit(Unit unit) {
        this.incompleteUnits.remove(unit);
    }

    public Unit getIncompleteUnitByType(UnitType unitType) {
        for (Unit incompleteUnit : incompleteUnits) {
            if (incompleteUnit.getUnitType().equals(unitType)) {
                return incompleteUnit;
            }
        }
        return null;
    }

    public void addCitizen(Citizen citizen) {
        this.citizens.add(citizen);
    }

    public void removeCitizen(Citizen citizen) {
        this.citizens.remove(citizen);
    }

    public Citizen getCitizenByName(String name) {
        for (Citizen citizen : citizens) {
            if (citizen.getName().equals(name)) {
                return citizen;
            }
        }
        return null;
    }

    private ArrayList<Unit> getUnits() {
        // returns unit in territory (but why??)
        ArrayList<Unit> units = new ArrayList<>();
        for (Tile tile : territory) {
            units.addAll(Arrays.asList(tile.getUnit()));
        }
        return units;
    }

    private int getStrength() {
        // TODO: 4/17/2022  by units and baseStrength
        return 0;
    }

    // TODO: 4/17/2022 getCitizenByID, removeCitizen, addTile, RemoveTile,( expand in Controller), getTile, getNeighbors, get


}
