package Model;

import Model.Units.Unit;
import enums.BuildingType;
import enums.TechnologyType;

import java.util.ArrayList;

public class Player {
    private final User user;
    private Map map;
    private final ArrayList<Unit> units;
    private final ArrayList<Building> buildings;
    private final ArrayList<Technology> technologies;
    private final ArrayList<City> cities;
    private final ArrayList<Tile> tiles;
    private int gold, science, food, XP, happiness, population;
    private final ArrayList<Player> inWarPlayers;
    private final ArrayList<String> notifications;
    private int cameraX;
    private int cameraY;

    public Player(User user, Map map, int cameraX, int cameraY) {
        this.user = user;
        this.map = map;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>(); // may need to change this
        this.technologies = new ArrayList<>();
        this.cities = new ArrayList<>(); // and this
        this.tiles = new ArrayList<>();
        this.inWarPlayers = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String showMap(){
        // shows players view of the map - currently in string form to be later replaces with graphics
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

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    public void setCamera(int cameraX, int cameraY){
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    // TODO: 4/17/2022 adder remover getByName for ArrayLists

    public void addUnit(Unit unit){
        this.units.add(unit);
    }

    public void removeUnit(Unit unit){
        this.units.remove(unit);
    }

    public void addBuilding(Building building){
        this.buildings.add(building);
    }

    public void removeBuilding(Building building){
        this.buildings.remove(building);
    }

    public Building getBuildingByName(String name){
        for (Building building : buildings) {
            if(building.getName().equals(name)){
                return building;
            }
        }
        return null;
    }

    public Building getBuildingByType(BuildingType buildingType){
        for (Building building : buildings) {
            if(building.getBuildingType().equals(buildingType)){
                return building;
            }
        }
        return null;
    }

    public void addTechnology(Technology technology){
        this.technologies.add(technology);
    }

    public void removeTechnology(Technology technology){
        this.technologies.remove(technology);
    }

    public Technology getTechnologyByName(String name){

    }

    public Technology getTechnologyByType(TechnologyType technologyType){

    }

    public void addCity(City city){

    }

    public void removeCity(City city){

    }

    public City getCityByName(String name){

    }

    public City getCityByXY(int x, int y){

    }

    public void addTile(Tile tile){

    }

    public void removeTile(Tile tile){

    }

    public Tile getTileByXY(int x, int y){

    }

    public void addInWarPlayer(Player player){

    }

    public void removeInWarPlayer(Player player){

    }

    public Player getInWarPlayerByName(String name){

    }

    public void addNotification(String notification){

    }
}
