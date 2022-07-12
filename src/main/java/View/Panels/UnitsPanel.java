package View.Panels;

import Controller.GameController;
import Model.Units.Unit;
import View.PastViews.CLI;
import View.PastViews.GameMenu;
import enums.Color;
import enums.Responses.InGameResponses;

import java.util.ArrayList;

public class UnitsPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show panel")) {
            showPanel();
        } else if (command.startsWith("open unit ")) {
            openUnit(command);
        } else {
            invalidCommand();
        }
    }


    private static void showPanel() {
        int i = 0;
        printRow("id", "Type", "Location");
        for (Unit unit : GameController.getCurrentPlayer().getUnits()) {
            i++;
            printRow(unit.getOwner().getBackgroundColor().code + i + "  " + Color.RESET.code,
                    unit.getUnitType().name,
                    unit.getRow() + " " + unit.getColumn()
            );
        }
    }

    private static void openUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "id");
        if (parameters == null || !parameters.get(0).matches("-?\\d+")) {
            invalidCommand();
            return;
        }
        int id = Integer.parseInt(parameters.get(0)) - 1;
        if (id < 0 || id >= GameController.getCurrentPlayer().getUnits().size()) {
            System.out.println(InGameResponses.Info.INVALID_ID.getString());
            return;
        }
        GameController.setSelectedUnit(GameController.getCurrentPlayer().getUnits().get(id));
        currentPanel = PanelType.UNIT_SELECTED_PANEL;
        System.out.println("unit opened successfully");
    }

    private static void printRow(String s1, String s2, String s3) {
        String format = "|%1$-3s|%2$-15s|%3$-15s|";
        System.out.format(format, s1, s2, s3);
        System.out.println();
    }
}
