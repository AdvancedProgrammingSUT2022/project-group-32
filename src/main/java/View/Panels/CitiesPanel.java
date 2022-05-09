package View.Panels;

import Controller.GameController;
import Model.City;
import Model.Player;
import View.GameMenu;
import enums.Color;

import java.util.ArrayList;
import java.util.Comparator;

public class CitiesPanel extends GameMenu {

    private static final ArrayList<City> cities = new ArrayList<>();


    public static void run(String command) {
        initializeCities();
        if (command.startsWith("cities panel")) {
            printPanel();
        }

    }

    private static void initializeCities() {
        for (Player player : GameController.getGame().getPlayers()) {
            cities.addAll(player.getCities());
        }
        cities.sort(Comparator.comparing(City::getName));
    }

    private static void printPanel() {
        int i = 0;
        for (City city : cities) {
            i++;
            System.out.println(city.getOwner().getBackgroundColor().code + i + "- " + city.getName() + " \t" + city.getOwner().getName() + Color.RESET);
        }
    }
}
