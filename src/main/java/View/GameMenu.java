package View;

import enums.Responses.Response;

import java.util.Scanner;

public class GameMenu extends Menu {
    public enum PanelType {
        CITY_SELECTED, // TODO: others must be added
    }

    private static final PanelType panelType = null;

    public static void run(Scanner scanner) {

        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("show map")){
                showMap(command);
            }
            else if (command.startsWith("move map")){
                moveMap(command);
            }
            else if (command.startsWith("select unit")){
                selectUnit(command);
            }
            else if (command.startsWith("select tile")){
                selectTile(command);
            }
            else if (command.startsWith("select city")){
                selectCity(command);
            }
            else if (command.startsWith("end game")){
                endGame(command);
            }
            else if (command.startsWith("open panel")){
                openPanel(command);
            }
            else if (command.startsWith("run panel")){
                runPanel(command);
            }
            else if (command.startsWith("show current panel")){
                showCurrentPanel(command);
            }
            else{
                System.out.println(Response.LoginMenu.INVALID_COMMAND.getString());
            }
        }
    }

    private static void showMap(String command) {

    }

    private static void moveMap(String command) {

    }

    private static void selectUnit(String command) {

    }

    private static void selectTile(String command) {

    }

    private static void selectCity(String command) {

    }

    private static void endGame(String command) {

    }

    private static void openPanel(String command) {

    }

    private static void runPanel(String command) {

    }

    public static void showCurrentPanel(String command) {
        System.out.println(panelType);
    }
}
