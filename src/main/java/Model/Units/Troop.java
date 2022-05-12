package Model.Units;

import Model.Player;
import Model.Tile;
import enums.CombatType;
import enums.ResourceType;
import enums.TechnologyType;
import enums.UnitType;

public class Troop extends Unit {
    private int meleeStrength;
    private int rangedStrength;
    private int range;
    private int fortifyBonus;
    private final ResourceType neededResource;
    private final TechnologyType neededTechnology;

    public Troop(Tile tile, Player owner, UnitType unitType) {
        super(tile, owner, unitType);
        this.meleeStrength = unitType.strength;
        this.rangedStrength = unitType.rangedStrength;
        this.range = unitType.range;
        this.fortifyBonus = 0;
        this.neededResource = unitType.neededResource;
        this.neededTechnology = unitType.neededTech;
    }

    public int getMeleeStrength() {
        return meleeStrength;
    }

    public void setMeleeStrength(int meleeStrength) {
        this.meleeStrength = meleeStrength;
    }

    public int getRangedStrength() {
        return rangedStrength;
    }

    public void setRangedStrength(int rangedStrength) {
        this.rangedStrength = rangedStrength;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getFortifyBonus() {
        return fortifyBonus;
    }

    public void setFortifyBonus(int fortifyBonus) {
        this.fortifyBonus = fortifyBonus;
    }

    public ResourceType getNeededResource() {
        return neededResource;
    }

    public TechnologyType getNeededTechnology() {
        return neededTechnology;
    }

}
