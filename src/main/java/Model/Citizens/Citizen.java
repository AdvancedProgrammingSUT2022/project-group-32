package Model.Citizens;

import Model.City;
import Model.Tile;

public class Citizen {
    static int id = 0;
    private String name;
    private City city;
    private Tile assignedTile; //null = not assigned

    public Citizen(City city) {
        id++;
        this.name = "Naser Kazemi #" + id;
        this.city = city;
        this.assignedTile = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Tile getAssignedTile() {
        return assignedTile;
    }

    public void setAssignedTile(Tile assignedTile) {
        this.assignedTile = assignedTile;
    }
}
