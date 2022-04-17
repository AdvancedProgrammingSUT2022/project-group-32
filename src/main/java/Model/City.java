package Model;

import Model.Citizens.Citizen;
import Model.Units.Unit;

import java.util.ArrayList;

public class City {
    private String name;
    private Player owner;
    private Tile capitalTile; // TODO: 4/17/2022 Kaakh must be handled
    private ArrayList<Tile> territory = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private int gold, food, production, population, health, baseStrength; // TODO: 4/17/2022 is Gold for a city or for a nation??

    public City(String name, Player owner, Tile capitalTile, ArrayList<Tile> territory) {
        this.name = name;
        this.owner = owner;
        this.capitalTile = capitalTile;
        this.territory = territory;
        // TODO: 4/17/2022 setts buildings, citizens, gold, food, .... to default value;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    private ArrayList<Unit> getUnits() {
        // TODO: 4/17/2022 returns units in city
        return new ArrayList<Unit>();
    }

    private int getStrength() {
        // TODO: 4/17/2022  by units and baseStrength
        return 0;
    }

    public void addCitizen() {
        // TODO: 4/17/2022
    }

    // TODO: 4/17/2022 getCitizenByID, removeCitizen, addTile, RemoveTile,( expand in Controller), getTile, getNeighbors, get


}
