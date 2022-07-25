package View.Panels;

import Model.Player;
import Model.Request;
import View.Network;
import View.PastViews.GameMenu;
import enums.RequestActions;

public class DiplomacyPanel extends GameMenu {
    public static void run(String command) {

    }

    public static String showPanel() {
        Player player = ((Player) Network.getResponseObjOf(RequestActions.GET_THIS_PLAYER.code, null));
        String result = "Currently in War with:\n";
        for (Player inWarPlayer : player.getInWarPlayers()) {
            result += inWarPlayer.getName() + "\n";
        }
        return result;
    }

}
