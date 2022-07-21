package View.Panels;

import Controller.GameController;
import Model.Player;
import Model.Request;
import View.Network;
import View.PastViews.GameMenu;
import enums.RequestActions;

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

    public static String showPanel() {
        StringBuilder panelContent = new StringBuilder();
        Player player = (Player) Network.getResponseObjOf(RequestActions.GET_THIS_PLAYER.code, null);
        for (String notification : player.getNotifications()) {
            panelContent.append(notification).append("\n");
        }
        if (player.getNotifications().size() == 0) {
            panelContent.append("no notifications!");
        }
        return panelContent.toString();
    }

    public static String showRecent() {
        StringBuilder result = new StringBuilder();
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
            result.append(notification + "\n");
        }
        if (notifications.isEmpty()) {
            result.append("no recent notifications!");
        }
        return result.toString();
    }
    /////////////////////////////////////////////////////////

}
