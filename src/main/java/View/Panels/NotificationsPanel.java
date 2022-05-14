package View.Panels;

import Controller.GameController;
import Model.Player;
import View.GameMenu;

import java.util.ArrayList;

public class NotificationsPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show panel")) {
            showPanel();
        } else if (command.startsWith("show recent")) {
            showRecent();
        } else {
            invalidCommand();
        }
    }

    private static void showPanel() {
        Player player = GameController.getCurrentPlayer();
        for (String notification : player.getNotifications()) {
            System.out.println(notification);
        }
        if (player.getNotifications().size() == 0) {
            System.out.println("no notifications!");
        }
    }

    private static void showRecent() {
        ArrayList<String> notifications = new ArrayList<>();
        Player player = GameController.getCurrentPlayer();
        int turn = GameController.getGame().getTurnCount();
        for (String notification : player.getNotifications()) {
            if (notification.startsWith(String.valueOf(turn)) || notification.startsWith(String.valueOf(turn - 1))) {
                notifications.add(notification);
            }
        }
        for (String notification : notifications) {
            System.out.println(notification);
        }
        if (notifications.isEmpty()) {
            System.out.println("no recent notifications!");
        }
    }
}
