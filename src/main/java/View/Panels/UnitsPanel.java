package View.Panels;

import Model.Units.Unit;
import View.GameMenu;

import java.util.ArrayList;

public class UnitsPanel extends GameMenu {
    private static final ArrayList<Unit> playerUnits = new ArrayList<>();

    public static void run(String command) {

    }

    private static void printPanel() {
        int i = 0;
        System.out.println("#  Name\tHP\tMP");
        for (Unit unit : playerUnits) {
            System.out.println(i + " -" + unit.getUnitType().name + "\t" + unit.getHP() + "\t" + unit.getMP());
        }
    }
}
