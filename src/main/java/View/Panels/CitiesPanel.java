package View.Panels;

import Controller.GameController;
import Model.City;
import Model.Player;
import View.GameMenu;
import enums.Color;

import java.util.ArrayList;
import java.util.Comparator;

public class CitiesPanel extends GameMenu {

    private static ArrayList<City> cities = new ArrayList<>();


    public static void run(String command) {
        initializeCities();
        if (command.startsWith("show")) {
            printPanel();
        }

    }

    private static void initializeCities() {
        cities = new ArrayList<>();
        for (Player player : GameController.getGame().getPlayers()) {
            cities.addAll(player.getCities());
        }
        cities.sort(Comparator.comparing(City::getName));
    }

    private static void printPanel() {
        int i = 0;
        System.out.println("### CITIES");
        for (City city : cities) {
            i++;
            printRow(city.getOwner().getBackgroundColor().code + i + "  " + Color.RESET.code,
                    city.getName(),
                    city.getOwner().getName()
            );
        }
    }

    private static void printRow(String s1, String s2, String s3) {
        String format = "|%1$-5s|%2$-15s|%3$-15s|";
        System.out.format(format, s1, s2, s3);
        System.out.println();
    }
}
