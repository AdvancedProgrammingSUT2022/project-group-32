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
    private CombatType combatType;
    private final ArrayList<ResourceType> neededResources = new ArrayList<ResourceType>();
    private final ArrayList<TechnologyType> neededTechnologies = new ArrayList<TechnologyType>();

    public Troop(Tile tile, Player owner, UnitType unitType) {
        super(tile, owner, unitType);

    }

    @Override
    protected void setFieldsFromDatabase(UnitType unitType) {
        // TODO: 4/16/2022 set health, cost, movement, HP, XP, ranges, needed techs , ...

    }

}
