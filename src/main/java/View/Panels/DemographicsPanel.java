package View.Panels;

import Controller.GameController;
import Model.City;
import Model.Player;
import View.PastViews.GameMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DemographicsPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show panel")) {
            printPanel();
        }
    }

    private static void printPanel() {
        System.out.println("### DEMOGRAPHICS");
        printRow("Demographic",
                "value",
                "Best",
                "Avg.",
                "Worst",
                "Rank");
        Player player = GameController.getCurrentPlayer();
        ArrayList<City> cities = player.getCities();
        ArrayList<Player> players = GameController.getGame().getPlayers();

        // Population
        players.sort(Comparator.comparing(Player::getPopulation));
        Collections.reverse(players);
        printRow(
                "Population",
                player.getPopulation() + "",
                players.get(0).getPopulation() + "",
                players.stream().mapToInt(Player::getPopulation).average().getAsDouble() + "",
                players.get(players.size() - 1).getPopulation() + "",
                ((player.getPopulation() == players.get(0).getPopulation()) ? 1 : players.indexOf(player) + 1) + ""
        );

        // Production
        players.sort(Comparator.comparing(Player::getProduction));
        Collections.reverse(players);

        printRow(
                "Production",
                player.getProduction() + "",
                players.get(0).getProduction() + "",
                players.stream().mapToInt(Player::getProduction).average().getAsDouble() + "",
                players.get(players.size() - 1).getProduction() + "",
                ((player.getProduction() == players.get(0).getProduction()) ? 1 : players.indexOf(player) + 1) + ""
        );

        // GOLD
        players.sort(Comparator.comparing(Player::getGold));
        Collections.reverse(players);
        printRow(
                "Gold",
                player.getGold() + "",
                players.get(0).getGold() + "",
                players.stream().mapToInt(Player::getGold).average().getAsDouble() + "",
                players.get(players.size() - 1).getGold() + "",
                ((player.getGold() == players.get(0).getGold()) ? 1 : players.indexOf(player) + 1) + ""
        );

        // Territory
        players.sort(Comparator.comparing(Player::getTerritoryCount));
        Collections.reverse(players);

        printRow(
                "Land",
                player.getTerritoryCount() + "",
                players.get(0).getTerritoryCount() + "",
                players.stream().mapToInt(Player::getTerritoryCount).average().getAsDouble() + "",
                players.get(players.size() - 1).getTerritoryCount() + "",
                ((player.getTerritoryCount() == players.get(0).getTerritoryCount()) ? 1 : players.indexOf(player) + 1) + ""
        );

        // Troops
        players.sort(Comparator.comparing(Player::getTroopCount));
        Collections.reverse(players);
        printRow(
                "Troops",
                player.getTroopCount() + "",
                players.get(0).getTroopCount() + "",
                players.stream().mapToInt(Player::getTroopCount).average().getAsDouble() + "",
                players.get(players.size() - 1).getTroopCount() + "",
                ((player.getTroopCount() == players.get(0).getTroopCount()) ? 1 : players.indexOf(player) + 1) + ""
        );

    }

    private static void printRow(String s1, String s2, String s3, String s4, String s5, String s6) {
        String format = "|%1$-13s|%2$-8s|%3$-8s|%4$-8s|%5$-8s|%6$-8s|";
        System.out.format(format, s1, s2, s3, s4, s5, s6);
        System.out.println();
    }
}
