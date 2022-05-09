package View.Panels;

import Controller.GameController;
import Controller.UnitController;
import View.CLI;
import View.GameMenu;
import View.Menu;
import enums.ImprovementType;

import java.util.ArrayList;

public class UnitSelectedPanel extends GameMenu {
    public static void run(String command) {
        if(command.startsWith("move unit")){
            moveTo(command);
        } else if(command.startsWith("back")){
            GameMenu.currentPanel = null;
        } else if(command.startsWith("build city")){
            foundCity(command);
        } else if(command.startsWith("sleep")){
            sleep();
        }
        else if(command.startsWith("show selected unit")){
            showSelected();
        } else {
            invalidCommand();
        }
    }

    private static void showSelected(){
        System.out.println(GameController.getSelectedUnit().getTile().getRow() + " " + GameController.getSelectedUnit().getTile().getColumn());
    }

    private static void moveTo(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(UnitController.moveTo(row, column).getString());
    }

    private static void foundCity(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "cn");
        if(parameters == null){
            invalidCommand();
            return;
        }
        System.out.println(UnitController.foundCity(parameters.get(0)).getString());
    }

    private static void sleep(){
        System.out.println(UnitController.sleep().getString());
    }

    private static void alert(){
        System.out.println(UnitController.alert().getString());
    }

    private static void fortify(){
        System.out.println(UnitController.fortify().getString());
    }

    private static void heal(){
        System.out.println(UnitController.heal().getString());
    }

    private static void wake(){
        System.out.println(UnitController.wake().getString());
    }

    private static void delete(){
        System.out.println(UnitController.delete().getString());
    }

    private static void buildImprovement(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if(parameters == null){
            invalidCommand();
            return;
        }
        System.out.println(UnitController.buildImprovement(ImprovementType.getTypeByName(parameters.get(0))).getString());
    }

}
