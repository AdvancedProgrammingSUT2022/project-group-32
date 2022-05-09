package View;

import Controller.GameController;
import Controller.PlayerController;
import Model.Tile;
import View.Panels.*;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class GameMenu extends Menu {
    private static final int SCREEN_WIDTH = 150;
    private static final int SCREEN_HEIGHT = 26;

    public enum PanelType {
        CITIES_PANEL("cities", x -> CitiesPanel.run(x)),
        CITY_SELECTED_PANEL("citySelected", x -> CitySelectedPanel.run(x)),
        DEALS_PANEL("deals", x -> DealsPanel.run(x)),
        DEMOGRAPHICS_PANEL("demographics", x -> DemographicsPanel.run(x)),
        DIPLOMACY_PANEL("diplomacy", x -> DiplomacyPanel.run(x)),
        DIPLOMATIC_PANEL("diplomatic", x -> DiplomaticPanel.run(x)),
        ECONOMY_PANEL("economy", x -> EconomyPanel.run(x)),
        MILITARY_PANEL("military", x -> MilitaryPanel.run(x)),
        NOTIFICATIONS_PANEL("notifications", x -> NotificationsPanel.run(x)),
        RESEARCH_PANEL("research", x -> ResearchPanel.run(x)),
        UNIT_SELECTED_PANEL("unitSelected", x -> UnitSelectedPanel.run(x)),
        UNITS_PANEL("units", x -> UnitsPanel.run(x)),
        VICTORY_PANEL("victory", x -> VictoryPanel.run(x));

        String name;
        Consumer<String> function;

        PanelType(String name, Consumer<String> consumer){
            this.function = consumer;
            this.name = name;
        }
    }

    protected static PanelType currentPanel = null;

    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("show map")) {
                showMap(command);
            } else if (command.startsWith("move map")) {
                moveMap(command);
            } else if (command.startsWith("select unit")) {
                selectUnit(command);
            } else if (command.startsWith("select troop")) {
                selectTroop(command);
            } else if (command.startsWith("select city")) {
                selectCity(command);
            } else if (command.startsWith("end game")) {
                endGame(command);
            } else if (command.startsWith("open panel")) {
                openPanel(command);
            } else if (command.startsWith("show current turn")) {
                showTurn(command);
            } else if (command.startsWith("pass turn")) {
                passTurn(command);
            } else if (command.startsWith("show current panel")) {
                showCurrentPanel(command);
            } else {
                runPanel(command);
            }
        }
    }

    private static void showMap(String command) {
        int tileRow = GameController.getGame().getCurrentPlayer().getCameraRow(); // TODO: 4/25/2022 must be accessed better
        int tileColumn = GameController.getGame().getCurrentPlayer().getCameraColumn();
        Tile[][] map = GameController.getCurrentPlayerMap().getTiles();
        String[][] stringMap = MapMaker.getMap(map);
        printTopBar();
        printMap(stringMap, MapMaker.getTileCenterRow(tileRow, tileColumn), MapMaker.getTileCenterColumn(tileRow, tileColumn));
    }

    private static void printTopBar() {
        System.out.println(MapMaker.getTopBar());
    }

    private static void printMap(String[][] map, int cameraRow, int cameraColumn) {

        for (int row = Math.max(0, cameraRow - SCREEN_HEIGHT / 2); row < Math.min(map.length, cameraRow + SCREEN_HEIGHT / 2); row++) {
            for (int column = Math.max(0, cameraColumn - SCREEN_WIDTH / 2);
                 column < Math.min(map[0].length, cameraColumn + SCREEN_WIDTH / 2);
                 column++) {
                System.out.print(map[row][column]);
            }
            System.out.println();
        }
    }


    private static void moveMap(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "d");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int amount = Integer.parseInt(parameters.get(1));
        System.out.println(GameController.changeCamera(parameters.get(0), amount).getString());
        showMap(command);
    }

    private static void selectUnit(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        Response.GameMenu response = GameController.selectUnit(row, column);
        if(response.equals(Response.GameMenu.NO_UNIT_IN_TILE)){
            System.out.println(response.getString(row + " " + column));
        } else {
            System.out.println(response.getString());
            currentPanel = PanelType.UNIT_SELECTED_PANEL;
        }
    }

    private static void selectTroop(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        Response.GameMenu response = GameController.selectTroop(row, column);
        if(response.equals(Response.GameMenu.NO_TROOP_IN_TILE)){
            System.out.println(response.getString(row + " " + column));
        } else {
            System.out.println(response.getString());
            currentPanel = PanelType.UNIT_SELECTED_PANEL;
        }
    }

    private static void selectCity(String command) {
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if(parameters == null){
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        Response.GameMenu response = GameController.selectCity(row, column);
        if(response.equals(Response.GameMenu.NO_CITY_IN_TILE)){
            System.out.println(response.getString(row + " " + column));
        } else {
            System.out.println(response.getString());
            currentPanel = PanelType.CITY_SELECTED_PANEL;
        }
    }

    private static void showTurn(String command){
        System.out.println(GameController.getGame().getCurrentPlayer().getName());
    }

    private static void passTurn(String command) {
        System.out.println(PlayerController.nextTurn().getString());
        currentPanel = null;
    }

    private static void endGame(String command) {

    }

    private static void openPanel(String command) {
        // to whoever implementing this . . .
        // cycle through panel enums and finding the right name
    }

    // supposed to run the current panel
    private static void runPanel(String command) {
        if(currentPanel == null){
            invalidCommand();
            return;
        }
        currentPanel.function.accept(command);
    }

    public static void showCurrentPanel(String command) {
        if(currentPanel == null){
            System.out.println("no panel is open");
            return;
        }
        System.out.println(currentPanel.name);
    }
}
