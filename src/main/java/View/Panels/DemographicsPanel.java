package View.Panels;

import Controller.GameController;
import Model.City;
import Model.Player;
import View.GameMenu;

import java.util.ArrayList;
import java.util.Comparator;

public class DemographicsPanel extends GameMenu {
    public static void run(String command) {

        if (command.startsWith("demography panel")) {
            printPanel();
        }
    }

    private static void printPanel() {
        System.out.println("Demographic\t" +
                "value\t" +
                "Best\t" +
                "Avg.\t" +
                "Worst\t" +
                "Rank");
        Player player = GameController.getCurrentPlayer();
        ArrayList<City> cities = player.getCities();
        ArrayList<Player> players = GameController.getGame().getPlayers();

        // Population
        players.sort(Comparator.comparing(Player::getPopulation));
        System.out.println(
                "Population\t" +
                        player.getPopulation() + "\t" +
                        players.get(0).getPopulation() + "\t" +
                        players.stream().mapToInt(Player::getPopulation).average() + "\t" +
                        players.get(players.size() - 1).getPopulation() + "\t" +
                        players.indexOf(player) + 1
        );

        // Production
        players.sort(Comparator.comparing(Player::getProduction));
        System.out.println(
                "Population\t" +
                        player.getProduction() + "\t" +
                        players.get(0).getProduction() + "\t" +
                        players.stream().mapToInt(Player::getProduction).average() + "\t" +
                        players.get(players.size() - 1).getProduction() + "\t" +
                        players.indexOf(player) + 1
        );

        // GOLD
        players.sort(Comparator.comparing(Player::getGold));
        System.out.println(
                "Population\t" +
                        player.getGold() + "\t" +
                        players.get(0).getGold() + "\t" +
                        players.stream().mapToInt(Player::getGold).average() + "\t" +
                        players.get(players.size() - 1).getGold() + "\t" +
                        players.indexOf(player) + 1
        );

        // Territory
        players.sort(Comparator.comparing(Player::getTerritoryCount));
        System.out.println(
                "Population\t" +
                        player.getTerritoryCount() + "\t" +
                        players.get(0).getTerritoryCount() + "\t" +
                        players.stream().mapToInt(Player::getTerritoryCount).average() + "\t" +
                        players.get(players.size() - 1).getTerritoryCount() + "\t" +
                        players.indexOf(player) + 1
        );

        // Troops
        players.sort(Comparator.comparing(Player::getTroopCount));
        System.out.println(
                "Population\t" +
                        player.getTroopCount() + "\t" +
                        players.get(0).getTroopCount() + "\t" +
                        players.stream().mapToInt(Player::getTroopCount).average() + "\t" +
                        players.get(players.size() - 1).getTroopCount() + "\t" +
                        players.indexOf(player) + 1
        );

    }
}
