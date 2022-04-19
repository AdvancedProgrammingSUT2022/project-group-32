package Controller;

import View.Menu;

public class MenuController {
    public static String currentMenu() {
        return Menu.getCurrentMenu().name();
    }

    // TODO: 4/17/2022 maybe more functions?
}
