package Model.Citizens;

import Model.Building;
import Model.City;

public class Specialist extends Citizen {
    private Building building;

    public Specialist(City city) {
        super(city);
        this.building = null;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
