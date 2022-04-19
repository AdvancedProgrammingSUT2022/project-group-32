package View;

import java.util.Scanner;

public class Menu {
    private static MenuType currentMenu;

    public enum MenuType {
        MAIN_MENU("main menu"),
        LOGIN_MENU("login menu"),
        GAME_MENU("game menu"),
        PROFILE_MENU("profile menu"),
        EXIT("exit");

        String name;

        MenuType(String name) {
            this.name = name;
        }
    }

    public static MenuType getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(MenuType currentMenu) {
        Menu.currentMenu = currentMenu;
    }

    public static void run(Scanner scanner) {
        currentMenu = MenuType.LOGIN_MENU;
        while(currentMenu != MenuType.EXIT){
            switch (currentMenu) {
                case LOGIN_MENU -> LoginMenu.run(scanner);
                case MAIN_MENU ->  MainMenu.run(scanner);
                case GAME_MENU ->  GameMenu.run(scanner);
                case PROFILE_MENU -> ProfileMenu.run(scanner);
            }
        }


        // TODO: 4/17/2022 Menu Navigation should be handled in view as it makes sense
    }

    public static void showCurrentMenu(String command) {
        // TODO: 4/19/2022 //?
    }
}
