package Controller;

import Model.City;
import Model.Player;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.Types.CombatType;
import enums.Types.UnitType;

public class CombatController {

    public static void meleeAttack(Troop attacker, Unit defender) {

    }

    public static void meleeAttack(Troop attacker, City defender) {
        double attPower = attacker.getMeleeStrength() * (attacker.getHP() / attacker.getHealth());
        attPower *= 1 + attacker.getTile().getTerrain().getCombat();
        if (attacker.getUnitType().equals(UnitType.TANK)) attPower *= 0.9;
        double defPower = defender.getStrength();

        if (attacker.getUnitType().combatType == CombatType.MOUNTED) attacker.setMP(attacker.getMP() - 1);
        else attacker.setMP(0);
        attacker.setFortifyBonus(0);

        if (defPower >= 0) defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));
        else defender.setHP(0);
        attacker.setHP(attacker.getHP() - attacker.getHealth() * (defPower / (attPower * 2)));

        if (attacker.getHP() <= 0) {
            attacker.destroy();
            attacker.getOwner().addNotification(GameController.getTurn() + ": a unit of yours has died!");
        } else if (defender.getHP() <= 0) {
            attacker.placeIn(defender.getCapitalTile(), GameController.getMap());
            captureCity(attacker.getOwner(), defender);
        }
    }

    public static void rangedAttack(Troop attacker, Unit defender) {

    }

    public static void rangedAttack(Troop attacker, City defender) {
        double attPower = attacker.getRangedStrength() * (attacker.getHP() / attacker.getHealth());
        attPower *= 1 + attacker.getTile().getTerrain().getCombat();
        if (attacker.getCombatType().equals(CombatType.SIEGE)) attPower *= 1.1;
        double defPower = defender.getStrength();

        if (attacker.getUnitType().combatType == CombatType.MOUNTED) attacker.setMP(attacker.getMP() - 1);
        else attacker.setMP(0);
        attacker.setFortifyBonus(0);

        if (defPower >= 0) defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));
        else defender.setHP(0);
        defender.setHP(Math.max(defender.getHP(), 1));
    }

    public static void rangedAttack(City attacker, Troop defender) {
        double attPower = attacker.getStrength();
        double defPower = defender.getMeleeStrength();
        defPower *= 1 + defender.getTile().getTerrain().getCombat();
        defPower *= 1 + 0.25 * defender.getFortifyBonus();
        if (defender.getCombatType().equals(CombatType.SIEGE)) defPower *= 1.1;
        if (defender.getUnitType().equals(UnitType.TANK)) defPower *= 0.9;

        attacker.setHasAttacked(true);
        defender.setHP(defender.getHP() - defender.getHealth() * (attPower / (defPower * 2)));

        if (defender.getHP() <= 0) {
            defender.destroy();
            defender.getOwner().addNotification(GameController.getTurn() + ": a unit of yours has died!");
        }
    }

    public static void captureCity(Player player, City city) {
        int turn = GameController.getTurn();
        city.getOwner().addNotification(turn + ": you have lost the city of " + city.getName());
        city.getOwner().removeCity(city);
        city.setOwner(player);
        player.addCity(city);
        player.addNotification(turn + ": you have have captured the city of" + city.getName());
    }

    public static void captureUnit(Unit enemyUnit) {

    }
}
