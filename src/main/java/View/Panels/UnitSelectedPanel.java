package View.Panels;

import Controller.GameController;
import Controller.UnitController;
import View.CLI;
import View.GameMenu;
import View.Menu;
import enums.ImprovementType;
import enums.Responses.InGameResponses;
import enums.RoadType;

import java.util.ArrayList;

public class UnitSelectedPanel extends GameMenu {
    public static void run(String command) {
        if(command.startsWith("move unit")) moveTo(command);
        else if(command.startsWith("back")) GameMenu.currentPanel = null;
        else if(command.startsWith("build city")) foundCity(command);
        else if(command.startsWith("sleep")) sleep();
        else if(command.startsWith("alert")) alert();
        else if(command.startsWith("fortify")) fortify();
        else if(command.startsWith("heal")) heal();
        else if(command.startsWith("wake")) wake();
        else if(command.startsWith("delete")) delete();
        else if(command.startsWith("build improvement")) buildImprovement(command);
        else if(command.startsWith("build road")) buildRoad(command);
        else if(command.startsWith("remove forest")) removeForest();
        else if(command.startsWith("remove jungle")) removeJungle();
        else if(command.startsWith("remove marsh")) removeMarsh();
        else if(command.startsWith("remove road")) removeRoute();
        else if(command.startsWith("pillage")) pillage();
        else if(command.startsWith("repair")) repair();
        else if(command.startsWith("set up")) setup();
        else if(command.startsWith("garrison")) garrison();
        else if(command.startsWith("show selected unit")) showSelected();
        else invalidCommand();
    }

    private static void showSelected(){
        System.out.println(GameController.getSelectedUnit().getTile().getRow() + " " + GameController.getSelectedUnit().getTile().getColumn());
        System.out.println("type: " + GameController.getSelectedUnit().getUnitType().name);
        System.out.println("owner: " + GameController.getSelectedUnit().getOwner().getName());
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

    private static void buildRoad(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if(parameters == null){
            invalidCommand();
            return;
        }
        System.out.println(UnitController.buildRoad(RoadType.getTypeByName(parameters.get(0))).getString());
    }

    private static void removeForest(){
        System.out.println(UnitController.removeForest().getString());
    }

    private static void removeJungle(){
        System.out.println(UnitController.removeJungle().getString());
    }

    private static void removeMarsh(){
        System.out.println(UnitController.removeMarsh().getString());
    }

    private static void removeRoute(){
        System.out.println(UnitController.removeRoute().getString());
    }

    private static void pillage(){
        System.out.println(UnitController.pillage().getString());
    }

    private static void repair(){
        System.out.println(UnitController.repair().getString());
    }

    private static void setup(){
        System.out.println(UnitController.setup().getString());
    }

    private static void garrison(){
        System.out.println(UnitController.garrison().getString());
    }

}
