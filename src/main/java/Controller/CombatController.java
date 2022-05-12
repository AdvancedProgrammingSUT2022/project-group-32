package Controller;

import Model.City;
import Model.Tile;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.CombatType;

public class CombatController {
    public static void meleeAttack(Troop attacker, Unit defender) {

    }

    public static void meleeAttack(Troop attacker, City defender) {
        double attPower = attacker.getMeleeStrength() * (attacker.getHP() / attacker.getHealth());
        attPower *= 1 + attacker.getTile().getTerrain().getCombat();
        double defPower = defender.getStrength();

        if(attacker.getUnitType().combatType == CombatType.MOUNTED) attacker.setMP(attacker.getMP() - 1);
        else attacker.setMP(0);
        attacker.setFortifyBonus(0);

        defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));
        attacker.setHP(attacker.getHP() - attacker.getHealth() * (defPower / (attPower * 2)));

        if(attacker.getHP() <= 0){
            attacker.destroy();
            attacker.getOwner().addNotification(GameController.getTurn() + ": a unit of yours has died!");
        } else if(defender.getHP() <= 0){
            attacker.placeIn(defender.getCapitalTile(), GameController.getMap());
            captureCity(defender);
        }
    }

    public static void rangedAttack(Troop attacker, Unit defender) {

    }

    public static void rangedAttack(Troop attacker, City defender) {
        double attPower = attacker.getRangedStrength() * (attacker.getHP() / attacker.getHealth());
        attPower *= 1 + attacker.getTile().getTerrain().getCombat();
        double defPower = defender.getStrength();

        if(attacker.getUnitType().combatType == CombatType.MOUNTED) attacker.setMP(attacker.getMP() - 1);
        else attacker.setMP(0);
        attacker.setFortifyBonus(0);

        defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));
        defender.setHP(Math.max(defender.getHP(), 1));
    }

    public static void rangedAttack(City attacker, Troop defender) {
        double attPower = attacker.getStrength();
        double defPower = defender.getMeleeStrength();
        defPower *= 1 + defender.getTile().getTerrain().getCombat();
        defPower *= 1 + 0.25 * defender.getFortifyBonus();

        attacker.setHasAttacked(true);
        defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));
        if(defender.getHP() <= 0) {
            defender.destroy();
            defender.getOwner().addNotification(GameController.getTurn() + ": a unit of yours has died!");
        }
    }

    public static void captureCity(City enemyCity) {
        Unit unit = GameController.getSelectedUnit();
    }

    public static void captureUnit(Unit enemyUnit) {

    }
}
