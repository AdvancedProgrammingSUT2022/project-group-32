package View.Panels;

import Controller.GameController;
import Model.City;
import View.GameMenu;

import java.util.ArrayList;

public class EconomyPanel extends GameMenu {
    private static ArrayList<City> cities;

    public static void run(String command) {
        cities.addAll(GameController.getCurrentPlayer().getCities()); // this is what used every where, for same indexing ...

    }

    private static void printPanel() {
        int i = 0;
        System.out.println("#  Name\tPopulation\tFood\tGold\tScience\tProduction\tBuilding");
        for (City city : cities) {
            i++;
            System.out.println(i + "- " + city.getName() + "\t" +
                    city.getPopulation() + "\t" +
                    city.getFoodIncome() + "\t" +
                    city.getGoldIncome() + "\t" +
                    city.getScienceIncome() + "\t" +
                    city.getProduction() + "\t" +
                    (((city.getBuildingInProgress() == null) ? "-" : city.getBuildingInProgress().getName()) + "\t")
            );

        }
    }

}
