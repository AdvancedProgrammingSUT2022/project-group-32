package View.Panels;

import Controller.CityController;
import Controller.GameController;
import Model.City;
import Model.Tile;
import View.PastViews.CLI;
import View.PastViews.GameMenu;
import enums.Color;
import enums.Types.UnitType;

import java.util.ArrayList;

public class CitySelectedPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("build unit")) buildUnit(command);
        else if (command.startsWith("pause unit")) pauseUnit();
        else if (command.startsWith("build building")) buildBuilding(command);
        else if (command.startsWith("pause building")) pauseBuilding(command);
        else if (command.startsWith("buy unit")) buyUnit(command);
        else if (command.startsWith("assign citizen")) assignCitizen(command);
        else if (command.startsWith("free citizen")) freeCitizen(command);
        else if (command.startsWith("buy tile")) buyTile(command);
        else if (command.startsWith("attack")) attack(command);
        else if (command.startsWith("delete")) delete();
        else if (command.startsWith("show banner")) showBanner();
        else if (command.startsWith("back")) currentPanel = null;
        else invalidCommand();
    }

    public static void buildBuilding(String command) {

    }

    public static void pauseBuilding(String command) {

    }

    public static void buildUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        UnitType unitType = UnitType.getUnitTypeByName(parameters.get(0));
        System.out.println(CityController.buildUnit(unitType).getString());
    }

    public static void pauseUnit() {
        System.out.println(CityController.pauseInProgressUnit());
    }

    public static void buyUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        UnitType unitType = UnitType.getUnitTypeByName(parameters.get(0));
        System.out.println(CityController.buyUnit(unitType).getString());
    }

    public static void assignCitizen(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.assignCitizenToTile(row, column).getString());
    }

    public static void freeCitizen(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.freeCitizenFromTile(row, column).getString());
    }

    public static void buyTile(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.buyTile(row, column));
    }

    public static void attack(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(CityController.attack(row, column).getString());
    }

    public static void delete() {
        System.out.println(CityController.delete().getString());
    }

    public static void showBanner() {
        City city = GameController.getSelectedCity();
        System.out.println(city.getOwner().getBackgroundColor().code
                + city.getName() + " owned by " + city.getOwner().getName() + Color.RESET.code);
        System.out.println("location: " + city.getCapitalTile().getRow() + "," + city.getCapitalTile().getColumn());
        System.out.println("HP: " + city.getHP());
        System.out.println("strength: " + city.getStrength());
        System.out.println("population: " + city.getPopulation());
        System.out.println("production: " + city.getProductionIncome());
        System.out.println("food: " + city.getFoodIncome());
        System.out.println("gold: " + city.getGoldIncome());
        System.out.println("unemployed citizen cnt: " + city.getFreeCitizens());
        System.out.println("tiles being worked on: ");
        for (Tile tile : city.getTerritory()) {
            if(tile.isHasCitizen()) System.out.println(tile.getRow() + "," + tile.getColumn());
        }
        if (city.getUnitInProgress() != null) {
            System.out.println("currently building a " + city.getUnitInProgress().getUnitType().name);
            int remainingTurns = (city.getUnitInProgress().getRemainingCost() + city.getProductionIncome() - 1) / city.getProductionIncome();
            System.out.println("will be completed in: " + remainingTurns);
        }
    }
}
