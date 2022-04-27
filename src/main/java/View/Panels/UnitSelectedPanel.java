package View.Panels;

import Controller.GameController;
import Controller.UnitController;
import View.CLI;
import View.GameMenu;
import View.Menu;

import java.util.ArrayList;

public class UnitSelectedPanel extends GameMenu {
    public static void run(String command) {
        if(command.startsWith("move unit")){
            moveTo(command);
        } else if(command.startsWith("back")){
            GameMenu.currentPanel = null;
        } else if(command.startsWith("build city")){
            foundCity(command);
        } else if(command.startsWith("show selected unit")){
            showSelected();
        }  else {
            invalidCommand();
        }
    }

    public static void showSelected(){
        System.out.println(GameController.getSelectedUnit().getTile().getRow() + " " + GameController.getSelectedUnit().getTile().getColumn());
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

    public static void foundCity(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "cn");
        if(parameters == null){
            invalidCommand();
            return;
        }
        System.out.println(UnitController.foundCity(parameters.get(0)).getString());
    }

    public static void setOrder(String command) {

    }
}
