package View;

import java.util.Scanner;

public class MenuHandler {
    public static MenuType currentMenu;

    public enum MenuType {
        MAIN_MENU,
        LOGIN_MENU,
        GAME,
        PROFILE,
        EXIT
    }

    public static void run(Scanner scanner) {
        currentMenu = MenuType.LOGIN_MENU;
        switch (currentMenu) {
            case LOGIN_MENU -> LoginMenu.run(scanner);

        }

        // TODO: 4/16/2022 current X,Y in PlayerController and cameraMove
    }
}
