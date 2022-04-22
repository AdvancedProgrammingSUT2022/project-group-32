package Model;

import Model.Units.Unit;
import enums.BuildingType;
import enums.Color;
import enums.TechnologyType;

import java.util.ArrayList;

public class Player {
    private final int id;
    private static int count = 0;
    private final User user;
    private final String name;
    private Map map;
    private final ArrayList<Unit> units;
    private final ArrayList<Building> buildings;
    private final ArrayList<Technology> technologies;
    private Technology technologyInProgress;
    private final ArrayList<Technology> incompleteTechnologies;
    private final ArrayList<City> cities;
    private final ArrayList<Tile> tiles;
    private int gold, science, food, XP, happiness, population;
    private final ArrayList<Player> inWarPlayers;
    private final ArrayList<String> notifications;
    private int cameraRow;
    private int cameraColumn;
    private final Color backgroundColor;
    private final Color color;

    public Player(User user, int cameraRow, int cameraColumn) {
        // TODO: 4/21/2022 players map must be set after all players are created
        this.user = user;
        this.name = user.getNickname();
        this.cameraRow = cameraRow;
        this.cameraColumn = cameraColumn;
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>(); // may need to change this
        this.technologies = new ArrayList<>();
        this.cities = new ArrayList<>(); // and this
        this.tiles = new ArrayList<>();
        this.inWarPlayers = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.incompleteTechnologies = new ArrayList<>();
        this.id = count;
        this.backgroundColor = Color.values()[this.id];
        this.color = Color.values()[this.id + 8];

        count++;
    }

    public String showMap() {
        // shows players view of the map - currently in string form to be later replaces with graphics
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // TODO: 4/18/2022
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
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

    public Technology getTechnologyInProgress() {
        return technologyInProgress;
    }

    public void setTechnologyInProgress(Technology technologyInProgress) {
        this.technologyInProgress = technologyInProgress;
    }

    public ArrayList<Technology> getIncompleteTechnologies() {
        return incompleteTechnologies;
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

    public int getRowP() {
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

    public int getCameraRow() {
        return cameraRow;
    }

    public int getCameraColumn() {
        return cameraColumn;
    }

    public void setCameraRow(int cameraRow) {
        this.cameraRow = cameraRow;
    }

    public void setCameraColumn(int cameraColumn) {
        this.cameraColumn = Player.this.cameraColumn;
    }

    public void setCamera(int cameraRow, int cameraColumn) {
        this.cameraRow = cameraRow;
        this.cameraColumn = cameraColumn;
    }

    public void setCamera(Tile tile) {
        this.setCamera(tile.getRow(), tile.getColumn());
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
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

    public void addTechnology(Technology technology) {
        this.technologies.add(technology);
    }

    public void removeTechnology(Technology technology) {
        this.technologies.remove(technology);
    }

    public Technology getTechnologyByName(String name) {
        for (Technology technology : technologies) {
            if (technology.getName().equals(name)) {
                return technology;
            }
        }
        return null;
    }

    public Technology getTechnologyByType(TechnologyType technologyType) {
        for (Technology technology : technologies) {
            if (technology.getTechnologyType().equals(technologyType)) {
                return technology;
            }
        }
        return null;
    }

    public void addIncompleteTechnology(Technology technology) {
        this.incompleteTechnologies.add(technology);
    }

    public void removeIncompleteTechnology(Technology technology) {
        this.incompleteTechnologies.remove(technology);
    }

    public Technology getIncompleteTechnologyByName(String name) {
        for (Technology technology : incompleteTechnologies) {
            if (technology.getName().equals(name)) {
                return technology;
            }
        }
        return null;
    }

    public Technology getIncompleteTechnologyByType(TechnologyType technologyType) {
        for (Technology technology : incompleteTechnologies) {
            if (technology.getTechnologyType().equals(technologyType)) {
                return technology;
            }
        }
        return null;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public void removeCity(City city) {
        this.cities.remove(city);
    }

    public City getCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public City getCityByXY(int x, int y) {
        for (City city : cities) {
            if (city.getCapitalTile().getRow() == x && city.getCapitalTile().getColumn() == y) {
                return city;
            }
        }
        return null;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        this.tiles.remove(tile);
    }

    public Tile getTileByXY(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.getRow() == x && tile.getColumn() == y) {
                return tile;
            }
        }
        return null;
    }

    public Tile getTileByID(int id) {
        for (Tile tile : tiles) {
            if (tile.getId() == id) {
                return tile;
            }
        }
        return null;
    }

    public void addInWarPlayer(Player player) {
        this.inWarPlayers.add(player);
    }

    public void removeInWarPlayer(Player player) {
        this.inWarPlayers.remove(player);
    }

    public Player getInWarPlayerByName(String name) {
        for (Player inWarPlayer : inWarPlayers) {
            if (inWarPlayer.getName().equals(name)) {
                return inWarPlayer;
            }
        }
        return null;
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public int getScore() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // TODO: 4/18/2022
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public Color getColor() {
        return this.color;
    }
}
