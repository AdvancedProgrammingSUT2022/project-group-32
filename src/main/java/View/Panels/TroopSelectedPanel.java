package View.Panels;

import Controller.UnitController;
import View.CLI;
import View.GameMenu;

import java.util.ArrayList;

public class TroopSelectedPanel extends GameMenu {
    public static void run(String command) {
        if(command.startsWith("move troop")){
            moveTo(command);
        } else if(command.startsWith("back")){
            GameMenu.currentPanel = null;
        } else {
            invalidCommand();
        }
    }

    public static void moveTo(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(UnitController.moveTo(row, column).getString());
    }
}
