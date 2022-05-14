package Model;

import enums.Types.RuinType;
import enums.Types.TechnologyType;
import enums.Types.UnitType;

import java.util.ArrayList;

public class Ruin {
    private RuinType ruinType;
    private int gold;
    private int population;
    private ArrayList<UnitType> units;
    private ArrayList<Tile> vision;
    private ArrayList<TechnologyType> technologies;
    private boolean isUsed;

    public Ruin(RuinType ruinType) {
        this.ruinType = ruinType;
        this.isUsed = false;
    }

    public RuinType getRuinType() {
        return ruinType;
    }

    public void setRuinType(RuinType ruinType) {
        this.ruinType = ruinType;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<UnitType> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<UnitType> units) {
        this.units = units;
    }

    public ArrayList<Tile> getVision() {
        return vision;
    }

    public void setVision(ArrayList<Tile> vision) {
        this.vision = vision;
    }

    public ArrayList<TechnologyType> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<TechnologyType> technologies) {
        this.technologies = technologies;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
