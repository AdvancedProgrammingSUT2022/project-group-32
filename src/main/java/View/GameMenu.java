package View;

import java.util.Scanner;

public class GameMenu extends Menu{
    public enum PanelType {
        CITY_SELECTED
    }

    private static final PanelType panelType = null;

    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();

        }
    }

    private static void showMap(String command) {

    }

    private static void moveMap(String command){

    }

    private static void selectUnit(String command){

    }

    private static void selectTile(String command){

    }

    private static void selectCity(String command){

    }

    private static void endGame(String command){

    }

    private static void openPanel(String command){

    }

    private static void runPanel(String command){

    }

    public static void ShowCurrentPanel(String command){
        System.out.println(panelType);
    }
}
