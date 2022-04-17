package View;

import java.util.Scanner;

public class MenuHandler {
    public static MenuType currentMenu;

    public enum MenuType {
        MAIN_MENU("main menu"),
        LOGIN_MENU("login menu"),
        GAME_MENU("game menu"),
        PROFILE_MENU("profile menu"),
        EXIT("exit");

        String name;
        MenuType(String name){
            this.name = name;
        }
    }

    public static void run(Scanner scanner) {
        currentMenu = MenuType.LOGIN_MENU;
        switch (currentMenu) {
            case LOGIN_MENU -> LoginMenu.run(scanner);

        }

        // TODO: 4/17/2022 Menu Navigation should be handled in view as it makes sense
    }
}
