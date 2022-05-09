package View.Panels;

import Controller.CityController;
import View.CLI;
import View.GameMenu;
import enums.UnitType;

import java.util.ArrayList;

public class CitySelectedPanel extends GameMenu {
    public static void run(String command) {
        if(command.startsWith("build unit")) buildUnit(command);
        else if(command.startsWith("pause unit")) pauseUnit(command);
        else if(command.startsWith("build building")) buildBuilding(command);
        else if(command.startsWith("pause building")) pauseBuilding(command);
        else if(command.startsWith("buy unit")) buyUnit(command);
        else if(command.startsWith("assign citizen")) assignCitizen(command);
        else if(command.startsWith("free citizen")) freeCitizen(command);
        else if(command.startsWith("buy tile")) buyTile(command);
        else if(command.startsWith("show banner")) showBanner(command);
        else if(command.startsWith("back")) currentPanel = null;
        else invalidCommand();
    }

    private static void buildBuilding(String command) {

    }

    private static void pauseBuilding(String command) {

    }

    private static void buildUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if(parameters == null){
            invalidCommand();
            return;
        }
        UnitType unitType = UnitType.getUnitTypeByName(parameters.get(0));
        System.out.println(CityController.buildUnit(unitType).getString());
    }

    private static void pauseUnit(String command) {
        System.out.println(CityController.pauseInProgressUnit());
    }

    private static void buyUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if(parameters == null){
            invalidCommand();
            return;
        }
        UnitType unitType = UnitType.getUnitTypeByName(parameters.get(0));
        System.out.println(CityController.buyUnit(unitType).getString());
    }

    private static void assignCitizen(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.assignCitizenToTile(row, column).getString());
    }

    private static void freeCitizen(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.freeCitizenFromTile(row, column).getString());
    }

    private static void buyTile(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.buyTile(row, column));
    }

    private static void showBanner(String command){

    }
}
