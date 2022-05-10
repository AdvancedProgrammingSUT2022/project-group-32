package View.Panels;

import Controller.GameController;
import Model.City;
import View.GameMenu;

import java.util.ArrayList;

public class EconomyPanel extends GameMenu {
    private static final ArrayList<City> cities = new ArrayList<>();

    public static void run(String command) {
        cities.addAll(GameController.getCurrentPlayer().getCities()); // this is what used every where, for same indexing ...
        if (command.startsWith("show")) {
            printPanel();
        }
    }

    private static void printPanel() {
        int i = 0;
        System.out.println("### ECONOMY");
        printRow("#", "Name", "Population", "Food", "Gold", "Science", "Production", "Building");
        for (City city : cities) {
            i++;
            printRow(i + "",
                    city.getName(),
                    city.getPopulation() + "",
                    city.getFoodIncome() + "",
                    city.getGoldIncome() + "",
                    city.getScienceIncome() + "",
                    city.getProductionIncome() + "",
                    (((city.getBuildingInProgress() == null) ? "-" : city.getBuildingInProgress().getName()))
            );

        }
    }

    private static void printRow(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        String format = "|%1$-4s|%2$-15s|%3$-12s|%4$-12s|%5$-12s|%6$-12s|%6$-12s|%6$-12s|";
        System.out.format(format, s1, s2, s3, s4, s5, s6, s7, s8);
        System.out.println();
    }

}
