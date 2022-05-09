package View.Panels;

import Controller.GameController;
import Model.City;
import Model.Player;
import View.CLI;
import View.GameMenu;
import enums.Color;
import enums.Responses.InGameResponses;

import java.util.ArrayList;
import java.util.Comparator;

public class CitiesPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show panel")) {
            showPanel();
        } else if(command.startsWith("open city ")){
            openCity(command);
        } else {
            invalidCommand();
        }
    }

    private static void showPanel() {
        int i = 0;
        for (City city : GameController.getCurrentPlayer().getCities()) {
            i++;
            printRow(city.getOwner().getBackgroundColor().code + i + "  " + Color.RESET.code,
                    city.getName(),
                    city.getOwner().getName()
            );
        }
    }

    private static void openCity(String command){
        ArrayList<String> parameters = CLI.getParameters(command, "id");
        if(parameters == null || !parameters.get(0).matches("-?\\d+")){
            invalidCommand();
            return;
        }
        int id = Integer.parseInt(parameters.get(0)) - 1;
        if (id < 0 || id >= GameController.getCurrentPlayer().getCities().size()){
            System.out.println(InGameResponses.Info.INVALID_ID.getString());
            return;
        }
        GameController.setSelectedCity(GameController.getCurrentPlayer().getCities().get(id));
        currentPanel = PanelType.CITY_SELECTED_PANEL;
        System.out.println("city opened successfully");
    }

    private static void printRow(String s1, String s2, String s3) {
        String format = "|%1$-5s|%2$-15s|%3$-15s|";
        System.out.format(format, s1, s2, s3);
        System.out.println();
    }
}
