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

    public Player(User user, Map map, int cameraX, int cameraY) {
        this.user = user;
//        this.map = map;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void updateFieldOfView() {
        // TODO: 4/16/2022
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<Player> getInWarPlayers() {
        return inWarPlayers;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void addCity(City city) {
        if (city == null) {
            System.err.println("null city");
        }
        cities.add(city);
    }
    // TODO: 4/17/2022 adder remover getByName for ArrayLists
}
