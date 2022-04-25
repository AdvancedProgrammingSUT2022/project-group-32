package Model.Units;

import Model.Player;
import Model.Tile;
import enums.CombatType;
import enums.ResourceType;
import enums.TechnologyType;
import enums.UnitType;

import java.util.ArrayList;

public class Troop extends Unit {
    private int meleeStrength;
    private int rangedStrength;
    private int range;
    private int beenFortified;
    private boolean isFortified;
    private CombatType combatType;
    private final ResourceType neededResource;
    private final TechnologyType neededTechnology;

    public Troop(Tile tile, Player owner, UnitType unitType) {
        super(tile, owner, unitType);
        this.meleeStrength = unitType.strength;
        this.rangedStrength = unitType.rangedStrength;
        this.range = unitType.range;
        this.beenFortified = 0;
        this.isFortified = false;
        this.combatType = unitType.combatType;
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

    public CombatType getCombatType() {
        return combatType;
    }

    public void setCombatType(CombatType combatType) {
        this.combatType = combatType;
    }

    public int getBeenFortified() {
        return beenFortified;
    }

    public void setBeenFortified(int beenFortified) {
        this.beenFortified = beenFortified;
    }

    public boolean isFortified() {
        return isFortified;
    }

    public void setFortified(boolean fortified) {
        isFortified = fortified;
    }

    public ResourceType getNeededResource() {
        return neededResource;
    }

    public TechnologyType getNeededTechnology() {
        return neededTechnology;
    }
}
