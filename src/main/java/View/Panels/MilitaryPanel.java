package View.Panels;

import Controller.GameController;
import Model.Units.Troop;
import View.GameMenu;

import java.util.ArrayList;

public class MilitaryPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show panel")) {
            printPanel();
        }
    }

    private static void printPanel() {
        ArrayList<Troop> troops = new ArrayList<>(GameController.getCurrentPlayer().getUnits().stream().
                filter(u -> u instanceof Troop).filter(u -> u.getRemainingCost() == 0).map(u -> (Troop) u).toList());
        printRow("Name", "Status", "HP", "Melee Strength", "Ranged Strength", "Destination");
        for (Troop troop : troops) {
            printRow(troop.getUnitType().name,
                    troop.getOrderType().toString(),
                    troop.getHP() + "",
                    troop.getMeleeStrength() + "",
                    troop.getRangedStrength() + "",
                    (troop.getDestination() == troop.getTile()) ? "-" : (troop.getDestination().getRow() + ", " + troop.getDestination().getColumn()));
        }
    }

    private static void printRow(String s1, String s2, String s3, String s4, String s5, String s6) {
        String format = "|%1$-13s|%2$-10s|%3$-8s|%4$-17s|%5$-17s|%6$-15s|";
        System.out.format(format, s1, s2, s3, s4, s5, s6);
        System.out.println();
    }
}
